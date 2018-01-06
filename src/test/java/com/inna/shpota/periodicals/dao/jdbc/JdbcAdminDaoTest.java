package com.inna.shpota.periodicals.dao.jdbc;

import com.inna.shpota.periodicals.domain.Admin;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class JdbcAdminDaoTest extends AbstractDaoTest {
    private JdbcAdminDao jdbcAdminDao;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void before() {
        prepareConnection();
        jdbcAdminDao = new JdbcAdminDao(jdbcDataSource);
    }

    @Test
    public void shouldCreate() {
        Admin admin = new Admin("test", "test");

        long id = jdbcAdminDao.create(admin);

        Admin actual = jdbcAdminDao.getById(id);
        assertEquals(admin.getLogin(), actual.getLogin());
        assertEquals(admin.getPassword(), actual.getPassword());
    }

    @Test
    public void shouldDelete() {
        long id = 4;

        jdbcAdminDao.delete(id);

        int size = jdbcAdminDao.getAll().size();
        assertEquals(3, size);
    }

    @Test
    public void shouldGetById() {
        long id = 1;
        Admin expectedAdmin = new Admin(id, "admin1", "admin1");

        Admin actualAdmin = jdbcAdminDao.getById(id);

        assertEquals(expectedAdmin, actualAdmin);
    }

    @Test
    public void shouldGetByLoginAndPassword() {
        String admin2 = "admin2";
        Admin expectedAdmin = new Admin(2, admin2, admin2);

        Admin actualAdmin = jdbcAdminDao.getByLoginAndPassword(admin2, admin2);

        assertEquals(expectedAdmin, actualAdmin);
    }

    @Test
    public void shouldUpdate() {
        long id = 1;
        Admin expectedAdmin = new Admin(id, "test", "test");

        jdbcAdminDao.update(expectedAdmin);

        Admin actualAdmin = jdbcAdminDao.getById(id);
        assertEquals(expectedAdmin, actualAdmin);
    }

    @Test
    public void shouldGetAll() {
        List<Admin> expected = asList(
                new Admin(1, "admin1", "admin1"),
                new Admin(2, "admin2", "admin2"),
                new Admin(3, "admin3", "admin3")
        );

        List<Admin> actual = jdbcAdminDao.getAll();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldFailToCreateGivenNullAdmin() {
        Admin admin = null;
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Admin must not be null");

        jdbcAdminDao.create(admin);
    }

    @Test
    public void shouldFailToCreateGivenNullLogin() {
        Admin admin = new Admin(null, "password");
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Login must not be empty");

        jdbcAdminDao.create(admin);
    }

    @Test
    public void shouldFailToCreateGivenNullPassword() {
        Admin admin = new Admin("login", null);
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Password must not be empty");

        jdbcAdminDao.create(admin);
    }

    @Test
    public void shouldFailToDeleteGivenNegativeId() {
        long id = -2;
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("ID must be positive");

        jdbcAdminDao.delete(id);
    }

    @Test
    public void shouldFailToGetByLoginAndPasswordGivenEmptyLogin() {
        String login = "";
        String password = "password";
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Login must not be empty");

        jdbcAdminDao.getByLoginAndPassword(login, password);
    }

    @Test
    public void shouldFailToGetByLoginAndPasswordGivenNullPassword() {
        String login = "login";
        String password = null;
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Password must not be empty");

        jdbcAdminDao.getByLoginAndPassword(login, password);
    }

    @Test
    public void shouldFailToGetByIdGivenNegativeId() {
        long id = -2;
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("ID must be positive");

        jdbcAdminDao.getById(id);
    }

    @Test
    public void shouldFailToUpdateGivenNullAdmin() {
        Admin admin = null;
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Admin must not be null");

        jdbcAdminDao.update(admin);
    }

    @Test
    public void shouldFailToUpdateGivenNullLogin() {
        Admin admin = new Admin(null, "password");
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Login must not be empty");

        jdbcAdminDao.update(admin);
    }

    @Test
    public void shouldFailToUpdateGivenNullPassword() {
        Admin admin = new Admin("login", null);
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Password must not be empty");

        jdbcAdminDao.update(admin);
    }
}