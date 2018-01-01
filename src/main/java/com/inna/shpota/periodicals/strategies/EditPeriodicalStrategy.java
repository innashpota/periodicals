package com.inna.shpota.periodicals.strategies;

import com.inna.shpota.periodicals.dao.PeriodicalsDao;
import com.inna.shpota.periodicals.domain.Admin;
import com.inna.shpota.periodicals.domain.Periodicals;
import com.inna.shpota.periodicals.domain.Reader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditPeriodicalStrategy extends Strategy {
    private final PeriodicalsDao periodicalsDao;

    public EditPeriodicalStrategy(PeriodicalsDao periodicalsDao) {
        this.periodicalsDao = periodicalsDao;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validateAdmin(request, response);
        validateReader(request, response);
        long id = extractId(request);
        Periodicals periodical = periodicalsDao.getById(id);
        request.getSession().setAttribute("periodical", periodical);
        request.getRequestDispatcher("/jsp/edit.jsp").forward(request, response);
    }

    private void validateAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        if (admin == null) {
            response.sendRedirect("/periodicals");
        }
    }

    private void validateReader(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Reader reader = (Reader) request.getSession().getAttribute("reader");
        if (reader != null) {
            request.getSession().setAttribute("message", "Access denied!");
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
    }

    private long extractId(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String postIdString = uri.replace("/edit-periodicals/", "")
                .replace("/edit", "");
        return Long.parseLong(postIdString);
    }
}
