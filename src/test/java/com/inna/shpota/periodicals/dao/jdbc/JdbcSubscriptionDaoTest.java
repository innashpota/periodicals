package com.inna.shpota.periodicals.dao.jdbc;

import com.inna.shpota.periodicals.domain.Subscription;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.Month.DECEMBER;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class JdbcSubscriptionDaoTest extends AbstractDaoTest {
    private JdbcSubscriptionDao jdbcSubscriptionDao;

    @Before
    public void before() throws Exception {
        prepareConnection();
        jdbcSubscriptionDao = new JdbcSubscriptionDao(jdbcDataSource);
    }

    @Test
    public void shouldCreate() throws Exception {
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
    public void shouldDelete() throws Exception {
        long id = 6;

        jdbcSubscriptionDao.delete(id);

        int size = jdbcSubscriptionDao.getAll().size();
        assertEquals(5, size);
    }

    @Test
    public void shouldGetById() throws Exception {
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
    public void shouldUpdate() throws Exception {
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
    public void shouldGetAll() throws Exception {
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
}