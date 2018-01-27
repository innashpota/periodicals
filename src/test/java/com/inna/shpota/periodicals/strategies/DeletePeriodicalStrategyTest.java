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

public class DeletePeriodicalStrategyTest {
    @Test
    public void shouldHandle() throws ServletException, IOException {
        PeriodicalsDao periodicalsDao = mock(PeriodicalsDao.class);
        Strategy strategy = new DeletePeriodicalStrategy(periodicalsDao);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        prepareMock(periodicalsDao, request, session);

        strategy.handle(request, response);

        verify(periodicalsDao).delete(3);
        verify(session).setAttribute("periodicals", list());
        verify(response).sendRedirect("/edit-periodicals");
    }

    private void prepareMock(PeriodicalsDao periodicalsDao, HttpServletRequest request, HttpSession session) {
        given(request.getRequestURI()).willReturn("/periodicals/delete/" + 3);
        given(request.getSession()).willReturn(session);
        given(periodicalsDao.getAll()).willReturn(list());
    }

    private List<Periodicals> list() {
        return singletonList(
                new Periodicals(1, "name", "publisher", new BigDecimal("2.22"), false));
    }
}