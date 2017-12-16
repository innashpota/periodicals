package com.inna.shpota.periodicals.domain;

import java.math.BigDecimal;

/**
 * Periodicals Model Object
 *
 * <P>Various attributes of periodicals, and related behaviour.
 *
 * <P>Note that {@link BigDecimal} is used to model the month price - not double or float.
 * See {@link #Periodicals(String name, String publisher, BigDecimal monthPrice)} or
 * {@link #Periodicals(long id, String name, String publisher, BigDecimal monthPrice)} for more information
 *
 * @author Inna Shpota
 * @version 1.0
 */
public class Periodicals {
    private long id;
    private final String name;
    private final String publisher;
    private final BigDecimal monthPrice;

    /**
     * Constructor for all parameters.
     *
     * @param id         ID of the periodicals.
     * @param name       (required) Name of the periodicals.
     * @param publisher  (required) Publisher of the periodicals.
     * @param monthPrice (required) Month price of the all months.
     */
    public Periodicals(long id, String name, String publisher, BigDecimal monthPrice) {
        this(name, publisher, monthPrice);
        this.id = id;
    }

    /**
     * Constructor.
     *
     * @param name       (required) Name of the periodicals.
     * @param publisher  (required) Publisher of the periodicals.
     * @param monthPrice (required) Month price of the all months.
     */
    public Periodicals(String name, String publisher, BigDecimal monthPrice) {
        this.name = name;
        this.publisher = publisher;
        this.monthPrice = monthPrice;
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
     * Getter for a name.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for a publisher.
     *
     * @return publisher
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Getter for a month price.
     *
     * @return monthPrice
     */
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
