package com.inna.shpota.periodicals.dao;

import com.inna.shpota.periodicals.domain.Periodicals;
import com.inna.shpota.periodicals.util.Assert;

public interface PeriodicalsDao extends Dao<Periodicals> {
    default void validate(Periodicals periodicals) {
        Assert.notNull(periodicals, "Periodicals must not be null");
        Assert.notEmpty(periodicals.getName(), "Name must not be empty");
        Assert.notEmpty(periodicals.getPublisher(), "Publisher must not be empty");
        Assert.isPositive(periodicals.getMonthPrice(), "Month price must be positive");
    }
}
