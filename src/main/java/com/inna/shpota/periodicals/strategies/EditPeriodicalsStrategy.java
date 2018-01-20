package com.inna.shpota.periodicals.strategies;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditPeriodicalsStrategy extends Strategy {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/edit-periodicals.jsp").forward(request, response);
    }
}
