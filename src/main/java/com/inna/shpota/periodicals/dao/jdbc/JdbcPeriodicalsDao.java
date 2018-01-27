package com.inna.shpota.periodicals.dao.jdbc;

import com.inna.shpota.periodicals.dao.PeriodicalsDao;
import com.inna.shpota.periodicals.domain.Periodicals;
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

public class JdbcPeriodicalsDao implements PeriodicalsDao {
    private final static Logger LOGGER = Logger.getLogger(JdbcPeriodicalsDao.class);
    private final String SQL_INSERT_PERIODICALS =
            "INSERT INTO periodicals (name, publisher, month_price, deleted) VALUES (?, ?, ?, ?);";
    private static final String SQL_DELETE_PERIODICALS =
            "UPDATE periodicals SET deleted = ? WHERE id = ?;";
    private static final String SQL_SELECT_PERIODICALS =
            "SELECT name, publisher, month_price, deleted FROM periodicals WHERE id = ?;";
    private static final String SQL_UPDATE_PERIODICALS =
            "UPDATE periodicals SET name = ?, publisher = ?, month_price = ?, deleted = ? WHERE id = ?;";
    private static final String SQL_SELECT_ALL =
            "SELECT * FROM periodicals WHERE deleted = 0;";
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
            createStatement.setInt(4, periodicals.isDeleted() ? 1 : 0);
            createStatement.executeUpdate();
            LOGGER.info("Create new periodical: " + periodicals);
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
                     SQL_DELETE_PERIODICALS
             )) {
            deleteStatement.setInt(1, 1);
            deleteStatement.setLong(2, id);
            deleteStatement.executeUpdate();
            LOGGER.info("Delete periodical by ID: " + id);
        } catch (SQLException e) {
            LOGGER.error("SQLException occurred in JdbcPeriodicalsDao", e);
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
                            resultSet.getBigDecimal("month_price"),
                            resultSet.getInt("deleted") == 1
                    );
                }
                LOGGER.info("Get by ID periodical: " + periodicals);
                return periodicals;
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException occurred in JdbcPeriodicalsDao", e);
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
            updateStatement.setInt(4, periodicals.isDeleted() ? 1 : 0);
            updateStatement.setLong(5, periodicals.getId());
            updateStatement.executeUpdate();
            LOGGER.info("Update periodical: " + periodicals);
        } catch (SQLException e) {
            LOGGER.error("SQLException occurred in JdbcPeriodicalsDao", e);
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
                            resultSet.getBigDecimal("month_price"),
                            resultSet.getInt("deleted") == 1
                    ));
                }
                LOGGER.info("Get all periodicals: " + list);
                return list;
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException occurred in JdbcPeriodicalsDao", e);
            throw new DaoException(e);
        }
    }
}
