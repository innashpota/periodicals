package com.inna.shpota.periodicals.dao.jdbc;

import com.inna.shpota.periodicals.domain.Admin;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class JdbcAdminDaoTest extends AbstractDaoTest {
    private JdbcAdminDao jdbcAdminDao;

    @Before
    public void before() throws Exception {
        prepareConnection();
        jdbcAdminDao = new JdbcAdminDao(jdbcDataSource);
    }

    @Test
    public void shouldCreate() throws Exception {
        Admin admin = new Admin("test", "test");

        long id = jdbcAdminDao.create(admin);

        Admin actual = jdbcAdminDao.getById(id);
        assertEquals(admin.getLogin(), actual.getLogin());
        assertEquals(admin.getPassword(), actual.getPassword());
    }

    @Test
    public void shouldDelete() throws Exception {
        long id = 4;

        jdbcAdminDao.delete(id);

        int size = jdbcAdminDao.getAll().size();
        assertEquals(3, size);
    }

    @Test
    public void shouldGetById() throws Exception {
        Admin expectedAdmin = new Admin(1, "admin1", "admin1");

        Admin actualAdmin = jdbcAdminDao.getById(1);

        assertEquals(expectedAdmin, actualAdmin);
    }

    @Test
    public void shouldUpdate() throws Exception {
        Admin expectedAdmin = new Admin(1, "test", "test");

        jdbcAdminDao.update(expectedAdmin);

        Admin actualAdmin = jdbcAdminDao.getById(1);
        assertEquals(expectedAdmin, actualAdmin);
    }

    @Test
    public void shouldGetAll() throws Exception {
        List<Admin> expected = asList(
                new Admin(1, "admin1", "admin1"),
                new Admin(2, "admin2", "admin2"),
                new Admin(3, "admin3", "admin3")
        );

        List<Admin> actual = jdbcAdminDao.getAll();

        assertEquals(expected, actual);
    }
}