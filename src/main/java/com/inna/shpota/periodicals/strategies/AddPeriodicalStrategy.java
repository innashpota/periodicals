package com.inna.shpota.periodicals.strategies;

import com.inna.shpota.periodicals.dao.PeriodicalsDao;
import com.inna.shpota.periodicals.domain.Periodicals;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class AddPeriodicalStrategy extends Strategy {
    private final PeriodicalsDao periodicalsDao;

    public AddPeriodicalStrategy(PeriodicalsDao periodicalsDao) {
        this.periodicalsDao = periodicalsDao;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String publisher = request.getParameter("publisher");
        String monthPrice = request.getParameter("monthPrice");
        Periodicals periodical = new Periodicals(name, publisher, new BigDecimal(monthPrice));
        long id = periodicalsDao.create(periodical);
        List<Periodicals> periodicals = periodicalsDao.getAll();
        request.getSession().setAttribute("periodicals", periodicals);
        response.sendRedirect("/edit-periodicals");
    }
}
