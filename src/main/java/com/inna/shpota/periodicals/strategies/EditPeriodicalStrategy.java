package com.inna.shpota.periodicals.strategies;

import com.inna.shpota.periodicals.dao.PeriodicalsDao;
import com.inna.shpota.periodicals.domain.Periodicals;

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
        long id = extractId(request);
        Periodicals periodical = periodicalsDao.getById(id);
        request.getSession().setAttribute("periodical", periodical);
        request.getRequestDispatcher("/jsp/edit.jsp").forward(request, response);
    }

    private long extractId(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String postIdString = uri.replace("/edit-periodicals/", "")
                .replace("/edit", "");
        return Long.parseLong(postIdString);
    }
}
