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
        long id = 3;
        Strategy strategy = new DeletePeriodicalStrategy(periodicalsDao);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        given(request.getRequestURI()).willReturn("/periodicals/delete/" + id);
        given(request.getSession()).willReturn(session);
        List<Periodicals> list = singletonList(
                new Periodicals(1, "name", "publisher", new BigDecimal("2.22")));
        given(periodicalsDao.getAll()).willReturn(list);

        strategy.handle(request, response);

        verify(periodicalsDao).delete(id);
        verify(session).setAttribute("periodicals", list);
        verify(response).sendRedirect("/edit-periodicals");
    }
}