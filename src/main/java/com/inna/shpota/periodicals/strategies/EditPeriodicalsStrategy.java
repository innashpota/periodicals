package com.inna.shpota.periodicals.strategies;

import com.inna.shpota.periodicals.dao.AdminDao;
import com.inna.shpota.periodicals.domain.Admin;
import com.inna.shpota.periodicals.domain.Reader;

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
        validateReader(request, response);
        validateAdmin(request);
        String login = (String) request.getSession().getAttribute("login");
        String password = (String) request.getSession().getAttribute("password");
        long id = adminDao.getByLoginAndPassword(login, password);
        if (id > 0) {
            Admin admin = adminDao.getById(id);
            request.getSession().setAttribute("admin", admin);
            request.getRequestDispatcher("/jsp/edit-periodicals.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute("message", "Either admin login or password is wrong.");
            response.sendRedirect("/admin/login");
        }
    }

    private void validateReader(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Reader reader = (Reader) request.getSession().getAttribute("reader");
        if (reader != null) {
            request.getSession().setAttribute("message", "Access denied!");
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
    }

    private void validateAdmin(HttpServletRequest request) {
        Admin attributeAdmin = (Admin) request.getSession().getAttribute("admin");
        if (attributeAdmin != null) {
            request.getSession().setAttribute("login", attributeAdmin.getLogin());
            request.getSession().setAttribute("password", attributeAdmin.getPassword());
        }
    }
}
