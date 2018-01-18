package com.inna.shpota.periodicals.strategies;

import com.inna.shpota.periodicals.dao.PeriodicalsDao;
import com.inna.shpota.periodicals.domain.Periodicals;
import org.junit.Ignore;
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
        List<Periodicals> periodicals = asList(
                new Periodicals(1, "name1", "publisher", new BigDecimal("1.11")),
                new Periodicals(2, "name2", "publisher", new BigDecimal("2.22")));
        given(periodicalsDao.getAll()).willReturn(periodicals);
        given(request.getSession()).willReturn(session);
        given(request.getRequestDispatcher("/WEB-INF/jsp/periodicals.jsp")).willReturn(dispatcher);

        strategy.handle(request, response);

        verify(session).setAttribute("periodicals", periodicals);
        verify(dispatcher).forward(request, response);
    }
}