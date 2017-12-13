package com.inna.shpota.periodicals.domain;

public class Payment {
    private long id;
    private final long subscriptionId;
    private final double price;
    private final boolean paid;

    public Payment(long id, long subscriptionId, double price, boolean paid) {
        this(subscriptionId, price, paid);
        this.id = id;
    }

    public Payment(long subscriptionId, double price, boolean paid) {
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

    public double getPrice() {
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
        if (Double.compare(payment.price, price) != 0) return false;
        return paid == payment.paid;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (subscriptionId ^ (subscriptionId >>> 32));
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
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
