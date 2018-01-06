package com.inna.shpota.periodicals.dao.jdbc;

import com.inna.shpota.periodicals.domain.Information;
import com.inna.shpota.periodicals.domain.Reader;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static java.time.Month.DECEMBER;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;

public class JdbcReaderDaoTest extends AbstractDaoTest {
    private JdbcReaderDao jdbcReaderDao;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void before() {
        prepareConnection();
        jdbcReaderDao = new JdbcReaderDao(jdbcDataSource);
    }

    @Test
    public void shouldCreate() {
        Reader reader = Reader.builder()
                .lastName("lastName")
                .firstName("firstName")
                .middleName("middleName")
                .email("email")
                .password("password")
                .build();

        long id = jdbcReaderDao.create(reader);

        Reader actual = jdbcReaderDao.getById(id);
        assertEquals(reader.getLastName(), actual.getLastName());
        assertEquals(reader.getFirstName(), actual.getFirstName());
        assertEquals(reader.getMiddleName(), actual.getMiddleName());
        assertEquals(reader.getEmail(), actual.getEmail());
        assertEquals(reader.getPassword(), actual.getPassword());
    }

    @Test
    public void shouldDelete() {
        long id = 5;

        jdbcReaderDao.delete(id);

        int size = jdbcReaderDao.getAll().size();
        assertEquals(4, size);
    }

    @Test
    public void shouldGetById() {
        long id = 3;
        Reader expectedReader = Reader.builder()
                .id(id)
                .lastName("Paton")
                .firstName("Yevgen")
                .middleName("Oskarovich")
                .email("paton@tv.com")
                .password("3")
                .build();

        Reader actualReader = jdbcReaderDao.getById(id);

        assertEquals(expectedReader, actualReader);
    }

    @Test
    public void shouldGetByEmailAndPassword() {
        String email = "viazovska@ok.com";
        String password = "2";
        Reader expectedReader = Reader.builder()
                .id(2)
                .lastName("Viazovska")
                .firstName("Maryna")
                .middleName("Sergiivna")
                .email(email)
                .password(password)
                .build();

        Reader actualReader = jdbcReaderDao.getByEmailAndPassword(email, password);

        assertEquals(expectedReader, actualReader);
    }

    @Test
    public void shouldGetByEmail() {
        String email = "viazovska@ok.com";
        Reader expectedReader = Reader.builder()
                .id(2)
                .lastName("Viazovska")
                .firstName("Maryna")
                .middleName("Sergiivna")
                .email(email)
                .password("2")
                .build();

        Reader actualReader = jdbcReaderDao.getByEmail(email);

        assertEquals(expectedReader, actualReader);
    }

    @Test
    public void shouldUpdate() {
        long id = 3;
        Reader expectedReader = Reader.builder()
                .id(id)
                .lastName("lastName")
                .firstName("firstName")
                .middleName("middleName")
                .email("email")
                .password("password")
                .build();

        jdbcReaderDao.update(expectedReader);

        Reader actualReader = jdbcReaderDao.getById(id);
        assertEquals(expectedReader, actualReader);
    }

    @Test
    public void shouldGetAll() {
        List<Reader> expected = asList(
                Reader.builder()
                        .id(1)
                        .lastName("Korolyuk")
                        .firstName("Volodymyr")
                        .middleName("Semenovych")
                        .email("korolyuk@ok.com")
                        .password("1")
                        .build(),
                Reader.builder()
                        .id(2)
                        .lastName("Viazovska")
                        .firstName("Maryna")
                        .middleName("Sergiivna")
                        .email("viazovska@ok.com")
                        .password("2")
                        .build(),
                Reader.builder()
                        .id(3)
                        .lastName("Paton")
                        .firstName("Yevgen")
                        .middleName("Oskarovich")
                        .email("paton@tv.com")
                        .password("3")
                        .build(),
                Reader.builder()
                        .id(4)
                        .lastName("Drinfeld")
                        .firstName("Volodymyr")
                        .middleName("Gershonovich")
                        .email("drinfeld@tv.com")
                        .password("4")
                        .build()
        );

        List<Reader> actual = jdbcReaderDao.getAll();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldGetInformationByReader() {
        long id = 2;
        List<Information> expected = singletonList(
                Information.builder()
                        .periodicalsName("VOGUE UA")
                        .periodicalsPublisher("TRK MEDIA FINANCE LIMITED")
                        .periodicalsMonthPrice(new BigDecimal("99.00"))
                        .subscriptionDate(LocalDateTime.of(2017, DECEMBER, 16, 13, 35))
                        .monthQuantity(6)
                        .paymentPaid(true)
                        .build()
        );

        List<Information> actual = jdbcReaderDao.getInformationByReader(id);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldFailToCreateGivenNullReader() {
        Reader reader = null;
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Reader must not be null");

        jdbcReaderDao.create(reader);
    }

    @Test
    public void shouldFailToCreateGivenNullLastName() {
        Reader reader = getReaderNullLastName();
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Last name must not be empty");

        jdbcReaderDao.create(reader);
    }

    @Test
    public void shouldFailToCreateGivenNullFirstName() {
        Reader reader = getReaderNullFirstName();
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("First name must not be empty");

        jdbcReaderDao.create(reader);
    }

    @Test
    public void shouldFailToCreateGivenNullMiddleName() {
        Reader reader = getReaderNullMiddleName();
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Middle name must not be empty");

        jdbcReaderDao.create(reader);
    }

    @Test
    public void shouldFailToCreateGivenNullEmail() {
        Reader reader = getReaderNullEmail();
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Email must not be empty");

        jdbcReaderDao.create(reader);
    }

    @Test
    public void shouldFailToCreateGivenNullPassword() {
        Reader reader = getReaderNullPassword();
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Password must not be empty");

        jdbcReaderDao.create(reader);
    }

    @Test
    public void shouldFailToDeleteGivenNegativeId() {
        long id = -2;
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("ID must be positive");

        jdbcReaderDao.delete(id);
    }

    @Test
    public void shouldFailToGetByIdGivenNegativeId() {
        long id = -2;
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("ID must be positive");

        jdbcReaderDao.getById(id);
    }

    @Test
    public void shouldFailToGetByEmailAndPasswordGivenEmptyLogin() {
        String email = "";
        String password = "password";
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Email must not be empty");

        jdbcReaderDao.getByEmailAndPassword(email, password);
    }

    @Test
    public void shouldFailToGetByLoginAndPasswordGivenNullPassword() {
        String email = "email";
        String password = null;
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Password must not be empty");

        jdbcReaderDao.getByEmailAndPassword(email, password);
    }

    @Test
    public void shouldFailToGetByLoginGivenNullEmail() {
        String email = null;
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Email must not be empty");

        jdbcReaderDao.getByEmail(email);
    }

    @Test
    public void shouldFailToUpdateGivenNullReader() {
        Reader reader = null;
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Reader must not be null");

        jdbcReaderDao.update(reader);
    }

    @Test
    public void shouldFailToUpdateGivenNullLastName() {
        Reader reader = getReaderNullLastName();
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Last name must not be empty");

        jdbcReaderDao.update(reader);
    }

    @Test
    public void shouldFailToUpdateGivenNullFirstName() {
        Reader reader = getReaderNullFirstName();
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("First name must not be empty");

        jdbcReaderDao.update(reader);
    }

    @Test
    public void shouldFailToUpdateGivenNullMiddleName() {
        Reader reader = getReaderNullMiddleName();
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Middle name must not be empty");

        jdbcReaderDao.update(reader);
    }

    @Test
    public void shouldFailToUpdateGivenNullEmail() {
        Reader reader = getReaderNullEmail();
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Email must not be empty");

        jdbcReaderDao.update(reader);
    }

    @Test
    public void shouldFailToUpdateGivenNullPassword() {
        Reader reader = getReaderNullPassword();
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Password must not be empty");

        jdbcReaderDao.update(reader);
    }

    @Test
    public void shouldFailToGetInformationByReaderGivenNegativeId() {
        long id = -2;
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("ID must be positive");

        jdbcReaderDao.getInformationByReader(id);
    }

    private Reader getReaderNullLastName() {
        return Reader.builder()
                .id(5)
                .lastName(null)
                .firstName("Volodymyr")
                .middleName("Gershonovich")
                .email("test@tv.com")
                .password("4")
                .build();
    }

    private Reader getReaderNullFirstName() {
        return Reader.builder()
                .id(5)
                .lastName("Test")
                .firstName(null)
                .middleName("Gershonovich")
                .email("test@tv.com")
                .password("4")
                .build();
    }

    private Reader getReaderNullMiddleName() {
        return Reader.builder()
                .id(5)
                .lastName("Test")
                .firstName("Volodymyr")
                .middleName(null)
                .email("test@tv.com")
                .password("4")
                .build();
    }

    private Reader getReaderNullEmail() {
        return Reader.builder()
                .id(5)
                .lastName("Test")
                .firstName("Volodymyr")
                .middleName("Gershonovich")
                .email(null)
                .password("4")
                .build();
    }

    private Reader getReaderNullPassword() {
        return Reader.builder()
                .id(5)
                .lastName("Test")
                .firstName("Volodymyr")
                .middleName("Gershonovich")
                .email("test@tv.com")
                .password(null)
                .build();
    }
}