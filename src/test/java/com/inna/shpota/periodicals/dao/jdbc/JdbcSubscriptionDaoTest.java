package com.inna.shpota.periodicals.dao.jdbc;

import com.inna.shpota.periodicals.domain.Payment;
import com.inna.shpota.periodicals.domain.Subscription;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static java.time.Month.DECEMBER;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class JdbcSubscriptionDaoTest extends AbstractDaoTest {
    private JdbcSubscriptionDao jdbcSubscriptionDao;
    private JdbcPaymentDao jdbcPaymentDao;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void before() {
        prepareConnection();
        jdbcSubscriptionDao = new JdbcSubscriptionDao(jdbcDataSource);
        jdbcPaymentDao = new JdbcPaymentDao(jdbcDataSource);
    }

    @Test
    public void shouldCreate() {
        Subscription subscription = Subscription.builder()
                .readerId(1)
                .periodicalsId(1)
                .monthQuantity(5)
                .date(LocalDateTime.now())
                .build();

        long id = jdbcSubscriptionDao.create(subscription);

        Subscription actual = jdbcSubscriptionDao.getById(id);
        assertEquals(subscription.getReaderId(), actual.getReaderId());
        assertEquals(subscription.getPeriodicalsId(), actual.getPeriodicalsId());
        assertEquals(subscription.getMonthQuantity(), actual.getMonthQuantity());
        assertEquals(subscription.getDate(), actual.getDate());
    }

    @Test
    public void shouldCreatePaymentBySubscription() {
        int monthQuantity = 5;
        Subscription subscription = Subscription.builder()
                .readerId(1)
                .periodicalsId(1)
                .monthQuantity(monthQuantity)
                .date(LocalDateTime.now())
                .build();
        BigDecimal monthPrice = new BigDecimal("20.00");
        BigDecimal quantity = new BigDecimal(monthQuantity);
        BigDecimal price = monthPrice.multiply(quantity);

        long paymentId = jdbcSubscriptionDao.createPaymentBySubscription(subscription, monthPrice);

        Payment actual = jdbcPaymentDao.getById(paymentId);
        assertEquals(price, actual.getPrice());
        assertEquals(false, actual.isPaid());
    }

    @Test
    public void shouldDelete() {
        long id = 6;

        jdbcSubscriptionDao.delete(id);

        int size = jdbcSubscriptionDao.getAll().size();
        assertEquals(5, size);
    }

    @Test
    public void shouldGetById() {
        long id = 2;
        Subscription expectedSubscription = Subscription.builder()
                .id(id)
                .readerId(1)
                .periodicalsId(2)
                .monthQuantity(12)
                .date(LocalDateTime.of(2017, DECEMBER, 14, 13, 35))
                .build();

        Subscription actualSubscription = jdbcSubscriptionDao.getById(id);

        assertEquals(expectedSubscription, actualSubscription);
    }

    @Test
    public void shouldUpdate() {
        int id = 2;
        Subscription expectedSubscription = Subscription.builder()
                .id(id)
                .readerId(1)
                .periodicalsId(1)
                .monthQuantity(5)
                .date(LocalDateTime.now())
                .build();

        jdbcSubscriptionDao.update(expectedSubscription);

        Subscription actualSubscription = jdbcSubscriptionDao.getById(id);
        assertEquals(expectedSubscription, actualSubscription);
    }

    @Test
    public void shouldGetAll() {
        List<Subscription> expected = asList(
                Subscription.builder()
                        .id(1)
                        .readerId(2)
                        .periodicalsId(1)
                        .monthQuantity(6)
                        .date(LocalDateTime.of(2017, DECEMBER, 16, 13, 35))
                        .build(),
                Subscription.builder()
                        .id(2)
                        .readerId(1)
                        .periodicalsId(2)
                        .monthQuantity(12)
                        .date(LocalDateTime.of(2017, DECEMBER, 14, 13, 35))
                        .build(),
                Subscription.builder()
                        .id(3)
                        .readerId(1)
                        .periodicalsId(3)
                        .monthQuantity(3)
                        .date(LocalDateTime.of(2017, DECEMBER, 14, 13, 35))
                        .build(),
                Subscription.builder()
                        .id(4)
                        .readerId(4)
                        .periodicalsId(1)
                        .monthQuantity(3)
                        .date(LocalDateTime.of(2017, DECEMBER, 4, 13, 35))
                        .build(),
                Subscription.builder()
                        .id(5)
                        .readerId(3)
                        .periodicalsId(1)
                        .monthQuantity(12)
                        .date(LocalDateTime.of(2017, DECEMBER, 4, 13, 35))
                        .build()
        );

        List<Subscription> actual = jdbcSubscriptionDao.getAll();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldFailToCreateGivenNullSubscription() {
        Subscription subscription = null;
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Subscription must not be null");

        jdbcSubscriptionDao.create(subscription);
    }

    @Test
    public void shouldFailToCreateGivenNegativeReaderId() {
        Subscription subscription = getSubscriptionWithNegativeReaderId();
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Reader ID must be positive");

        jdbcSubscriptionDao.create(subscription);
    }

    @Test
    public void shouldFailToCreateGivenNegativePeriodicalsId() {
        Subscription subscription = getSubscriptionWithNegativePeriodicalsId();
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Periodicals ID must be positive");

        jdbcSubscriptionDao.create(subscription);
    }

    @Test
    public void shouldFailToCreateGivenNegativeMonthQuantity() {
        Subscription subscription = getSubscriptionWithNegativeMonthQuantity();
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Month quantity must be positive");

        jdbcSubscriptionDao.create(subscription);
    }

    @Test
    public void shouldFailToCreateGivenNullDate() {
        Subscription subscription = getSubscriptionWithNullDate();
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Date must not be null");

        jdbcSubscriptionDao.create(subscription);
    }


    @Test
    public void shouldFailToCreatePaymentGivenNullSubscription() {
        Subscription subscription = null;
        BigDecimal monthPrice = new BigDecimal("10.00");
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Subscription must not be null");

        jdbcSubscriptionDao.createPaymentBySubscription(subscription, monthPrice);
    }

    @Test
    public void shouldFailToCreatePaymentGivenNegativeReaderId() {
        Subscription subscription = getSubscriptionWithNegativeReaderId();
        BigDecimal monthPrice = new BigDecimal("10.00");
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Reader ID must be positive");

        jdbcSubscriptionDao.createPaymentBySubscription(subscription, monthPrice);
    }

    @Test
    public void shouldFailToCreatePaymentGivenNegativePeriodicalsId() {
        Subscription subscription = getSubscriptionWithNegativePeriodicalsId();
        BigDecimal monthPrice = new BigDecimal("10.00");
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Periodicals ID must be positive");

        jdbcSubscriptionDao.createPaymentBySubscription(subscription, monthPrice);
    }

    @Test
    public void shouldFailToCreatePaymentGivenNegativeMonthQuantity() {
        Subscription subscription = getSubscriptionWithNegativeMonthQuantity();
        BigDecimal monthPrice = new BigDecimal("10.00");
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Month quantity must be positive");

        jdbcSubscriptionDao.createPaymentBySubscription(subscription, monthPrice);
    }

    @Test
    public void shouldFailToCreatePaymentGivenNullDate() {
        Subscription subscription = getSubscriptionWithNullDate();
        BigDecimal monthPrice = new BigDecimal("10.00");
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Date must not be null");

        jdbcSubscriptionDao.createPaymentBySubscription(subscription, monthPrice);
    }

    @Test
    public void shouldFailToCreatePaymentGivenNegativeMonthPrice() {
        Subscription subscription = Subscription.builder()
                .readerId(1)
                .periodicalsId(1)
                .monthQuantity(5)
                .date(LocalDateTime.now())
                .build();
        BigDecimal monthPrice = new BigDecimal("-10.00");
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Month price must be positive");

        jdbcSubscriptionDao.createPaymentBySubscription(subscription, monthPrice);
    }

    @Test
    public void shouldFailToDeleteGivenNegativeId() {
        long id = -2;
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("ID must be positive");

        jdbcSubscriptionDao.delete(id);
    }

    @Test
    public void shouldFailToGetByIdGivenNegativeId() {
        long id = -2;
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("ID must be positive");

        jdbcSubscriptionDao.getById(id);
    }

    @Test
    public void shouldFailToUpdateGivenNullSubscription() {
        Subscription subscription = null;
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Subscription must not be null");

        jdbcSubscriptionDao.update(subscription);
    }

    @Test
    public void shouldFailToUpdateGivenNegativeReaderId() {
        Subscription subscription = getSubscriptionWithNegativeReaderId();
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Reader ID must be positive");

        jdbcSubscriptionDao.update(subscription);
    }

    @Test
    public void shouldFailToUpdateGivenNegativePeriodicalsId() {
        Subscription subscription = getSubscriptionWithNegativePeriodicalsId();
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Periodicals ID must be positive");

        jdbcSubscriptionDao.update(subscription);
    }

    @Test
    public void shouldFailToUpdateGivenNegativeMonthQuantity() {
        Subscription subscription = getSubscriptionWithNegativeMonthQuantity();
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Month quantity must be positive");

        jdbcSubscriptionDao.update(subscription);
    }

    @Test
    public void shouldFailToUpdateGivenNullDate() {
        Subscription subscription = getSubscriptionWithNullDate();
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Date must not be null");

        jdbcSubscriptionDao.update(subscription);
    }

    private Subscription getSubscriptionWithNegativeReaderId() {
        return Subscription.builder()
                .id(5)
                .readerId(-3)
                .periodicalsId(1)
                .monthQuantity(12)
                .date(LocalDateTime.of(2017, DECEMBER, 4, 13, 35))
                .build();
    }

    private Subscription getSubscriptionWithNegativePeriodicalsId() {
        return Subscription.builder()
                .id(5)
                .readerId(3)
                .periodicalsId(-1)
                .monthQuantity(12)
                .date(LocalDateTime.of(2017, DECEMBER, 4, 13, 35))
                .build();
    }

    private Subscription getSubscriptionWithNegativeMonthQuantity() {
        return Subscription.builder()
                .id(5)
                .readerId(3)
                .periodicalsId(1)
                .monthQuantity(-12)
                .date(LocalDateTime.of(2017, DECEMBER, 4, 13, 35))
                .build();
    }

    private Subscription getSubscriptionWithNullDate() {
        return Subscription.builder()
                .id(5)
                .readerId(3)
                .periodicalsId(1)
                .monthQuantity(12)
                .date(null)
                .build();
    }
}