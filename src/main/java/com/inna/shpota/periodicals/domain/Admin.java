package com.inna.shpota.periodicals.domain;

import java.util.Objects;

/**
 * Admin Model Object
 *
 * <P>Various attributes of admin, and related behaviour.
 *
 * See {@link #Admin(String login, String password)} or
 * {@link #Admin(long id, String login, String password)} for more information
 *
 * @author Inna Shpota
 * @version 1.0
 */
public class Admin {
    private final long id;
    private final String login;
    private final String password;

    /**
     * Constructor for all parameters.
     *
     * @param id       ID of the admin.
     * @param login    (required) Login of the admin.
     * @param password (required) Password of the admin.
     */
    public Admin(long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    /**
     * Constructor.
     *
     * @param login    (required) Login of the admin.
     * @param password (required) Password of the admin.
     */
    public Admin(String login, String password) {
        this(-1, login, password);
    }

    /**
     * Getter for an id.
     *
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * Getter for a login.
     *
     * @return login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Getter for a password.
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return id == admin.id &&
                Objects.equals(login, admin.login) &&
                Objects.equals(password, admin.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
