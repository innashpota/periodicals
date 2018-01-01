package com.inna.shpota.periodicals.dao.jdbc;

import com.inna.shpota.periodicals.dao.AdminDao;
import com.inna.shpota.periodicals.domain.Admin;
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

public class JdbcAdminDao implements AdminDao {
    private final static Logger LOGGER = Logger.getLogger(JdbcAdminDao.class);
    private static final String SQL_INSERT_ADMIN =
            "INSERT INTO subscription_admin (login, password) VALUES (?, ?);";
    private static final String SQL_DELETE_ADMIN =
            "DELETE FROM subscription_admin WHERE id = ?;";
    private static final String SQL_SELECT_ADMIN =
            "SELECT login, password FROM subscription_admin WHERE id = ?;";
    private static final String SQL_SELECT_ADMIN_BY_LOGIN_AND_PASS =
            "SELECT * FROM subscription_admin WHERE login = ? AND password = ?;";
    private static final String SQL_UPDATE_ADMIN =
            "UPDATE subscription_admin SET login = ?, password = ? WHERE id = ?;";
    private static final String SQL_SELECT_ALL =
            "SELECT * FROM subscription_admin;";
    private final DataSource dataSource;

    public JdbcAdminDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public long create(Admin admin) {
        validate(admin);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement createStatement = connection.prepareStatement(
                     SQL_INSERT_ADMIN,
                     RETURN_GENERATED_KEYS
             )) {
            createStatement.setString(1, admin.getLogin());
            createStatement.setString(2, admin.getPassword());
            createStatement.executeUpdate();
            LOGGER.info("Create new admin: " + admin);
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
                     SQL_DELETE_ADMIN
             )) {
            deleteStatement.setLong(1, id);
            deleteStatement.executeUpdate();
            LOGGER.info("Delete admin by ID: " + id);
        } catch (SQLException e) {
            LOGGER.error("SQLException occurred in JdbcPeriodicalsDao", e);
            throw new DaoException(e);
        }
    }

    @Override
    public Admin getById(long id) {
        Assert.isPositive(id, "ID must be positive");
        try (Connection connection = dataSource.getConnection();
             PreparedStatement selectStatement = connection.prepareStatement(
                     SQL_SELECT_ADMIN
             )) {
            selectStatement.setLong(1, id);
            try (ResultSet resultSet = selectStatement.executeQuery()) {
                Admin admin = null;
                if (resultSet.next()) {
                    admin = new Admin(
                            id,
                            resultSet.getString("login"),
                            resultSet.getString("password"));
                }
                LOGGER.info("Get by ID admin: " + admin);
                return admin;
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException occurred in JdbcPeriodicalsDao", e);
            throw new DaoException(e);
        }
    }

    @Override
    public Admin getByLoginAndPassword(String login, String password) {
        Assert.notEmpty(login, "Login must not be empty");
        Assert.notEmpty(password, "Password must not be empty");
        try (Connection connection = dataSource.getConnection();
             PreparedStatement selectStatement = connection.prepareStatement(
                     SQL_SELECT_ADMIN_BY_LOGIN_AND_PASS
             )) {
            selectStatement.setString(1, login);
            selectStatement.setString(2, password);
            try (ResultSet resultSet = selectStatement.executeQuery()) {
                Admin admin = null;
                if (resultSet.next()) {
                    admin = new Admin(resultSet.getLong("id"), login, password);
                }
                LOGGER.info("Get by login and password: " + admin);
                return admin;
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException occurred in JdbcPeriodicalsDao", e);
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Admin admin) {
        validate(admin);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement updateStatement = connection.prepareStatement(
                     SQL_UPDATE_ADMIN
             )) {
            updateStatement.setString(1, admin.getLogin());
            updateStatement.setString(2, admin.getPassword());
            updateStatement.setLong(3, admin.getId());
            updateStatement.executeUpdate();
            LOGGER.info("Update admin: " + admin);
        } catch (SQLException e) {
            LOGGER.error("SQLException occurred in JdbcPeriodicalsDao", e);
            throw new DaoException(e);
        }
    }

    @Override
    public List<Admin> getAll() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement allStatement = connection.prepareStatement(
                     SQL_SELECT_ALL
             )) {
            try (ResultSet resultSet = allStatement.executeQuery()) {
                List<Admin> list = new ArrayList<>();
                while (resultSet.next()) {
                    list.add(new Admin(
                            resultSet.getLong("id"),
                            resultSet.getString("login"),
                            resultSet.getString("password")
                    ));
                }
                LOGGER.info("Get all admins: " + list);
                return list;
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException occurred in JdbcPeriodicalsDao", e);
            throw new DaoException(e);
        }
    }
}
