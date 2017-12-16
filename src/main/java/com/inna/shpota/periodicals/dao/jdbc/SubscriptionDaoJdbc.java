package com.inna.shpota.periodicals.dao.jdbc;

import com.inna.shpota.periodicals.dao.SubscriptionDao;
import com.inna.shpota.periodicals.domain.Subscription;
import com.inna.shpota.periodicals.exception.DaoException;
import com.inna.shpota.periodicals.util.Assert;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class SubscriptionDaoJdbc implements SubscriptionDao {
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
    private static final String SQL_SELECT_ALL =
            "SELECT * FROM subscription;";
    private final DataSource dataSource;

    public SubscriptionDaoJdbc(DataSource dataSource) {
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
            return getGeneratedId(createStatement);
        } catch (SQLException e) {
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
        } catch (SQLException e) {
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
                            .readerId(resultSet.getLong("reader_id"))
                            .periodicalsId(resultSet.getLong("periodicals_id"))
                            .monthQuantity(resultSet.getInt("month_quantity"))
                            .date(resultSet.getObject("date", LocalDateTime.class))
                            .build();
                }
                return subscription;
            }
        } catch (SQLException e) {
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
        } catch (SQLException e) {
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
                return list;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}