package com.inna.shpota.periodicals.domain;

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

    public long getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

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

    public static class Builder {
        private long id;
        private String lastName;
        private String firstName;
        private String middleName;
        private String email;
        private String password;

        private Builder() { }

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder middleName(String middleName) {
            this.middleName = middleName;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Reader build() {
            return new Reader(id, lastName, firstName, middleName, email, password);
        }
    }
}
