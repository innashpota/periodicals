package com.inna.shpota.periodicals.dao.jdbc;

import com.inna.shpota.periodicals.domain.Periodicals;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class JdbcPeriodicalsDaoTest extends AbstractDaoTest {
    private JdbcPeriodicalsDao jdbcPeriodicalsDao;

    @Before
    public void before() throws Exception {
        prepareConnection();
        jdbcPeriodicalsDao = new JdbcPeriodicalsDao(jdbcDataSource);
    }

    @Test
    public void shouldCreate() throws Exception {
        Periodicals periodicals = new Periodicals("test", "test", new BigDecimal("66.66"));

        long id = jdbcPeriodicalsDao.create(periodicals);

        Periodicals actual = jdbcPeriodicalsDao.getById(id);
        assertEquals(periodicals.getName(), actual.getName());
        assertEquals(periodicals.getPublisher(), actual.getPublisher());
        assertEquals(periodicals.getMonthPrice(), actual.getMonthPrice());
    }

    @Test
    public void shouldDelete() throws Exception {
        long id = 4;

        jdbcPeriodicalsDao.delete(id);

        int size = jdbcPeriodicalsDao.getAll().size();
        assertEquals(3, size);
    }

    @Test
    public void shouldGetById() throws Exception {
        long id = 3;
        Periodicals expectedPeriodicals = new Periodicals(
                id, "HI - TECH PRO", "ID SoftPress", new BigDecimal("28.00")
        );

        Periodicals actualPeriodicals = jdbcPeriodicalsDao.getById(id);

        assertEquals(expectedPeriodicals, actualPeriodicals);
    }

    @Test
    public void shouldUpdate() throws Exception {
        long id = 3;
        Periodicals expectedPeriodicals = new Periodicals(3, "test", "test", new BigDecimal("66.66"));

        jdbcPeriodicalsDao.update(expectedPeriodicals);

        Periodicals actualPeriodicals = jdbcPeriodicalsDao.getById(id);
        assertEquals(expectedPeriodicals, actualPeriodicals);
    }

    @Test
    public void shouldGetAll() throws Exception {
        List<Periodicals> expected = asList(
                new Periodicals(1, "VOGUE UA", "TRK MEDIA FINANCE LIMITED", new BigDecimal("99.00")),
                new Periodicals(2, "All Retail", "All Retail", new BigDecimal("75.00")),
                new Periodicals(3, "HI - TECH PRO", "ID SoftPress", new BigDecimal("28.00"))
        );

        List<Periodicals> actual = jdbcPeriodicalsDao.getAll();

        assertEquals(expected, actual);
    }
}