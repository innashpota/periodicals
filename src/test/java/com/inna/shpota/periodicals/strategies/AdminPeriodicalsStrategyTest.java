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
        given(request.getParameter("login")).willReturn("login");
        given(request.getParameter("password")).willReturn("pass");
        given(request.getSession()).willReturn(session);
        given(adminDao.getByLoginAndPassword("login", "pass")).willReturn(admin());

        strategy.handle(request, response);

        verify(session).setAttribute("login", null);
        verify(session).setAttribute("password", null);
        verify(session).setAttribute("message", null);
        verify(session).setAttribute("admin", admin());
        verify(response).sendRedirect("/edit-periodicals");
    }

    @Test
    public void shouldHandleRedirect() throws ServletException, IOException {
        AdminDao adminDao = mock(AdminDao.class);
        Strategy strategy = new AdminPeriodicalsStrategy(adminDao);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        given(request.getParameter("login")).willReturn("login");
        given(request.getParameter("password")).willReturn("pass");
        given(request.getSession()).willReturn(session);
        given(adminDao.getByLoginAndPassword("login", "pass")).willReturn(null);

        strategy.handle(request, response);

        verify(session).setAttribute("login", "login");
        verify(session).setAttribute("password", "pass");
        verify(session).setAttribute("message", "Either admin login or password is wrong.");
        verify(response).sendRedirect("/admin/login");
    }

    private Admin admin() {
        return new Admin(2, "login", "pass");
    }
}