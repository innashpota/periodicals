package com.inna.shpota.periodicals.strategies;

import com.inna.shpota.periodicals.dao.PeriodicalsDao;
import com.inna.shpota.periodicals.domain.Periodicals;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PeriodicalsStrategyTest {
    @Test
    public void shouldHandle() throws ServletException, IOException {
        PeriodicalsDao periodicalsDao = mock(PeriodicalsDao.class);
        Strategy strategy = new PeriodicalsStrategy(periodicalsDao);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        prepareMock(periodicalsDao, request, session, dispatcher);

        strategy.handle(request, response);

        verify(session).setAttribute("periodicals", list());
        verify(dispatcher).forward(request, response);
    }

    private void prepareMock(
            PeriodicalsDao periodicalsDao, HttpServletRequest request, HttpSession session, RequestDispatcher dispatcher
    ) {
        given(periodicalsDao.getAll()).willReturn(list());
        given(request.getSession()).willReturn(session);
        given(request.getRequestDispatcher("/WEB-INF/jsp/periodicals.jsp")).willReturn(dispatcher);
    }

    private List<Periodicals> list() {
        return asList(
                new Periodicals(1, "name1", "publisher", new BigDecimal("1.11"), false),
                new Periodicals(2, "name2", "publisher", new BigDecimal("2.22"), false));
    }
}