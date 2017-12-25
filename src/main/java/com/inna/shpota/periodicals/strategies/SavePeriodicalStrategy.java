package com.inna.shpota.periodicals.strategies;

import com.inna.shpota.periodicals.dao.PeriodicalsDao;
import com.inna.shpota.periodicals.domain.Admin;
import com.inna.shpota.periodicals.domain.Periodicals;
import com.inna.shpota.periodicals.domain.Reader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class SavePeriodicalStrategy extends Strategy {
    private final PeriodicalsDao periodicalsDao;

    public SavePeriodicalStrategy(PeriodicalsDao periodicalsDao) {
        this.periodicalsDao = periodicalsDao;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validateAdmin(request, response);
        validateReader(request, response);
        long id = extractId(request);
        updatePeriodical(request, id);
        response.sendRedirect("/edit-periodicals");
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

    private void updatePeriodical(HttpServletRequest request, long id) {
        String name = request.getParameter("name");
        String publisher = request.getParameter("publisher");
        String monthPrice = request.getParameter("monthPrice");
        Periodicals newPeriodical = new Periodicals(id, name, publisher, new BigDecimal(monthPrice));
        periodicalsDao.update(newPeriodical);
        List<Periodicals> periodicals = periodicalsDao.getAll();
        request.getSession().setAttribute("periodicals", periodicals);
    }
}
