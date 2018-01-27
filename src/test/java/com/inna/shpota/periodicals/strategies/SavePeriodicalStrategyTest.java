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

import static java.util.Collections.singletonList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SavePeriodicalStrategyTest {
    @Test
    public void shouldHandle() throws ServletException, IOException {
        PeriodicalsDao periodicalsDao = mock(PeriodicalsDao.class);
        Strategy strategy = new SavePeriodicalStrategy(periodicalsDao);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        prepareMock(periodicalsDao, request, session);

        strategy.handle(request, response);

        verify(periodicalsDao).update(periodical());
        verify(session).setAttribute("periodicals", singletonList(periodical()));
        verify(response).sendRedirect("/edit-periodicals");
    }

    private void prepareMock(PeriodicalsDao periodicalsDao, HttpServletRequest request, HttpSession session) {
        given(request.getRequestURI()).willReturn("/periodicals/edit/" + 3);
        given(request.getParameter("name")).willReturn("name");
        given(request.getParameter("publisher")).willReturn("publisher");
        given(request.getParameter("monthPrice")).willReturn("2.22");
        given(periodicalsDao.getAll()).willReturn(singletonList(periodical()));
        given(request.getSession()).willReturn(session);
    }

    private Periodicals periodical() {
        return new Periodicals(3, "name", "publisher", new BigDecimal("2.22"), false);
    }
}