package com.inna.shpota.periodicals.domain;

import java.time.LocalDateTime;

/**
 * Subscription Model Object
 *
 * <P>Various attributes of subscription, and related behaviour.
 *
 * See {@link #builder()}
 *
 * @author Inna Shpota
 * @version 1.0
 */
public class Subscription {
    private long id;
    private final long readerId;
    private final long periodicalsId;
    private final int monthQuantity;
    private final LocalDateTime date;

    private Subscription(
            long id,
            long readerId,
            long periodicalsId,
            int monthQuantity,
            LocalDateTime date) {
        this.id = id;
        this.readerId = readerId;
        this.periodicalsId = periodicalsId;
        this.monthQuantity = monthQuantity;
        this.date = date;
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
     * Getter for a reader id.
     *
     * @return readerId
     */
    public long getReaderId() {
        return readerId;
    }

    /**
     * Getter for a periodicals id.
     *
     * @return periodicalsId
     */
    public long getPeriodicalsId() {
        return periodicalsId;
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
     * Getter for a date.
     *
     * @return date
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Builders new {@link Builder} for a {@link Subscription}.
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

        Subscription that = (Subscription) o;

        if (id != that.id) return false;
        if (readerId != that.readerId) return false;
        if (periodicalsId != that.periodicalsId) return false;
        if (monthQuantity != that.monthQuantity) return false;
        return date != null ? date.equals(that.date) : that.date == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (readerId ^ (readerId >>> 32));
        result = 31 * result + (int) (periodicalsId ^ (periodicalsId >>> 32));
        result = 31 * result + monthQuantity;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", readerId=" + readerId +
                ", periodicalsId=" + periodicalsId +
                ", monthQuantity=" + monthQuantity +
                ", date=" + date +
                '}';
    }

    public static class Builder {
        private long id;
        private long readerId;
        private long periodicalsId;
        private int monthQuantity;
        private LocalDateTime date;

        private Builder() { }

        /**
         * Setter for an id.
         *
         * @param id ID of the subscription.
         * @return Builder object
         */
        public Builder id(long id) {
            this.id = id;
            return this;
        }

        /**
         * Setter for an reader id.
         *
         * @param readerId (required) ID of the reader.
         * @return Builder object
         */
        public Builder readerId(long readerId) {
            this.readerId = readerId;
            return this;
        }

        /**
         * Setter for an periodicals id.
         *
         * @param periodicalsId (required) ID of the periodicals.
         * @return Builder object
         */
        public Builder periodicalsId(long periodicalsId) {
            this.periodicalsId = periodicalsId;
            return this;
        }

        /**
         * Setter for a month quantity.
         *
         * @param monthQuantity (required) Quantity of the months.
         * @return Builder object
         */
        public Builder monthQuantity(int monthQuantity) {
            this.monthQuantity = monthQuantity;
            return this;
        }

        /**
         * Setter for a date.
         *
         * @param date Date of the subscription.
         * @return Builder object
         */
        public Builder date(LocalDateTime date) {
            this.date = date;
            return this;
        }

        /**
         * Builds a new Subscription.
         *
         * @return Subscription object
         */
        public Subscription build() {
            return new Subscription(id, readerId, periodicalsId, monthQuantity, date);
        }
    }
}
