package com.inna.shpota.periodicals.strategies;

import com.inna.shpota.periodicals.domain.Admin;
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

public class AdminLogInStrategyTest {
    @Test
    public void shouldHandle() throws ServletException, IOException {
        Strategy strategy = new AdminLogInStrategy();
        Admin admin = new Admin(1, "login", "pass");
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        given(request.getSession()).willReturn(session);
        given(session.getAttribute("admin")).willReturn(admin);

        strategy.handle(request, response);

        verify(response).sendRedirect("/edit-periodicals");
    }

    @Test
    public void shouldForwardToHandle() throws ServletException, IOException {
        Strategy strategy = new AdminLogInStrategy();
        Admin admin = null;
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        given(request.getSession()).willReturn(session);
        given(session.getAttribute("admin")).willReturn(admin);
        given(request.getRequestDispatcher("/jsp/login-admin.jsp")).willReturn(dispatcher);

        strategy.handle(request, response);

        verify(dispatcher).forward(request, response);
    }
}