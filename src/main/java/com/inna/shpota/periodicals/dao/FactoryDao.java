package com.inna.shpota.periodicals.dao;

import com.inna.shpota.periodicals.dao.jdbc.*;

import javax.sql.DataSource;

public class FactoryDao {
    public static AdminDao createAdminDao(DataSource dataSource) {
        return new AdminDaoJdbc(dataSource);
    }

    public static PeriodicalsDao createPeriodicalsDao(DataSource dataSource) {
        return new PeriodicalsDaoJdbc(dataSource);
    }

    public static ReaderDao createReaderDao(DataSource dataSource) {
        return new ReaderDaoJdbc(dataSource);
    }

    public static SubscriptionDao createSubscriptionDao(DataSource dataSource) {
        return new SubscriptionDaoJdbc(dataSource);
    }

    public static PaymentDao createPaymentDao(DataSource dataSource) {
        return new PaymentDaoJdbc(dataSource);
    }
}
