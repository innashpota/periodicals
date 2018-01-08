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
        long id = 3;
        Strategy strategy = new EditPeriodicalStrategy(periodicalsDao);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        given(request.getRequestURI()).willReturn("/periodicals/edit/" + id);
        given(request.getSession()).willReturn(session);
        BigDecimal price = new BigDecimal("2.22");
        Periodicals periodical = new Periodicals(id, "name", "publisher", price);
        given(periodicalsDao.getById(id)).willReturn(periodical);
        given(request.getRequestDispatcher("/jsp/edit.jsp")).willReturn(dispatcher);

        strategy.handle(request, response);

        verify(session).setAttribute("periodical", periodical);
        verify(dispatcher).forward(request, response);
    }
}