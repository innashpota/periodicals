package com.inna.shpota.periodicals.domain;

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
    private long id;
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

        if (id != reader.id) return false;
        if (lastName != null ? !lastName.equals(reader.lastName) : reader.lastName != null) return false;
        if (firstName != null ? !firstName.equals(reader.firstName) : reader.firstName != null) return false;
        if (middleName != null ? !middleName.equals(reader.middleName) : reader.middleName != null) return false;
        if (email != null ? !email.equals(reader.email) : reader.email != null) return false;
        return password != null ? password.equals(reader.password) : reader.password == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
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
