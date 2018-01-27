package com.inna.shpota.periodicals.dao.jdbc;

import com.inna.shpota.periodicals.domain.Periodicals;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class JdbcPeriodicalsDaoTest extends AbstractDaoTest {
    private JdbcPeriodicalsDao jdbcPeriodicalsDao;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void before() {
        prepareConnection();
        jdbcPeriodicalsDao = new JdbcPeriodicalsDao(jdbcDataSource);
    }

    @Test
    public void shouldCreate() {
        Periodicals periodicals = new Periodicals("test", "test", new BigDecimal("66.66"), false);

        long id = jdbcPeriodicalsDao.create(periodicals);

        Periodicals actual = jdbcPeriodicalsDao.getById(id);
        assertEquals(periodicals.getName(), actual.getName());
        assertEquals(periodicals.getPublisher(), actual.getPublisher());
        assertEquals(periodicals.getMonthPrice(), actual.getMonthPrice());
    }

    @Test
    public void shouldDelete() {
        long id = 3;

        jdbcPeriodicalsDao.delete(id);

        Periodicals actual = jdbcPeriodicalsDao.getById(id);
        assertEquals(true, actual.isDeleted());
    }

    @Test
    public void shouldGetById() {
        long id = 3;
        Periodicals expectedPeriodicals = new Periodicals(
                id, "HI - TECH PRO", "ID SoftPress", new BigDecimal("28.00"), false
        );

        Periodicals actualPeriodicals = jdbcPeriodicalsDao.getById(id);

        assertEquals(expectedPeriodicals, actualPeriodicals);
    }

    @Test
    public void shouldUpdate() {
        long id = 3;
        Periodicals expectedPeriodicals = new Periodicals(3, "test", "test", new BigDecimal("66.66"), false);

        jdbcPeriodicalsDao.update(expectedPeriodicals);

        Periodicals actualPeriodicals = jdbcPeriodicalsDao.getById(id);
        assertEquals(expectedPeriodicals, actualPeriodicals);
    }

    @Test
    public void shouldGetAll() {
        List<Periodicals> expected = asList(
                new Periodicals(1, "VOGUE UA", "TRK MEDIA FINANCE LIMITED", new BigDecimal("99.00"), false),
                new Periodicals(2, "All Retail", "All Retail", new BigDecimal("75.00"), false),
                new Periodicals(3, "HI - TECH PRO", "ID SoftPress", new BigDecimal("28.00"), false)
        );

        List<Periodicals> actual = jdbcPeriodicalsDao.getAll();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldFailToCreateGivenNullPeriodicals() {
        Periodicals periodicals = null;
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Periodicals must not be null");

        jdbcPeriodicalsDao.create(periodicals);
    }

    @Test
    public void shouldFailToCreateGivenEmptyName() {
        Periodicals periodicals = new Periodicals(null, "publisher", new BigDecimal("2.00"), false);
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Name must not be empty");

        jdbcPeriodicalsDao.create(periodicals);
    }

    @Test
    public void shouldFailToCreateGivenEmptyPublisher() {
        Periodicals periodicals = new Periodicals("name", null, new BigDecimal("2.00"), false);
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Publisher must not be empty");

        jdbcPeriodicalsDao.create(periodicals);
    }

    @Test
    public void shouldFailToCreateGivenNegativeMonthPrice() {
        Periodicals periodicals = new Periodicals("name", "publisher", new BigDecimal("-2.00"), false);
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Month price must be positive");

        jdbcPeriodicalsDao.create(periodicals);
    }

    @Test
    public void shouldFailToDeleteGivenNegativeId() {
        long id = -2;
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("ID must be positive");

        jdbcPeriodicalsDao.delete(id);
    }

    @Test
    public void shouldFailToGetByIdGivenNegativeId() {
        long id = -2;
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("ID must be positive");

        jdbcPeriodicalsDao.getById(id);
    }

    @Test
    public void shouldFailToUpdateGivenNullPeriodicals() {
        Periodicals periodicals = null;
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Periodicals must not be null");

        jdbcPeriodicalsDao.update(periodicals);
    }

    @Test
    public void shouldFailToUpdateGivenEmptyName() {
        Periodicals periodicals = new Periodicals(null, "publisher", new BigDecimal("2.00"), false);
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Name must not be empty");

        jdbcPeriodicalsDao.update(periodicals);
    }

    @Test
    public void shouldFailToUpdateGivenEmptyPublisher() {
        Periodicals periodicals = new Periodicals("name", null, new BigDecimal("2.00"), false);
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Publisher must not be empty");

        jdbcPeriodicalsDao.update(periodicals);
    }

    @Test
    public void shouldFailToUpdateGivenNegativeMonthPrice() {
        Periodicals periodicals = new Periodicals("name", "publisher", new BigDecimal("-2.00"), false);
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Month price must be positive");

        jdbcPeriodicalsDao.update(periodicals);
    }
}