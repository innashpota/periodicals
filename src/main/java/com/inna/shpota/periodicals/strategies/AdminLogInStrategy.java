package com.inna.shpota.periodicals.strategies;

import com.inna.shpota.periodicals.domain.Admin;
import com.inna.shpota.periodicals.domain.Reader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminLogInStrategy extends Strategy {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Reader reader = (Reader) request.getSession().getAttribute("reader");
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        if (reader != null) {
            request.getSession().setAttribute("message", "Access denied!");
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
        if (admin != null) {
            response.sendRedirect("/edit-periodicals");
        } else {
            request.getRequestDispatcher("/jsp/login-admin.jsp").forward(request, response);
        }
    }
}
