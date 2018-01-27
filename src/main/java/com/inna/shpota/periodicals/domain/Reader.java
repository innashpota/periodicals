package com.inna.shpota.periodicals.domain;

import java.util.Objects;

/**
 * Reader Model Object
 *
 * <P>Various attributes of reader, and related behaviour.
 *
 * See {@link #builder()}
 *
 * @author Inna Shpota
 * @version 1.0
 */
public class Reader {
    private final long id;
    private final String lastName;
    private final String firstName;
    private final String middleName;
    private final String email;
    private final String password;

    private Reader(
            long id,
            String lastName,
            String firstName,
            String middleName,
            String email,
            String password) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.email = email;
        this.password = password;
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
     * Getter for a last name.
     *
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Getter for a first name.
     *
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Getter for a middle name.
     *
     * @return middleName
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Getter for an email.
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Getter for a password.
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Builders new {@link Builder} for a {@link Reader}.
     *
     * @return Builder object
     */
    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reader reader = (Reader) o;
        return id == reader.id &&
                Objects.equals(lastName, reader.lastName) &&
                Objects.equals(firstName, reader.firstName) &&
                Objects.equals(middleName, reader.middleName) &&
                Objects.equals(email, reader.email) &&
                Objects.equals(password, reader.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastName, firstName, middleName, email, password);
    }

    @Override
    public String toString() {
        return "Reader{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    /**
     * Builder for a {@link Reader}
     */
    public static class Builder {
        private long id;
        private String lastName;
        private String firstName;
        private String middleName;
        private String email;
        private String password;

        private Builder() { }

        /**
         * Setter for an id.
         *
         * @param id ID of the reader.
         * @return Builder object
         */
        public Builder id(long id) {
            this.id = id;
            return this;
        }

        /**
         * Setter for a last name.
         *
         * @param lastName (required) Last name of the reader.
         * @return Builder object
         */
        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        /**
         * Setter for a first name.
         *
         * @param firstName (required) First name of the reader.
         * @return Builder object
         */
        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        /**
         * Setter for a middle name.
         *
         * @param middleName (required) Middle name of the reader.
         * @return Builder object
         */
        public Builder middleName(String middleName) {
            this.middleName = middleName;
            return this;
        }

        /**
         * Setter for an email.
         *
         * @param email (required) Email of the reader.
         * @return Builder object
         */
        public Builder email(String email) {
            this.email = email;
            return this;
        }

        /**
         * Setter for a password.
         *
         * @param password (required) Password of the reader.
         * @return Builder object
         */
        public Builder password(String password) {
            this.password = password;
            return this;
        }

        /**
         * Builds a new Reader.
         *
         * @return Reader object
         */
        public Reader build() {
            return new Reader(id, lastName, firstName, middleName, email, password);
        }
    }
}
