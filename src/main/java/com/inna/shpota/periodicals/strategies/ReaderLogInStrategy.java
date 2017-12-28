package com.inna.shpota.periodicals.strategies;

import com.inna.shpota.periodicals.domain.Reader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReaderLogInStrategy extends Strategy {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Reader reader = (Reader) request.getSession().getAttribute("reader");
        if (reader != null) {
            request.getSession().setAttribute("reader", reader);
            response.sendRedirect("/periodicals");
        } else {
            request.getRequestDispatcher("/jsp/login-reader.jsp").forward(request, response);
        }
    }
}
