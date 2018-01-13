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

public class SavePeriodicalStrategyTest {
    @Test
    public void shouldHandle() throws ServletException, IOException {
        PeriodicalsDao periodicalsDao = mock(PeriodicalsDao.class);
        long id = 3;
        String name = "name";
        String publisher = "publisher";
        BigDecimal price = new BigDecimal("2.22");
        Strategy strategy = new SavePeriodicalStrategy(periodicalsDao);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        given(request.getRequestURI()).willReturn("/periodicals/edit/" + id);
        given(request.getParameter("name")).willReturn(name);
        given(request.getParameter("publisher")).willReturn(publisher);
        given(request.getParameter("monthPrice")).willReturn("2.22");
        Periodicals periodical = new Periodicals(id, "name", "publisher", price);
        List<Periodicals> list = singletonList(periodical);
        given(periodicalsDao.getAll()).willReturn(list);
        given(request.getSession()).willReturn(session);

        strategy.handle(request, response);

        verify(periodicalsDao).update(periodical);
        verify(session).setAttribute("periodicals", list);
        verify(response).sendRedirect("/edit-periodicals");
    }
}