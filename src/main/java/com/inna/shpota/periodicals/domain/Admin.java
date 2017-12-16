package com.inna.shpota.periodicals.domain;

/**
 * Admin Model Object
 *
 * <P>Various attributes of admin, and related behaviour.
 *
 * See {@link #Admin(String, String)} or {@link #Admin(long, String, String)} for more information
 *
 * @author Inna Shpota
 * @version 1.0
 */
public class Admin {
    private long id;
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
        this(login, password);
        this.id = id;
    }

    /**
     * Constructor.
     *
     * @param login    (required) Login of the admin.
     * @param password (required) Password of the admin.
     */
    public Admin(String login, String password) {
        this.login = login;
        this.password = password;
    }

    /**
     * Getter for an id.
     *
     * @return id passed to the constructor.
     */
    public long getId() {
        return id;
    }

    /**
     * Getter for a login.
     *
     * @return login passed to the constructor.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Getter for a password.
     *
     * @return password passed to the constructor.
     */
    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Admin admin = (Admin) o;

        if (id != admin.id) return false;
        if (login != null ? !login.equals(admin.login) : admin.login != null) return false;
        return password != null ? password.equals(admin.password) : admin.password == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
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
