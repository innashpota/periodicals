package com.inna.shpota.periodicals.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface Dao<E> {
    long create(E variable);

    void delete(long id);

    E getById(long id);

    void update(E variable);

    List<E> getAll();

    default long getGeneratedId(PreparedStatement createStatement) throws SQLException {
        try (ResultSet resultSet = createStatement.getGeneratedKeys()) {
            long id = -1;
            if (resultSet.next()) {
                id = resultSet.getLong("id");
            }
            return id;
        }
    }
}
