package com.inna.shpota.periodicals.dao;

import com.inna.shpota.periodicals.domain.Reader;
import com.inna.shpota.periodicals.util.Assert;

public interface ReaderDao extends Dao<Reader> {
    default void validate(Reader reader) {
        Assert.notNull(reader, "Reader must not be null");
        Assert.notEmpty(reader.getLastName(), "Last name must not be empty");
        Assert.notEmpty(reader.getFirstName(), "First name must not be empty");
        Assert.notEmpty(reader.getMiddleName(), "Middle name must not be empty");
        Assert.notEmpty(reader.getEmail(), "Email must not be empty");
        Assert.notEmpty(reader.getPassword(), "Password must not be empty");
    }

    Reader getByEmailAndPassword(String email, String password);

    Reader getByEmail(String email);
}
