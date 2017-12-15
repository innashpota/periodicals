package com.inna.shpota.periodicals.controller;

import com.inna.shpota.periodicals.dao.AdminDao;
import com.inna.shpota.periodicals.dao.FactoryDao;
import com.inna.shpota.periodicals.domain.Admin;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@WebServlet(name = "PeriodicalsController", urlPatterns = {"", "/home"})
public class PeriodicalsController extends HttpServlet {
    @Override
    public void init() throws ServletException {
        try {
            Context initContext = new InitialContext();
            DataSource dataSource = (DataSource) initContext.lookup(
                    "java:/comp/env/jdbc/periodicals"
            );
            AdminDao adminDao = FactoryDao.createAdminDao(dataSource);
            System.out.println("-----------------ALL------------------------");
            System.out.println(adminDao.getAll());
            System.out.println("-----------------INSERT------------------------");
            long id = adminDao.create(new Admin("admin7", "admin7"));
            System.out.println("ID = " + id);
            System.out.println("-----------------ALL------------------------");
            System.out.println(adminDao.getAll());
        } catch (NamingException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }
}
