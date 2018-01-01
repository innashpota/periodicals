package com.inna.shpota.periodicals.strategies;

import com.inna.shpota.periodicals.dao.PeriodicalsDao;
import com.inna.shpota.periodicals.domain.Periodicals;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PeriodicalsStrategy extends Strategy {
    private final PeriodicalsDao periodicalsDao;

    public PeriodicalsStrategy(PeriodicalsDao periodicalsDao) {
        this.periodicalsDao = periodicalsDao;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Periodicals> periodicals = periodicalsDao.getAll();
        request.getSession().setAttribute("periodicals", periodicals);
        request.getRequestDispatcher("/jsp/periodicals.jsp").forward(request, response);
    }
}
