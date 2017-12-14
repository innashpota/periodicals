package com.inna.shpota.periodicals.domain;

import java.math.BigDecimal;

public class Periodicals {
    private long id;
    private final String name;
    private final String publisher;
    private final BigDecimal monthPrice;

    public Periodicals(long id, String name, String publisher, BigDecimal monthPrice) {
        this(name, publisher, monthPrice);
        this.id = id;
    }

    public Periodicals(String name, String publisher, BigDecimal monthPrice) {
        this.name = name;
        this.publisher = publisher;
        this.monthPrice = monthPrice;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPublisher() {
        return publisher;
    }

    public BigDecimal getMonthPrice() {
        return monthPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Periodicals that = (Periodicals) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (publisher != null ? !publisher.equals(that.publisher) : that.publisher != null) return false;
        return monthPrice != null ? monthPrice.equals(that.monthPrice) : that.monthPrice == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (publisher != null ? publisher.hashCode() : 0);
        result = 31 * result + (monthPrice != null ? monthPrice.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Periodicals{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", publisher='" + publisher + '\'' +
                ", monthPrice=" + monthPrice +
                '}';
    }
}
