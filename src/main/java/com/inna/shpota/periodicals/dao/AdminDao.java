package com.inna.shpota.periodicals.dao;

import com.inna.shpota.periodicals.domain.Admin;
import com.inna.shpota.periodicals.util.Assert;

public interface AdminDao extends Dao<Admin> {
    default void validate(Admin admin) {
        Assert.notNull(admin, "Admin must not be null");
        Assert.notEmpty(admin.getLogin(), "Login must not be empty");
        Assert.notEmpty(admin.getPassword(), "Password must not be empty");
    }

    long getByLoginAndPassword(String login, String password);
}
