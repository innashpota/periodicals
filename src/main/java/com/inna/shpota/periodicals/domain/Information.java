package com.inna.shpota.periodicals.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Information Model Object
 *
 * <P>Various attributes of information, and related behaviour.
 *
 * See {@link #builder()}
 *
 * @author Inna Shpota
 * @version 1.0
 */
public class Information {
    private final String periodicalsName;
    private final String periodicalsPublisher;
    private final BigDecimal periodicalsMonthPrice;
    private final LocalDateTime subscriptionDate;
    private final int monthQuantity;
    private final boolean paymentPaid;

    private Information(
            String periodicalsName,
            String periodicalsPublisher,
            BigDecimal periodicalsMonthPrice,
            LocalDateTime subscriptionDate,
            int monthQuantity,
            boolean paymentPaid
    ) {
        this.periodicalsName = periodicalsName;
        this.periodicalsPublisher = periodicalsPublisher;
        this.periodicalsMonthPrice = periodicalsMonthPrice;
        this.subscriptionDate = subscriptionDate;
        this.monthQuantity = monthQuantity;
        this.paymentPaid = paymentPaid;
    }

    /**
     * Getter for a periodicals name.
     *
     * @return periodicalsName
     */
    public String getPeriodicalsName() {
        return periodicalsName;
    }

    /**
     * Getter for a periodicals publisher.
     *
     * @return periodicalsPublisher
     */
    public String getPeriodicalsPublisher() {
        return periodicalsPublisher;
    }

    /**
     * Getter for a periodicals month price.
     *
     * @return periodicalsMonthPrice
     */
    public BigDecimal getPeriodicalsMonthPrice() {
        return periodicalsMonthPrice;
    }

    /**
     * Getter for a subscription date.
     *
     * @return subscriptionDate
     */
    public LocalDateTime getSubscriptionDate() {
        return subscriptionDate;
    }

    /**
     * Getter for a month quantity.
     *
     * @return monthQuantity
     */
    public int getMonthQuantity() {
        return monthQuantity;
    }

    /**
     * Getter for a payment paid.
     *
     * @return paymentPaid
     */
    public boolean isPaymentPaid() {
        return paymentPaid;
    }

    /**
     * Builders new {@link Builder} for a {@link Information}.
     *
     * @return Builder object
     */
    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Information that = (Information) o;
        return monthQuantity == that.monthQuantity &&
                paymentPaid == that.paymentPaid &&
                Objects.equals(periodicalsName, that.periodicalsName) &&
                Objects.equals(periodicalsPublisher, that.periodicalsPublisher) &&
                Objects.equals(periodicalsMonthPrice, that.periodicalsMonthPrice) &&
                Objects.equals(subscriptionDate, that.subscriptionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                periodicalsName,
                periodicalsPublisher,
                periodicalsMonthPrice,
                subscriptionDate,
                monthQuantity,
                paymentPaid);
    }

    @Override
    public String toString() {
        return "Information{" +
                "periodicalsName='" + periodicalsName + '\'' +
                ", periodicalsPublisher='" + periodicalsPublisher + '\'' +
                ", periodicalsMonthPrice=" + periodicalsMonthPrice +
                ", subscriptionDate=" + subscriptionDate +
                ", monthQuantity=" + monthQuantity +
                ", paymentPaid=" + paymentPaid +
                '}';
    }

    /**
     * Builder for a {@link Information}
     */
    public static class Builder {
        private String periodicalsName;
        private String periodicalsPublisher;
        private BigDecimal periodicalsMonthPrice;
        private LocalDateTime subscriptionDate;
        private int monthQuantity;
        private boolean paymentPaid;

        private Builder() { }

        /**
         * Setter for a periodicals name.
         *
         * @param periodicalsName
         * @return Builder object
         */
        public Builder periodicalsName(String periodicalsName) {
            this.periodicalsName = periodicalsName;
            return this;
        }

        /**
         * Setter for a periodicals publisher.
         *
         * @param periodicalsPublisher
         * @return Builder object
         */
        public Builder periodicalsPublisher(String periodicalsPublisher) {
            this.periodicalsPublisher = periodicalsPublisher;
            return this;
        }

        /**
         * Setter for a periodicals month price.
         *
         * @param periodicalsMonthPrice
         * @return Builder object
         */
        public Builder periodicalsMonthPrice(BigDecimal periodicalsMonthPrice) {
            this.periodicalsMonthPrice = periodicalsMonthPrice;
            return this;
        }

        /**
         * Setter for a subscription date.
         *
         * @param subscriptionDate
         * @return Builder object
         */
        public Builder subscriptionDate(LocalDateTime subscriptionDate) {
            this.subscriptionDate = subscriptionDate;
            return this;
        }

        /**
         * Setter for a subscription month quantity.
         *
         * @param monthQuantity
         * @return Builder object
         */
        public Builder monthQuantity(int monthQuantity) {
            this.monthQuantity = monthQuantity;
            return this;
        }

        /**
         * Setter for a payment paid.
         *
         * @param paymentPaid
         * @return Builder object
         */
        public Builder paymentPaid(boolean paymentPaid) {
            this.paymentPaid = paymentPaid;
            return this;
        }

        /**
         * Builds a new Information.
         *
         * @return Information object
         */
        public Information build() {
            return new Information(
                    periodicalsName,
                    periodicalsPublisher,
                    periodicalsMonthPrice,
                    subscriptionDate,
                    monthQuantity,
                    paymentPaid);
        }
    }
}
