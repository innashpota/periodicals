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

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class EditPeriodicalStrategyTest {
    @Test
    public void shouldHandle() throws ServletException, IOException {
        PeriodicalsDao periodicalsDao = mock(PeriodicalsDao.class);
        Strategy strategy = new EditPeriodicalStrategy(periodicalsDao);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        prepareMock(periodicalsDao, request, session, dispatcher);

        strategy.handle(request, response);

        verify(session).setAttribute("periodical", periodical());
        verify(dispatcher).forward(request, response);
    }

    private void prepareMock(
            PeriodicalsDao periodicalsDao, HttpServletRequest request, HttpSession session, RequestDispatcher dispatcher
    ) {
        given(request.getRequestURI()).willReturn("/periodicals/edit/" + 3);
        given(request.getSession()).willReturn(session);
        given(periodicalsDao.getById(3)).willReturn(periodical());
        given(request.getRequestDispatcher("/WEB-INF/jsp/edit.jsp")).willReturn(dispatcher);
    }

    private Periodicals periodical() {
        return new Periodicals(3, "name", "publisher", new BigDecimal("2.22"), false);
    }
}