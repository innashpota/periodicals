package com.inna.shpota.periodicals.strategies;

import com.inna.shpota.periodicals.dao.PeriodicalsDao;
import com.inna.shpota.periodicals.domain.Periodicals;
import com.inna.shpota.periodicals.domain.Reader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SubscribeStrategy extends Strategy {
    private final PeriodicalsDao periodicalsDao;

    public SubscribeStrategy(PeriodicalsDao periodicalsDao) {
        this.periodicalsDao = periodicalsDao;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Reader reader = (Reader) session.getAttribute("reader");
        if (reader == null) {
            response.sendRedirect("/login");
        } else {
            long id = extractId(request);
            Periodicals periodical = periodicalsDao.getById(id);
            request.getSession().setAttribute("periodical", periodical);
            request.getRequestDispatcher("/jsp/subscribe.jsp").forward(request, response);
        }
    }

    private long extractId(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String postIdString = uri.replace("/periodicals/subscribe/", "");
        return Long.parseLong(postIdString);
    }
}
