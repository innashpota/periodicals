package com.inna.shpota.periodicals.strategies;

import com.inna.shpota.periodicals.dao.jdbc.AbstractDaoTest;
import com.inna.shpota.periodicals.dao.jdbc.JdbcPeriodicalsDao;
import com.inna.shpota.periodicals.domain.Periodicals;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PeriodicalsStrategyTest extends AbstractDaoTest {
    private JdbcPeriodicalsDao jdbcPeriodicalsDao;

    @Before
    public void before() {
        prepareConnection();
        jdbcPeriodicalsDao = new JdbcPeriodicalsDao(jdbcDataSource);
    }

    @Test
    public void shouldHandle() throws Exception {
        Strategy strategy = new PeriodicalsStrategy(jdbcPeriodicalsDao);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        List<Periodicals> periodicals = jdbcPeriodicalsDao.getAll();
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        HttpSession session = mock(HttpSession.class);
        given(request.getSession()).willReturn(session);
        given(request.getRequestDispatcher("/jsp/periodicals.jsp")).willReturn(dispatcher);

        strategy.handle(request, response);

        verify(request.getSession()).setAttribute("periodicals", periodicals);
        verify(dispatcher).forward(request, response);
    }
}