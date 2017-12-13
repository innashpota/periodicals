package com.inna.shpota.periodicals.domain;

import java.time.LocalDateTime;

public class Subscription {
    private long id;
    private final long readerId;
    private final long periodicalsId;
    private final long monthQuantity;
    private final LocalDateTime date;

    private Subscription(
            long id,
            long readerId,
            long periodicalsId,
            long monthQuantity,
            LocalDateTime date) {
        this.id = id;
        this.readerId = readerId;
        this.periodicalsId = periodicalsId;
        this.monthQuantity = monthQuantity;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public long getReaderId() {
        return readerId;
    }

    public long getPeriodicalsId() {
        return periodicalsId;
    }

    public long getMonthQuantity() {
        return monthQuantity;
    }

    public LocalDateTime getDate() {
        return date;
    }

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
        result = 31 * result + (int) (monthQuantity ^ (monthQuantity >>> 32));
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
        private long monthQuantity;
        private LocalDateTime date;

        private Builder() { }

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder readerId(long readerId) {
            this.readerId = readerId;
            return this;
        }

        public Builder periodicalsId(long periodicalsId) {
            this.periodicalsId = periodicalsId;
            return this;
        }

        public Builder monthQuantity(long monthQuantity) {
            this.monthQuantity = monthQuantity;
            return this;
        }

        public Builder date(LocalDateTime date) {
            this.date = date;
            return this;
        }

        public Subscription build() {
            return new Subscription(id, readerId, periodicalsId, monthQuantity, date);
        }
    }
}
