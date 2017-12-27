package com.inna.shpota.periodicals.strategies;

import com.inna.shpota.periodicals.dao.ReaderDao;
import com.inna.shpota.periodicals.domain.Reader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReaderPeriodicalsStrategy extends Strategy {
    private final ReaderDao readerDao;

    public ReaderPeriodicalsStrategy(ReaderDao readerDao) {
        this.readerDao = readerDao;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        request.getSession().setAttribute("email", email);
        request.getSession().setAttribute("password", password);
        Reader reader = readerDao.getByEmailAndPassword(email, password);
        if (reader != null) {
            request.getSession().setAttribute("reader", reader
            );
            response.sendRedirect("/periodicals");
        } else {
            request.getSession().setAttribute("message", "Either reader login or password is wrong.");
            response.sendRedirect("/login");
        }
    }
}
