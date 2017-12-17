package com.inna.shpota.periodicals.dao.jdbc;

import com.inna.shpota.periodicals.dao.PeriodicalsDao;
import com.inna.shpota.periodicals.domain.Periodicals;
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

public class JdbcPeriodicalsDao implements PeriodicalsDao {
    private final String SQL_INSERT_PERIODICALS =
            "INSERT INTO periodicals (name, publisher, month_price) VALUES (?, ?, ?);";
    private static final String SQL_DELETE_PERIODICALS =
            "DELETE FROM periodicals WHERE id = ?;";
    private static final String SQL_SELECT_PERIODICALS =
            "SELECT name, publisher, month_price FROM periodicals WHERE id = ?;";
    private static final String SQL_UPDATE_PERIODICALS =
            "UPDATE periodicals SET name = ?, publisher = ?, month_price = ? WHERE id = ?;";
    private static final String SQL_SELECT_ALL =
            "SELECT * FROM periodicals;";
    private final DataSource dataSource;

    public JdbcPeriodicalsDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public long create(Periodicals periodicals) {
        validate(periodicals);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement createStatement = connection.prepareStatement(
                     SQL_INSERT_PERIODICALS,
                     RETURN_GENERATED_KEYS
             )) {
            createStatement.setString(1, periodicals.getName());
            createStatement.setString(2, periodicals.getPublisher());
            createStatement.setBigDecimal(3, periodicals.getMonthPrice());
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
                     SQL_DELETE_PERIODICALS
             )) {
            deleteStatement.setLong(1, id);
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Periodicals getById(long id) {
        Assert.isPositive(id, "ID must be positive");
        try (Connection connection = dataSource.getConnection();
             PreparedStatement selectStatement = connection.prepareStatement(
                     SQL_SELECT_PERIODICALS
             )) {
            selectStatement.setLong(1, id);
            try (ResultSet resultSet = selectStatement.executeQuery()) {
                Periodicals periodicals = null;
                if (resultSet.next()) {
                    periodicals = new Periodicals(
                            id,
                            resultSet.getString("name"),
                            resultSet.getString("publisher"),
                            resultSet.getBigDecimal("month_price")
                    );
                }
                return periodicals;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Periodicals periodicals) {
        validate(periodicals);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement updateStatement = connection.prepareStatement(
                     SQL_UPDATE_PERIODICALS
             )) {
            updateStatement.setString(1, periodicals.getName());
            updateStatement.setString(2, periodicals.getPublisher());
            updateStatement.setBigDecimal(3, periodicals.getMonthPrice());
            updateStatement.setLong(4, periodicals.getId());
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Periodicals> getAll() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement allStatement = connection.prepareStatement(
                     SQL_SELECT_ALL
             )) {
            try (ResultSet resultSet = allStatement.executeQuery()) {
                List<Periodicals> list = new ArrayList<>();
                while (resultSet.next()) {
                    list.add(new Periodicals(
                            resultSet.getLong("id"),
                            resultSet.getString("name"),
                            resultSet.getString("publisher"),
                            resultSet.getBigDecimal("month_price")
                    ));
                }
                return list;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
