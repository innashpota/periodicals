package com.inna.shpota.periodicals.dao.jdbc;

import com.inna.shpota.periodicals.domain.Reader;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class JdbcReaderDaoTest extends AbstractDaoTest {
    private JdbcReaderDao jdbcReaderDao;

    @Before
    public void before() throws Exception {
        prepareConnection();
        jdbcReaderDao = new JdbcReaderDao(jdbcDataSource);
    }

    @Test
    public void shouldCreate() throws Exception {
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
    public void shouldDelete() throws Exception {
        long id = 5;

        jdbcReaderDao.delete(id);

        int size = jdbcReaderDao.getAll().size();
        assertEquals(4, size);
    }

    @Test
    public void shouldGetById() throws Exception {
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
    public void shouldUpdate() throws Exception {
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
    public void shouldGetAll() throws Exception {
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
}