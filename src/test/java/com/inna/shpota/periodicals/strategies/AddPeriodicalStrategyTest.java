package com.inna.shpota.periodicals.strategies;

import com.inna.shpota.periodicals.dao.PeriodicalsDao;
import com.inna.shpota.periodicals.domain.Periodicals;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AddPeriodicalStrategyTest {
    @Test
    public void shouldHandle() throws ServletException, IOException {
        PeriodicalsDao periodicalsDao = mock(PeriodicalsDao.class);
        Strategy strategy = new AddPeriodicalStrategy(periodicalsDao);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        given(request.getParameter("name")).willReturn("name");
        given(request.getParameter("publisher")).willReturn("publisher");
        given(request.getParameter("monthPrice")).willReturn("2.00");
        given(periodicalsDao.create(periodical())).willReturn(1L);
        given(request.getSession()).willReturn(session);
        List<Periodicals> list = singletonList(periodicalWithId());
        given(periodicalsDao.getAll()).willReturn(list);

        strategy.handle(request, response);

        verify(session).setAttribute("periodicals", list);
        verify(response).sendRedirect("/edit-periodicals");
    }

    @Test
    public void shouldHandleRedirect() throws ServletException, IOException {
        PeriodicalsDao periodicalsDao = mock(PeriodicalsDao.class);
        Strategy strategy = new AddPeriodicalStrategy(periodicalsDao);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        given(request.getParameter("name")).willReturn("name");
        given(request.getParameter("publisher")).willReturn("publisher");
        given(request.getParameter("monthPrice")).willReturn("2.00");
        given(periodicalsDao.create(periodical())).willReturn(-2L);
        given(request.getSession()).willReturn(session);

        strategy.handle(request, response);

        verify(session).setAttribute("message", null);
        verify(response).sendRedirect("/error");
    }

    private Periodicals periodical() {
        return new Periodicals("name", "publisher", new BigDecimal("2.00"));
    }

    private Periodicals periodicalWithId() {
        return new Periodicals(1, "name", "publisher", new BigDecimal("2.00"));
    }
}