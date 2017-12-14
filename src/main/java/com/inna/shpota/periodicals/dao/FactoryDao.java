package com.inna.shpota.periodicals.dao;

import com.inna.shpota.periodicals.dao.jdbc.AdminDaoJdbc;
import com.inna.shpota.periodicals.dao.jdbc.PeriodicalsDaoJdbc;

import javax.sql.DataSource;

public class FactoryDao {
    public static AdminDao createAdminDao(DataSource dataSource) {
        return new AdminDaoJdbc(dataSource);
    }

    public static PeriodicalsDao createPeriodicalsDao(DataSource dataSource) {
        return new PeriodicalsDaoJdbc(dataSource);
    }
}
