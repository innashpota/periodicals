package com.inna.shpota.periodicals.strategies;

import com.inna.shpota.periodicals.annotation.RequestAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequestAttributes(uri = "/admin")
public class RedirectAdminLogInStrategy extends Strategy {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/admin/login");
    }
}
