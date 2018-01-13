package com.inna.shpota.periodicals.strategies;

import com.inna.shpota.periodicals.dao.PeriodicalsDao;
import com.inna.shpota.periodicals.domain.Periodicals;
import com.inna.shpota.periodicals.domain.Reader;
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

public class SubscribeStrategyTest {
    @Test
    public void shouldHandle() throws ServletException, IOException {
        PeriodicalsDao periodicalsDao = mock(PeriodicalsDao.class);
        Strategy strategy = new SubscribeStrategy(periodicalsDao);
        Reader reader = reader();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        given(request.getSession()).willReturn(session);
        given(session.getAttribute("reader")).willReturn(reader);
        given(request.getRequestURI()).willReturn("/periodicals/subscribe/2");
        given(periodicalsDao.getById(2)).willReturn(periodical());
        given(request.getRequestDispatcher("/jsp/subscribe.jsp")).willReturn(dispatcher);

        strategy.handle(request, response);

        verify(session).setAttribute("periodical", periodical());
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void shouldHandleForward() throws ServletException, IOException {
        PeriodicalsDao periodicalsDao = mock(PeriodicalsDao.class);
        Strategy strategy = new SubscribeStrategy(periodicalsDao);
        Reader reader = null;
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        given(request.getSession()).willReturn(session);
        given(session.getAttribute("reader")).willReturn(reader);

        strategy.handle(request, response);

        verify(response).sendRedirect("/login");
    }

    private Reader reader() {
        return Reader.builder()
                .id(2)
                .lastName("last")
                .firstName("first")
                .middleName("middle")
                .email("email")
                .password("password")
                .build();
    }

    private Periodicals periodical() {
        return new Periodicals(2, "name", "publisher", new BigDecimal("2.22"));
    }
}