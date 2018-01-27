package com.inna.shpota.periodicals.domain;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Periodicals Model Object
 * <p>
 * <P>Various attributes of periodicals, and related behaviour.
 * <p>
 * <P>Note that {@link BigDecimal} is used to model the month price - not double or float.
 * See {@link #Periodicals(String name, String publisher, BigDecimal monthPrice, boolean deleted)} or
 * {@link #Periodicals(long id, String name, String publisher, BigDecimal monthPrice, boolean deleted)}
 * for more information
 *
 * @author Inna Shpota
 * @version 1.0
 */
public class Periodicals {
    private final long id;
    private final String name;
    private final String publisher;
    private final BigDecimal monthPrice;
    private final boolean deleted;

    /**
     * Constructor for all parameters.
     *
     * @param id         ID of the periodicals.
     * @param name       (required) Name of the periodicals.
     * @param publisher  (required) Publisher of the periodicals.
     * @param monthPrice (required) Month price of the all months.
     * @param deleted    (required) Deleted or not deleted periodicals.
     *                   Default value false.
     */
    public Periodicals(long id, String name, String publisher, BigDecimal monthPrice, boolean deleted) {
        this.id = id;
        this.name = name;
        this.publisher = publisher;
        this.monthPrice = monthPrice;
        this.deleted = deleted;
    }

    /**
     * Constructor.
     *
     * @param name       (required) Name of the periodicals.
     * @param publisher  (required) Publisher of the periodicals.
     * @param monthPrice (required) Month price of the all months.
     * @param deleted    (required) Deleted or not deleted periodicals.
     *                   Default value false.
     */
    public Periodicals(String name, String publisher, BigDecimal monthPrice, boolean deleted) {
        this(-1, name, publisher, monthPrice, deleted);
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

    /**
     * Getter for a deleted.
     *
     * @return deleted
     */
    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Periodicals that = (Periodicals) o;
        return id == that.id &&
                deleted == that.deleted &&
                Objects.equals(name, that.name) &&
                Objects.equals(publisher, that.publisher) &&
                Objects.equals(monthPrice, that.monthPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, publisher, monthPrice, deleted);
    }

    @Override
    public String toString() {
        return "Periodicals{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", publisher='" + publisher + '\'' +
                ", monthPrice=" + monthPrice +
                ", deleted=" + deleted +
                '}';
    }
}
