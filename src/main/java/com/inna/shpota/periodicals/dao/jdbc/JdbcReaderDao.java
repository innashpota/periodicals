package com.inna.shpota.periodicals.dao.jdbc;

import com.inna.shpota.periodicals.dao.ReaderDao;
import com.inna.shpota.periodicals.domain.Reader;
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

public class JdbcReaderDao implements ReaderDao {
    private static final String SQL_INSERT_READER =
            "INSERT INTO reader (last_name, first_name, middle_name, email, password) VALUES (?, ?, ?, ?, ?);";
    private static final String SQL_DELETE_READER =
            "DELETE FROM reader WHERE id = ?;";
    private static final String SQL_SELECT_READER =
            "SELECT last_name, first_name, middle_name, email, password FROM reader WHERE id = ?;";
    private static final String SQL_UPDATE_READER =
            "UPDATE reader " +
                    "SET last_name = ?, first_name = ?, middle_name = ?, email = ?, password = ? " +
                    "WHERE id = ?;";
    private static final String SQL_SELECT_ALL =
            "SELECT * FROM reader;";
    private final DataSource dataSource;

    public JdbcReaderDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public long create(Reader reader) {
        validate(reader);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement createStatement = connection.prepareStatement(
                     SQL_INSERT_READER,
                     RETURN_GENERATED_KEYS
             )) {
            createStatement.setString(1, reader.getLastName());
            createStatement.setString(2, reader.getFirstName());
            createStatement.setString(3, reader.getMiddleName());
            createStatement.setString(4, reader.getEmail());
            createStatement.setString(5, reader.getPassword());
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
                     SQL_DELETE_READER
             )) {
            deleteStatement.setLong(1, id);
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Reader getById(long id) {
        Assert.isPositive(id, "ID must be positive");
        try (Connection connection = dataSource.getConnection();
             PreparedStatement selectStatement = connection.prepareStatement(
                     SQL_SELECT_READER
             )) {
            selectStatement.setLong(1, id);
            try (ResultSet resultSet = selectStatement.executeQuery()) {
                Reader reader = null;
                if (resultSet.next()) {
                    reader = Reader.builder()
                            .id(id)
                            .lastName(resultSet.getString("last_name"))
                            .firstName(resultSet.getString("first_name"))
                            .middleName(resultSet.getString("middle_name"))
                            .email(resultSet.getString("email"))
                            .password(resultSet.getString("password"))
                            .build();
                }
                return reader;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Reader reader) {
        validate(reader);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement updateStatement = connection.prepareStatement(
                     SQL_UPDATE_READER
             )) {
            updateStatement.setString(1, reader.getLastName());
            updateStatement.setString(2, reader.getFirstName());
            updateStatement.setString(3, reader.getLastName());
            updateStatement.setString(4, reader.getEmail());
            updateStatement.setString(5, reader.getPassword());
            updateStatement.setLong(6, reader.getId());
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Reader> getAll() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement allStatement = connection.prepareStatement(
                     SQL_SELECT_ALL
             )) {
            try (ResultSet resultSet = allStatement.executeQuery()) {
                List<Reader> list = new ArrayList<>();
                while (resultSet.next()) {
                    list.add(Reader.builder()
                            .id(resultSet.getLong("id"))
                            .lastName(resultSet.getString("last_name"))
                            .firstName(resultSet.getString("first_name"))
                            .middleName(resultSet.getString("middle_name"))
                            .email(resultSet.getString("email"))
                            .password(resultSet.getString("password"))
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
