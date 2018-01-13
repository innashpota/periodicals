package com.inna.shpota.periodicals.dao.jdbc;

import com.inna.shpota.periodicals.exception.DaoException;
import org.h2.jdbcx.JdbcDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class AbstractDaoTest {
    JdbcDataSource jdbcDataSource = new JdbcDataSource();

    private final String SQL_DROP_TABLES = "DROP TABLE IF EXISTS subscription_admin;" +
            "DROP TABLE IF EXISTS payment;" +
            "DROP TABLE IF EXISTS subscription;" +
            "DROP TABLE IF EXISTS periodicals;" +
            "DROP TABLE IF EXISTS reader;";
    private final String SQL_CREATE_ADMIN = "CREATE TABLE subscription_admin (" +
            "  id       INT UNSIGNED AUTO_INCREMENT," +
            "  login    VARCHAR(255) NOT NULL," +
            "  password VARCHAR(255) NOT NULL," +
            "  CONSTRAINT admin_login_uk UNIQUE (login)," +
            "  PRIMARY KEY (id)" +
            ");";
    private final String SQL_CREATE_PERIODICALS = "CREATE TABLE periodicals (" +
            "  id          INT UNSIGNED  AUTO_INCREMENT," +
            "  name        VARCHAR(255)  NOT NULL," +
            "  publisher   VARCHAR(255)  NOT NULL," +
            "  month_price DECIMAL(5, 2) NOT NULL," +
            "  CONSTRAINT periodicals_name_uk UNIQUE (name)," +
            "  PRIMARY KEY (id)" +
            ");";
    private final String SQL_CREATE_READER = "CREATE TABLE reader (" +
            "  id          INT UNSIGNED AUTO_INCREMENT," +
            "  last_name   VARCHAR(255) NOT NULL," +
            "  first_name  VARCHAR(255) NOT NULL," +
            "  middle_name VARCHAR(255) NOT NULL," +
            "  email       VARCHAR(255) NOT NULL," +
            "  password    VARCHAR(255) NOT NULL," +
            "  CONSTRAINT reader_email_uk UNIQUE (email)," +
            "  PRIMARY KEY (id)" +
            ");";
    private final String SQL_CREATE_SUBSCRIPTION = "CREATE TABLE subscription (" +
            "  id             INT UNSIGNED AUTO_INCREMENT," +
            "  reader_id      INT UNSIGNED NOT NULL," +
            "  periodicals_id INT UNSIGNED NOT NULL," +
            "  month_quantity INT          NOT NULL," +
            "  date           DATETIME     NOT NULL," +
            "  CONSTRAINT subscription_reader_reader_fk FOREIGN KEY (reader_id) REFERENCES reader (id)" +
            "    ON DELETE NO ACTION," +
            "  CONSTRAINT subscription_periodicals_periodicals_fk FOREIGN KEY (periodicals_id) REFERENCES periodicals (id)" +
            "    ON DELETE NO ACTION," +
            "  PRIMARY KEY (id)" +
            ");";
    private final String SQL_CREATE_PAYMENT = "CREATE TABLE payment (" +
            "  id              INT UNSIGNED  AUTO_INCREMENT," +
            "  subscription_id INT UNSIGNED  NOT NULL," +
            "  price           DECIMAL(7, 2) NOT NULL," +
            "  paid            BIT           NOT NULL DEFAULT 0," +
            "  CONSTRAINT payment_subscription_subscription_fk FOREIGN KEY (subscription_id) REFERENCES subscription (id)" +
            "    ON DELETE NO ACTION," +
            "  PRIMARY KEY (id)" +
            ");";
    private final String SQL_INSERT_ADMIN = "INSERT INTO subscription_admin (login, password) VALUES" +
            "  ('admin1', 'admin1')," +
            "  ('admin2', 'admin2')," +
            "  ('admin3', 'admin3');";
    private final String SQL_INSERT_PERIODICALS = "INSERT INTO periodicals (name, publisher, month_price) VALUES" +
            "  ('VOGUE UA', 'TRK MEDIA FINANCE LIMITED', 99)," +
            "  ('All Retail', 'All Retail', 75)," +
            "  ('HI - TECH PRO', 'ID SoftPress', 28);";
    private final String SQL_INSERT_READER = "INSERT INTO reader (last_name, first_name, middle_name, email, password) VALUES" +
            "  ('Korolyuk', 'Volodymyr', 'Semenovych', 'korolyuk@ok.com', '1')," +
            "  ('Viazovska', 'Maryna', 'Sergiivna', 'viazovska@ok.com', '2')," +
            "  ('Paton', 'Yevgen', 'Oskarovich', 'paton@tv.com', '3')," +
            "  ('Drinfeld', 'Volodymyr', 'Gershonovich', 'drinfeld@tv.com', '4');";
    private final String SQL_INSERT_SUBSCRIPTION = "INSERT INTO subscription (reader_id, periodicals_id, month_quantity, date) VALUES" +
            "  (2, 1, 6, '2017-12-16 13:35:00')," +
            "  (1, 2, 12, '2017-12-14 13:35:00')," +
            "  (1, 3, 3, '2017-12-14 13:35:00')," +
            "  (4, 1, 3, '2017-12-04 13:35:00')," +
            "  (3, 1, 12, '2017-12-04 13:35:00');";
    private final String SQL_INSERT_PAYMENT = "INSERT INTO payment (subscription_id, price, paid) VALUES" +
            "  (1, 594, 1)," +
            "  (2, 900, 1)," +
            "  (3, 84, 1)," +
            "  (4, 297, 1)," +
            "  (5, 1188, 1);";

    void prepareConnection() {
        jdbcDataSource.setURL("jdbc:h2:~/periodicals;MODE=MYSQL");
        jdbcDataSource.setUser("sa");
        jdbcDataSource.setPassword("");
        try (Connection connection = jdbcDataSource.getConnection();
             PreparedStatement dropStatement = connection.prepareStatement(SQL_DROP_TABLES)) {
            dropStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        try (Connection connection = jdbcDataSource.getConnection();
             PreparedStatement createStatement = connection.prepareStatement(
                     SQL_CREATE_ADMIN +
                             SQL_CREATE_PERIODICALS +
                             SQL_CREATE_READER +
                             SQL_CREATE_SUBSCRIPTION +
                             SQL_CREATE_PAYMENT
             )) {
            createStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        try (Connection connection = jdbcDataSource.getConnection();
             PreparedStatement insertStatement = connection.prepareStatement(
                     SQL_INSERT_ADMIN +
                             SQL_INSERT_PERIODICALS +
                             SQL_INSERT_READER +
                             SQL_INSERT_SUBSCRIPTION +
                             SQL_INSERT_PAYMENT
             )) {
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
