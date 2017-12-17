package com.inna.shpota.periodicals.dao.jdbc;

import com.inna.shpota.periodicals.exception.DaoException;
import org.h2.jdbcx.JdbcDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class AbstractDaoTest {
    JdbcDataSource jdbcDataSource = new JdbcDataSource();

    private final String SQL_DROP_TABLES = "DROP TABLE IF EXISTS subscription_admin;\n" +
            "DROP TABLE IF EXISTS payment;\n" +
            "DROP TABLE IF EXISTS subscription;\n" +
            "DROP TABLE IF EXISTS periodicals;\n" +
            "DROP TABLE IF EXISTS reader;";
    private final String SQL_CREATE_ADMIN = "CREATE TABLE subscription_admin (\n" +
            "  id       INT UNSIGNED AUTO_INCREMENT,\n" +
            "  login    VARCHAR(255) NOT NULL,\n" +
            "  password VARCHAR(255) NOT NULL,\n" +
            "  CONSTRAINT admin_login_uk UNIQUE (login),\n" +
            "  PRIMARY KEY (id)\n" +
            ");";
    private final String SQL_CREATE_PERIODICALS = "CREATE TABLE periodicals (\n" +
            "  id          INT UNSIGNED  AUTO_INCREMENT,\n" +
            "  name        VARCHAR(255)  NOT NULL,\n" +
            "  publisher   VARCHAR(255)  NOT NULL,\n" +
            "  month_price DECIMAL(5, 2) NOT NULL,\n" +
            "  CONSTRAINT periodicals_name_uk UNIQUE (name),\n" +
            "  PRIMARY KEY (id)\n" +
            ");";
    private final String SQL_CREATE_READER = "CREATE TABLE reader (\n" +
            "  id          INT UNSIGNED AUTO_INCREMENT,\n" +
            "  last_name   VARCHAR(255) NOT NULL,\n" +
            "  first_name  VARCHAR(255) NOT NULL,\n" +
            "  middle_name VARCHAR(255) NOT NULL,\n" +
            "  email       VARCHAR(255) NOT NULL,\n" +
            "  password    VARCHAR(255) NOT NULL,\n" +
            "  CONSTRAINT reader_email_uk UNIQUE (email),\n" +
            "  PRIMARY KEY (id)\n" +
            ");";
    private final String SQL_CREATE_SUBSCRIPTION = "CREATE TABLE subscription (\n" +
            "  id             INT UNSIGNED AUTO_INCREMENT,\n" +
            "  reader_id      INT UNSIGNED NOT NULL,\n" +
            "  periodicals_id INT UNSIGNED NOT NULL,\n" +
            "  month_quantity INT          NOT NULL,\n" +
            "  date           DATETIME     NOT NULL,\n" +
            "  CONSTRAINT subscription_reader_reader_fk FOREIGN KEY (reader_id) REFERENCES reader (id)\n" +
            "    ON DELETE NO ACTION,\n" +
            "  CONSTRAINT subscription_periodicals_periodicals_fk FOREIGN KEY (periodicals_id) REFERENCES periodicals (id)\n" +
            "    ON DELETE NO ACTION,\n" +
            "  PRIMARY KEY (id)\n" +
            ");";
    private final String SQL_CREATE_PAYMENT = "CREATE TABLE payment (\n" +
            "  id              INT UNSIGNED  AUTO_INCREMENT,\n" +
            "  subscription_id INT UNSIGNED  NOT NULL,\n" +
            "  price           DECIMAL(7, 2) NOT NULL,\n" +
            "  paid            BIT           NOT NULL DEFAULT 0,\n" +
            "  CONSTRAINT payment_subscription_subscription_fk FOREIGN KEY (subscription_id) REFERENCES subscription (id)\n" +
            "    ON DELETE NO ACTION,\n" +
            "  PRIMARY KEY (id)\n" +
            ");";
    private final String SQL_INSERT_ADMIN = "INSERT INTO subscription_admin (login, password) VALUES\n" +
            "  ('admin1', 'admin1'),\n" +
            "  ('admin2', 'admin2'),\n" +
            "  ('admin3', 'admin3');";
    private final String SQL_INSERT_PERIODICALS = "INSERT INTO periodicals (name, publisher, month_price) VALUES\n" +
            "  ('VOGUE UA', 'TRK MEDIA FINANCE LIMITED', 99),\n" +
            "  ('All Retail', 'All Retail', 75),\n" +
            "  ('HI - TECH PRO', 'ID SoftPress', 28);";
    private final String SQL_INSERT_READER = "INSERT INTO reader (last_name, first_name, middle_name, email, password) VALUES\n" +
            "  ('Korolyuk', 'Volodymyr', 'Semenovych', 'korolyuk@ok.com', '1'),\n" +
            "  ('Viazovska', 'Maryna', 'Sergiivna', 'viazovska@ok.com', '2'),\n" +
            "  ('Paton', 'Yevgen', 'Oskarovich', 'paton@tv.com', '3'),\n" +
            "  ('Drinfeld', 'Volodymyr', 'Gershonovich', 'drinfeld@tv.com', '4');";
    private final String SQL_INSERT_SUBSCRIPTION = "INSERT INTO subscription (reader_id, periodicals_id, month_quantity, date) VALUES\n" +
            "  (2, 1, 6, '2017-12-16 13:35:00'),\n" +
            "  (1, 2, 12, '2017-12-14 13:35:00'),\n" +
            "  (1, 3, 3, '2017-12-14 13:35:00'),\n" +
            "  (4, 1, 3, '2017-12-04 13:35:00'),\n" +
            "  (3, 1, 12, '2017-12-04 13:35:00');";
    private final String SQL_INSERT_PAYMENT = "INSERT INTO payment (subscription_id, price, paid) VALUES\n" +
            "  (1, 594, 1),\n" +
            "  (2, 900, 1),\n" +
            "  (3, 84, 1),\n" +
            "  (4, 297, 1),\n" +
            "  (5, 1188, 1);";

    public void prepareConnection() {
        jdbcDataSource.setURL("jdbc:h2:~/periodicals;MODE=MYSQL");
        jdbcDataSource.setUser("sa");
        jdbcDataSource.setPassword("");
        try (Connection connection = jdbcDataSource.getConnection();
             PreparedStatement dropStatement = connection.prepareStatement(SQL_DROP_TABLES);
             PreparedStatement createStatement = connection.prepareStatement(
                     SQL_CREATE_ADMIN +
                             SQL_CREATE_PERIODICALS +
                             SQL_CREATE_READER +
                             SQL_CREATE_SUBSCRIPTION +
                             SQL_CREATE_PAYMENT
             );
             PreparedStatement insertStatement = connection.prepareStatement(
                     SQL_INSERT_ADMIN +
                             SQL_INSERT_PERIODICALS +
                             SQL_INSERT_READER +
                             SQL_INSERT_SUBSCRIPTION +
                             SQL_INSERT_PAYMENT
             )) {
            dropStatement.executeUpdate();
            createStatement.executeUpdate();
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
