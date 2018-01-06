package com.inna.shpota.periodicals.dao.jdbc;

import com.inna.shpota.periodicals.domain.Payment;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class JdbcPaymentDaoTest extends AbstractDaoTest {
    private JdbcPaymentDao jdbcPaymentDao;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void before() {
        prepareConnection();
        jdbcPaymentDao = new JdbcPaymentDao(jdbcDataSource);
    }

    @Test
    public void shouldCreate() {
        Payment payment = new Payment(1, new BigDecimal("2.00"), true);

        long id = jdbcPaymentDao.create(payment);

        Payment actual = jdbcPaymentDao.getById(id);
        assertEquals(payment.getSubscriptionId(), actual.getSubscriptionId());
        assertEquals(payment.getPrice(), actual.getPrice());
        assertEquals(payment.isPaid(), actual.isPaid());
    }

    @Test
    public void shouldDelete() {
        long id = 6;

        jdbcPaymentDao.delete(id);

        int size = jdbcPaymentDao.getAll().size();
        assertEquals(5, size);
    }

    @Test
    public void shouldGetById() {
        long id = 2;
        Payment expectedPayment = new Payment(id, 2, new BigDecimal("900.00"), true);

        Payment actualPayment = jdbcPaymentDao.getById(id);

        assertEquals(expectedPayment, actualPayment);
    }

    @Test
    public void shouldUpdate() {
        long id = 2;
        Payment expectedPayment = new Payment(id, 2, new BigDecimal("1999.00"), false);

        jdbcPaymentDao.update(expectedPayment);

        Payment actualPayment = jdbcPaymentDao.getById(id);
        assertEquals(expectedPayment, actualPayment);
    }

    @Test
    public void shouldGetAll() {
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

    @Test
    public void shouldFailToCreateGivenNullPayment() {
        Payment payment = null;
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Payment must not be null");

        jdbcPaymentDao.create(payment);
    }

    @Test
    public void shouldFailToCreateGivenNegativeSubscriptionId() {
        Payment payment = new Payment(-4, new BigDecimal("2.00"), true);
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Subscription ID must be positive");

        jdbcPaymentDao.create(payment);
    }

    @Test
    public void shouldFailToCreateGivenNegativePrice() {
        Payment payment = new Payment(10, new BigDecimal("-2.00"), true);
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Price must be positive");

        jdbcPaymentDao.create(payment);
    }

    @Test
    public void shouldFailToDeleteGivenNegativeId() {
        long id = -2;
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("ID must be positive");

        jdbcPaymentDao.delete(id);
    }

    @Test
    public void shouldFailToGetByIdGivenNegativeId() {
        long id = -2;
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("ID must be positive");

        jdbcPaymentDao.getById(id);
    }

    @Test
    public void shouldFailToUpdateGivenNullPayment() {
        Payment payment = null;
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Payment must not be null");

        jdbcPaymentDao.update(payment);
    }

    @Test
    public void shouldFailToUpdateGivenNegativeSubscriptionId() {
        Payment payment = new Payment(-4, new BigDecimal("2.00"), true);
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Subscription ID must be positive");

        jdbcPaymentDao.update(payment);
    }

    @Test
    public void shouldFailToUpdateGivenNegativePrice() {
        Payment payment = new Payment(10, new BigDecimal("-2.00"), true);
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Price must be positive");

        jdbcPaymentDao.update(payment);
    }
}