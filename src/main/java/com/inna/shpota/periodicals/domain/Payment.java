package com.inna.shpota.periodicals.domain;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Payment Model Object
 *
 * <P>Various attributes of payment, and related behaviour.
 *
 * <P>Note that {@link BigDecimal} is used to model the price - not double or float.
 * See {@link #Payment(long subscriptionId, BigDecimal price, boolean paid)} or
 * {@link #Payment(long id, long subscriptionId, BigDecimal price, boolean paid)} for more information
 *
 * @author Inna Shpota
 * @version 1.0
 */
public class Payment {
    private final long id;
    private final long subscriptionId;
    private final BigDecimal price;
    private final boolean paid;

    /**
     * Constructor for all parameters.
     *
     * @param id             ID of the payment.
     * @param subscriptionId (required) ID of the subscription.
     * @param price          (automatically calculated) Price of the all months.
     * @param paid           (optional) Paid or not paid subscription. Default value false.
     */
    public Payment(long id, long subscriptionId, BigDecimal price, boolean paid) {
        this.id = id;
        this.subscriptionId = subscriptionId;
        this.price = price;
        this.paid = paid;
    }

    /**
     * Constructor.
     *
     * @param subscriptionId (required) ID of the subscription.
     * @param price          (automatically calculated) Price of the all months.
     * @param paid           (optional) Paid or not paid subscription. Default value false.
     */
    public Payment(long subscriptionId, BigDecimal price, boolean paid) {
        this(-1, subscriptionId, price, paid);
    }

    /**
     * Getter for an id.
     *
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * Getter for a subscription id.
     *
     * @return subscriptionId
     */
    public long getSubscriptionId() {
        return subscriptionId;
    }

    /**
     * Getter for a price.
     *
     * @return price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Getter for a paid.
     *
     * @return paid
     */
    public boolean isPaid() {
        return paid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return id == payment.id &&
                subscriptionId == payment.subscriptionId &&
                paid == payment.paid &&
                Objects.equals(price, payment.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subscriptionId, price, paid);
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
