package com.inna.shpota.periodicals.dao.jdbc;

import com.inna.shpota.periodicals.dao.PaymentDao;
import com.inna.shpota.periodicals.domain.Payment;
import com.inna.shpota.periodicals.exception.DaoException;
import com.inna.shpota.periodicals.util.Assert;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class JdbcPaymentDao implements PaymentDao {
    private final static Logger LOGGER = Logger.getLogger(JdbcPaymentDao.class);
    private static final String SQL_INSERT_PAYMENT =
            "INSERT INTO payment (subscription_id, price, paid) VALUES (?, ?, ?);";
    private static final String SQL_DELETE_PAYMENT =
            "DELETE FROM payment WHERE id = ?;";
    private static final String SQL_SELECT_PAYMENT =
            "SELECT subscription_id, price, paid FROM payment WHERE id = ?;";
    private static final String SQL_UPDATE_PAYMENT =
            "UPDATE payment SET subscription_id = ?, price = ?, paid = ? WHERE id = ?;";
    private static final String SQL_SELECT_ALL =
            "SELECT * FROM payment;";
    private final DataSource dataSource;

    public JdbcPaymentDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public long create(Payment payment) {
        validate(payment);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement createStatement = connection.prepareStatement(
                     SQL_INSERT_PAYMENT,
                     RETURN_GENERATED_KEYS
             )) {
            createStatement.setLong(1, payment.getSubscriptionId());
            createStatement.setBigDecimal(2, payment.getPrice());
            createStatement.setInt(3, payment.isPaid() ? 1 : 0);
            createStatement.executeUpdate();
            LOGGER.info("Create new payment: " + payment);
            return getGeneratedId(createStatement);
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
                     SQL_DELETE_PAYMENT
             )) {
            deleteStatement.setLong(1, id);
            deleteStatement.executeUpdate();
            LOGGER.info("Delete payment by ID: " + id);
        } catch (SQLException e) {
            LOGGER.error("SQLException occurred in JdbcPeriodicalsDao", e);
            throw new DaoException(e);
        }
    }

    @Override
    public Payment getById(long id) {
        Assert.isPositive(id, "ID must be positive");
        try (Connection connection = dataSource.getConnection();
             PreparedStatement selectStatement = connection.prepareStatement(
                     SQL_SELECT_PAYMENT
             )) {
            selectStatement.setLong(1, id);
            try (ResultSet resultSet = selectStatement.executeQuery()) {
                Payment payment = null;
                if (resultSet.next()) {
                    payment = new Payment(
                            id,
                            resultSet.getLong("subscription_id"),
                            resultSet.getBigDecimal("price"),
                            resultSet.getInt("paid") == 1);
                }
                LOGGER.info("Get by ID payment: " + payment);
                return payment;
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException occurred in JdbcPeriodicalsDao", e);
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Payment payment) {
        validate(payment);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement updateStatement = connection.prepareStatement(
                     SQL_UPDATE_PAYMENT
             )) {
            updateStatement.setLong(1, payment.getSubscriptionId());
            updateStatement.setBigDecimal(2, payment.getPrice());
            updateStatement.setInt(3, payment.isPaid() ? 1 : 0);
            updateStatement.setLong(4, payment.getId());
            updateStatement.executeUpdate();
            LOGGER.info("Update payment: " + payment);
        } catch (SQLException e) {
            LOGGER.error("SQLException occurred in JdbcPeriodicalsDao", e);
            throw new DaoException(e);
        }
    }

    @Override
    public List<Payment> getAll() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement allStatement = connection.prepareStatement(
                     SQL_SELECT_ALL
             )) {
            try (ResultSet resultSet = allStatement.executeQuery()) {
                List<Payment> list = new ArrayList<>();
                while (resultSet.next()) {
                    list.add(new Payment(
                            resultSet.getLong("id"),
                            resultSet.getLong("subscription_id"),
                            resultSet.getBigDecimal("price"),
                            resultSet.getInt("paid") == 1
                    ));
                }
                LOGGER.info("Get all payments: " + list);
                return list;
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException occurred in JdbcPeriodicalsDao", e);
            throw new DaoException(e);
        }
    }
}
