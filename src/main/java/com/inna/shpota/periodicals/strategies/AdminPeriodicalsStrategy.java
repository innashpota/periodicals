package com.inna.shpota.periodicals.strategies;

import com.inna.shpota.periodicals.annotation.RequestAttributes;
import com.inna.shpota.periodicals.dao.AdminDao;
import com.inna.shpota.periodicals.domain.Admin;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RequestAttributes(method = "POST", uri = "/admin/login")
public class AdminPeriodicalsStrategy extends Strategy {
    private final AdminDao adminDao;

    public AdminPeriodicalsStrategy(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        Admin admin = adminDao.getByLoginAndPassword(login, password);
        if (admin != null) {
            session.setAttribute("login", null);
            session.setAttribute("password", null);
            session.setAttribute("message", null);
            session.setAttribute("admin", admin);
            response.sendRedirect("/edit-periodicals");
        } else {
            session.setAttribute("login", login);
            session.setAttribute("password", password);
            session.setAttribute("message", "Either admin login or password is wrong.");
            response.sendRedirect("/admin/login");
        }
    }
}
