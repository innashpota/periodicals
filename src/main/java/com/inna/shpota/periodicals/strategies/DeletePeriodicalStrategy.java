package com.inna.shpota.periodicals.strategies;

import com.inna.shpota.periodicals.dao.PeriodicalsDao;
import com.inna.shpota.periodicals.domain.Periodicals;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DeletePeriodicalStrategy extends Strategy {
    private final PeriodicalsDao periodicalsDao;

    public DeletePeriodicalStrategy(PeriodicalsDao periodicalsDao) {
        this.periodicalsDao = periodicalsDao;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = extractId(request);
        periodicalsDao.delete(id);
        List<Periodicals> periodicals = periodicalsDao.getAll();
        request.getSession().setAttribute("periodicals", periodicals);
        response.sendRedirect("/edit-periodicals");
    }

    private long extractId(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String postIdString = uri.replace("/edit-periodicals/", "")
                .replace("/delete", "");
        return Long.parseLong(postIdString);
    }
}
