package com.inna.shpota.periodicals.strategies;

import com.inna.shpota.periodicals.dao.ReaderDao;
import com.inna.shpota.periodicals.domain.Reader;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ReaderPeriodicalsStrategyTest {
    @Test
    public void shouldHandle() throws ServletException, IOException {
        ReaderDao readerDao = mock(ReaderDao.class);
        Strategy strategy = new ReaderPeriodicalsStrategy(readerDao);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        String email = "email";
        String password = "pass";
        Reader reader = build(email, password);
        given(request.getParameter("email")).willReturn(email);
        given(request.getParameter("password")).willReturn(password);
        given(request.getSession()).willReturn(session);
        given(readerDao.getByEmailAndPassword(email, password)).willReturn(reader);

        strategy.handle(request, response);

        verify(session).setAttribute("email", email);
        verify(session).setAttribute("password", password);
        verify(session).setAttribute("reader", reader);
        verify(response).sendRedirect("/periodicals");
    }

    @Test
    public void shouldHandleRedirect() throws ServletException, IOException {
        ReaderDao readerDao = mock(ReaderDao.class);
        Strategy strategy = new ReaderPeriodicalsStrategy(readerDao);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        String email = "email";
        String password = "pass";
        Reader reader = null;
        given(request.getParameter("email")).willReturn(email);
        given(request.getParameter("password")).willReturn(password);
        given(request.getSession()).willReturn(session);
        given(readerDao.getByEmailAndPassword(email, password)).willReturn(reader);

        strategy.handle(request, response);

        verify(session).setAttribute("email", email);
        verify(session).setAttribute("password", password);
        verify(session).setAttribute("message", "Either reader login or password is wrong.");
        verify(response).sendRedirect("/login");
    }

    private Reader build(String email, String password) {
        return Reader.builder()
                .id(2)
                .lastName("last")
                .firstName("first")
                .middleName("middle")
                .email(email)
                .password(password)
                .build();
    }
}