package com.inna.shpota.periodicals.dao;

import com.inna.shpota.periodicals.domain.Payment;
import com.inna.shpota.periodicals.util.Assert;

public interface PaymentDao extends Dao<Payment> {
    default void validate(Payment payment) {
        Assert.notNull(payment, "Payment must not be null");
        Assert.isPositive(payment.getSubscriptionId(), "Subscription ID must be positive");
        Assert.isPositive(payment.getPrice(), "Price must be positive");
    }
}
