package com.inna.shpota.periodicals.dao.jdbc;

import com.inna.shpota.periodicals.domain.Payment;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class JdbcPaymentDaoTest extends AbstractDaoTest {
    private JdbcPaymentDao jdbcPaymentDao;

    @Before
    public void setUp() throws Exception {
        prepareConnection();
        jdbcPaymentDao = new JdbcPaymentDao(jdbcDataSource);
    }

    @Test
    public void shouldCreate() throws Exception {
        Payment payment = new Payment(1, new BigDecimal("2.00"), true);

        long id = jdbcPaymentDao.create(payment);

        Payment actual = jdbcPaymentDao.getById(id);
        assertEquals(payment.getSubscriptionId(), actual.getSubscriptionId());
        assertEquals(payment.getPrice(), actual.getPrice());
        assertEquals(payment.isPaid(), actual.isPaid());
    }

    @Test
    public void shouldDelete() throws Exception {
        long id = 6;

        jdbcPaymentDao.delete(id);

        int size = jdbcPaymentDao.getAll().size();
        assertEquals(5, size);
    }

    @Test
    public void shouldGetById() throws Exception {
        long id = 2;
        Payment expectedPayment = new Payment(id, 2, new BigDecimal("900.00"), true);

        Payment actualPayment = jdbcPaymentDao.getById(id);

        assertEquals(expectedPayment, actualPayment);
    }

    @Test
    public void shouldUpdate() throws Exception {
        Payment expectedPayment = new Payment(2, 2, new BigDecimal("1999.00"), false);

        jdbcPaymentDao.update(expectedPayment);

        Payment actualPayment = jdbcPaymentDao.getById(2);
        assertEquals(expectedPayment, actualPayment);
    }

    @Test
    public void shouldGetAll() throws Exception {
        List<Payment> expected = asList(
                new Payment(1, 1, new BigDecimal("594.00"), true),
                new Payment(2, 2, new BigDecimal("900.00"), true),
                new Payment(3, 3, new BigDecimal("84.00"), true),
                new Payment(4, 4, new BigDecimal("297.00"), true),
                new Payment(5, 5, new BigDecimal("1188.00"), true)
        );

        List<Payment> actual = jdbcPaymentDao.getAll();

        assertEquals(expected, actual);
    }

}