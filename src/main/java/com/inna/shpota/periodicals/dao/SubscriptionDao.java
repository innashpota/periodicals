package com.inna.shpota.periodicals.dao;

import com.inna.shpota.periodicals.domain.Payment;
import com.inna.shpota.periodicals.domain.Subscription;
import com.inna.shpota.periodicals.util.Assert;

import java.math.BigDecimal;

public interface SubscriptionDao extends Dao<Subscription> {
    default void validate(Subscription subscription) {
        Assert.notNull(subscription, "Subscription must not be null");
        Assert.isPositive(subscription.getReaderId(), "Reader ID must be positive");
        Assert.isPositive(subscription.getPeriodicalsId(), "Periodicals ID must be positive");
        Assert.isPositive(subscription.getMonthQuantity(), "Month quantity must be positive");
        Assert.notNull(subscription.getDate(), "Date must not be null");
    }

    long createPaymentBySubscription(Subscription subscription, BigDecimal monthPrice);
}
