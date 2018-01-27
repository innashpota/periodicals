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
        prepareMock(readerDao, request, session, reader());

        strategy.handle(request, response);

        verify(session).setAttribute("email", null);
        verify(session).setAttribute("password", null);
        verify(session).setAttribute("message", null);
        verify(session).setAttribute("reader", reader());
        verify(response).sendRedirect("/periodicals");
    }

    @Test
    public void shouldHandleRedirect() throws ServletException, IOException {
        ReaderDao readerDao = mock(ReaderDao.class);
        Strategy strategy = new ReaderPeriodicalsStrategy(readerDao);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        prepareMock(readerDao, request, session, null);

        strategy.handle(request, response);

        verify(session).setAttribute("email", "email");
        verify(session).setAttribute("password", "pass");
        verify(session).setAttribute("message", "Either reader login or password is wrong.");
        verify(response).sendRedirect("/login");
    }

    private void prepareMock(ReaderDao readerDao, HttpServletRequest request, HttpSession session, Reader reader) {
        given(request.getParameter("email")).willReturn("email");
        given(request.getParameter("password")).willReturn("pass");
        given(request.getSession()).willReturn(session);
        given(readerDao.getByEmailAndPassword("email", "pass")).willReturn(reader);
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
}