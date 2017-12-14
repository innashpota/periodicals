package com.inna.shpota.periodicals.domain;

import java.math.BigDecimal;

public class Payment {
    private long id;
    private final long subscriptionId;
    private final BigDecimal price;
    private final boolean paid;

    public Payment(long id, long subscriptionId, BigDecimal price, boolean paid) {
        this(subscriptionId, price, paid);
        this.id = id;
    }

    public Payment(long subscriptionId, BigDecimal price, boolean paid) {
        this.subscriptionId = subscriptionId;
        this.price = price;
        this.paid = paid;
    }

    public long getId() {
        return id;
    }

    public long getSubscriptionId() {
        return subscriptionId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isPaid() {
        return paid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payment payment = (Payment) o;

        if (id != payment.id) return false;
        if (subscriptionId != payment.subscriptionId) return false;
        if (paid != payment.paid) return false;
        return price != null ? price.equals(payment.price) : payment.price == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (subscriptionId ^ (subscriptionId >>> 32));
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (paid ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", subscriptionId=" + subscriptionId +
                ", price=" + price +
                ", paid=" + paid +
                '}';
    }
}
