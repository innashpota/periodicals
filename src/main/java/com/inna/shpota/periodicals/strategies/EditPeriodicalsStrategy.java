package com.inna.shpota.periodicals.strategies;

import com.inna.shpota.periodicals.dao.AdminDao;
import com.inna.shpota.periodicals.domain.Admin;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditPeriodicalsStrategy extends Strategy {
    private final AdminDao adminDao;

    public EditPeriodicalsStrategy(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = (String) request.getSession().getAttribute("login");
        String password = (String) request.getSession().getAttribute("password");
        Admin admin = adminDao.getByLoginAndPassword(login, password);
        if (admin != null) {
            request.getSession().setAttribute("admin", admin);
            request.getRequestDispatcher("/jsp/edit-periodicals.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute("message", "Either admin login or password is wrong.");
            response.sendRedirect("/admin/login");
        }
    }
}
