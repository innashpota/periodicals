package com.inna.shpota.periodicals.dao.jdbc;

import com.inna.shpota.periodicals.dao.SubscriptionDao;
import com.inna.shpota.periodicals.domain.Subscription;
import com.inna.shpota.periodicals.exception.DaoException;
import com.inna.shpota.periodicals.util.Assert;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class JdbcSubscriptionDao implements SubscriptionDao {
    private final static Logger LOGGER = Logger.getLogger(JdbcSubscriptionDao.class);
    private static final String SQL_INSERT_SUBSCRIPTION =
            "INSERT INTO subscription (reader_id, periodicals_id, month_quantity, date) VALUES (?, ?, ?, ?);";
    private static final String SQL_DELETE_SUBSCRIPTION =
            "DELETE FROM subscription WHERE id = ?;";
    private static final String SQL_SELECT_SUBSCRIPTION =
            "SELECT reader_id, periodicals_id, month_quantity, date FROM subscription WHERE id = ?;";
    private static final String SQL_UPDATE_SUBSCRIPTION =
            "UPDATE subscription " +
                    "SET reader_id = ?, periodicals_id = ?, month_quantity = ?, date = ? " +
                    "WHERE id = ?;";
    private static final String SQL_SELECT_ALL = "SELECT * FROM subscription;";
    private static final String SQL_INSERT_PAYMENT =
            "INSERT INTO payment (subscription_id, price, paid) VALUES (?, ?, ?);";
    private final DataSource dataSource;

    public JdbcSubscriptionDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public long create(Subscription subscription) {
        validate(subscription);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement createStatement = connection.prepareStatement(
                     SQL_INSERT_SUBSCRIPTION,
                     RETURN_GENERATED_KEYS
             )) {
            createStatement.setLong(1, subscription.getReaderId());
            createStatement.setLong(2, subscription.getPeriodicalsId());
            createStatement.setInt(3, subscription.getMonthQuantity());
            createStatement.setObject(4, subscription.getDate());
            createStatement.executeUpdate();
            LOGGER.info("Create new subscription: " + subscription);
            return getGeneratedId(createStatement);
        } catch (SQLException e) {
            LOGGER.error("SQLException occurred in JdbcPeriodicalsDao", e);
            throw new DaoException(e);
        }
    }

    @Override
    public long createPaymentBySubscription(Subscription subscription, BigDecimal monthPrice) {
        validate(subscription);
        Assert.isPositive(monthPrice, "Month price must be positive");
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement createSubscription = connection.prepareStatement(
                    SQL_INSERT_SUBSCRIPTION,
                    RETURN_GENERATED_KEYS);
                 PreparedStatement createPayment = connection.prepareStatement(
                         SQL_INSERT_PAYMENT,
                         RETURN_GENERATED_KEYS

                 )) {
                long subscriptionId = getSubscriptionId(subscription, createSubscription);
                BigDecimal monthQuantity = new BigDecimal(subscription.getMonthQuantity());
                BigDecimal price = monthPrice.multiply(monthQuantity);
                long paymentId = getPaymentId(createPayment, subscriptionId, price);
                connection.commit();
                return paymentId;
            } catch (SQLException e) {
                connection.rollback();
                LOGGER.error("SQLException occurred in JdbcPeriodicalsDao", e);
                throw new DaoException(e);
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException occurred in JdbcPeriodicalsDao", e);
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(long id) {
        Assert.isPositive(id, "ID must be positive");
        try (Connection connection = dataSource.getConnection();
             PreparedStatement deleteStatement = connection.prepareStatement(
                     SQL_DELETE_SUBSCRIPTION
             )) {
            deleteStatement.setLong(1, id);
            deleteStatement.executeUpdate();
            LOGGER.info("Delete subscription by ID: " + id);
        } catch (SQLException e) {
            LOGGER.error("SQLException occurred in JdbcPeriodicalsDao", e);
            throw new DaoException(e);
        }
    }

    @Override
    public Subscription getById(long id) {
        Assert.isPositive(id, "ID must be positive");
        try (Connection connection = dataSource.getConnection();
             PreparedStatement selectStatement = connection.prepareStatement(
                     SQL_SELECT_SUBSCRIPTION
             )) {
            selectStatement.setLong(1, id);
            try (ResultSet resultSet = selectStatement.executeQuery()) {
                Subscription subscription = null;
                if (resultSet.next()) {
                    subscription = Subscription.builder()
                            .id(id)
                            .readerId(resultSet.getLong("reader_id"))
                            .periodicalsId(resultSet.getLong("periodicals_id"))
                            .monthQuantity(resultSet.getInt("month_quantity"))
                            .date(resultSet.getObject("date", LocalDateTime.class))
                            .build();
                }
                LOGGER.info("Get by ID subscription: " + subscription);
                return subscription;
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException occurred in JdbcPeriodicalsDao", e);
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Subscription subscription) {
        validate(subscription);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement updateStatement = connection.prepareStatement(
                     SQL_UPDATE_SUBSCRIPTION
             )) {
            updateStatement.setLong(1, subscription.getReaderId());
            updateStatement.setLong(2, subscription.getPeriodicalsId());
            updateStatement.setInt(3, subscription.getMonthQuantity());
            updateStatement.setObject(4, subscription.getDate());
            updateStatement.setLong(5, subscription.getId());
            updateStatement.executeUpdate();
            LOGGER.info("Update subscription: " + subscription);
        } catch (SQLException e) {
            LOGGER.error("SQLException occurred in JdbcPeriodicalsDao", e);
            throw new DaoException(e);
        }
    }

    @Override
    public List<Subscription> getAll() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement allStatement = connection.prepareStatement(
                     SQL_SELECT_ALL
             )) {
            try (ResultSet resultSet = allStatement.executeQuery()) {
                List<Subscription> list = new ArrayList<>();
                while (resultSet.next()) {
                    list.add(Subscription.builder()
                            .id(resultSet.getLong("id"))
                            .readerId(resultSet.getLong("reader_id"))
                            .periodicalsId(resultSet.getLong("periodicals_id"))
                            .monthQuantity(resultSet.getInt("month_quantity"))
                            .date(resultSet.getObject("date", LocalDateTime.class))
                            .build()
                    );
                }
                LOGGER.info("Get all subscriptions: " + list);
                return list;
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException occurred in JdbcPeriodicalsDao", e);
            throw new DaoException(e);
        }
    }

    private long getSubscriptionId(
            Subscription subscription,
            PreparedStatement createSubscription
    ) throws SQLException {
        createSubscription.setLong(1, subscription.getReaderId());
        createSubscription.setLong(2, subscription.getPeriodicalsId());
        createSubscription.setInt(3, subscription.getMonthQuantity());
        createSubscription.setObject(4, subscription.getDate());
        createSubscription.executeUpdate();
        return getGeneratedId(createSubscription);
    }

    private long getPaymentId(
            PreparedStatement createPayment,
            long generatedIdSubscription,
            BigDecimal price
    ) throws SQLException {
        createPayment.setLong(1, generatedIdSubscription);
        createPayment.setBigDecimal(2, price);
        createPayment.setInt(3, 0);
        createPayment.executeUpdate();
        return getGeneratedId(createPayment);
    }
}
