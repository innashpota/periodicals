package com.inna.shpota.periodicals.dao.jdbc;

import com.inna.shpota.periodicals.dao.PaymentDao;
import com.inna.shpota.periodicals.domain.Payment;
import com.inna.shpota.periodicals.exception.DaoException;
import com.inna.shpota.periodicals.util.Assert;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class PaymentDaoJdbc implements PaymentDao {
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

    public PaymentDaoJdbc(DataSource dataSource) {
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
                     SQL_DELETE_PAYMENT
             )) {
            deleteStatement.setLong(1, id);
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
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
                return payment;
            }
        } catch (SQLException e) {
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
        } catch (SQLException e) {
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
                return list;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
