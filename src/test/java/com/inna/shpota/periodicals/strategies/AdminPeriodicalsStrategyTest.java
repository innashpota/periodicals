package com.inna.shpota.periodicals.strategies;

import com.inna.shpota.periodicals.dao.AdminDao;
import com.inna.shpota.periodicals.domain.Admin;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AdminPeriodicalsStrategyTest {
    @Test
    public void shouldHandle() throws ServletException, IOException {
        AdminDao adminDao = mock(AdminDao.class);
        Strategy strategy = new AdminPeriodicalsStrategy(adminDao);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        String login = "login";
        String password = "pass";
        Admin admin = new Admin(2, login, password);
        given(request.getParameter("login")).willReturn(login);
        given(request.getParameter("password")).willReturn(password);
        given(request.getSession()).willReturn(session);
        given(adminDao.getByLoginAndPassword(login, password)).willReturn(admin);

        strategy.handle(request, response);

        verify(session).setAttribute("login", login);
        verify(session).setAttribute("password", password);
        verify(session).setAttribute("admin", admin);
        verify(response).sendRedirect("/edit-periodicals");
    }

    @Test
    public void shouldHandleRedirect() throws ServletException, IOException {
        AdminDao adminDao = mock(AdminDao.class);
        Strategy strategy = new AdminPeriodicalsStrategy(adminDao);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        String login = "login";
        String password = "pass";
        Admin admin = null;
        given(request.getParameter("login")).willReturn(login);
        given(request.getParameter("password")).willReturn(password);
        given(request.getSession()).willReturn(session);
        given(adminDao.getByLoginAndPassword(login, password)).willReturn(admin);

        strategy.handle(request, response);

        verify(session).setAttribute("login", login);
        verify(session).setAttribute("password", password);
        verify(session).setAttribute("message", "Either admin login or password is wrong.");
        verify(response).sendRedirect("/admin/login");
    }
}