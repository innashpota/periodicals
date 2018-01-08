package com.inna.shpota.periodicals.strategies;

import com.inna.shpota.periodicals.domain.Reader;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ReaderLogInStrategyTest {
    @Test
    public void shouldHandle() throws ServletException, IOException {
        Strategy strategy = new ReaderLogInStrategy();
        Reader reader = build();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        given(request.getSession()).willReturn(session);
        given(session.getAttribute("reader")).willReturn(reader);

        strategy.handle(request, response);

        verify(response).sendRedirect("/periodicals");
    }

    @Test
    public void shouldHandleForward() throws ServletException, IOException {
        Strategy strategy = new ReaderLogInStrategy();
        Reader reader = null;
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        given(request.getSession()).willReturn(session);
        given(session.getAttribute("reader")).willReturn(reader);
        given(request.getRequestDispatcher("/jsp/login-reader.jsp")).willReturn(dispatcher);

        strategy.handle(request, response);

        verify(dispatcher).forward(request, response);
    }

    private Reader build() {
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