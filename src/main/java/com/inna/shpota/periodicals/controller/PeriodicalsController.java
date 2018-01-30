package com.inna.shpota.periodicals.controller;

import com.inna.shpota.periodicals.strategies.Strategy;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;

/*@WebServlet(name = "PeriodicalsController",
        urlPatterns = {"",
                "/periodicals",
                "/periodicals/*",
                "/admin",
                "/admin/*",
                "/edit-periodicals",
                "/create",
                "/login",
                "/signup",
                "/logout",
                "/profile"})*/
public class PeriodicalsController extends HttpServlet {
    private final static Logger LOGGER = Logger.getLogger(PeriodicalsController.class);
    private RequestHandler requestHandler;

    @Override
    public void init() throws ServletException {
        try {
            Context initContext = new InitialContext();
            DataSource dataSource = (DataSource) initContext.lookup(
                    "java:/comp/env/jdbc/periodicals"
            );
            requestHandler = RequestHandler.construct(dataSource);
            LOGGER.info("Using JDNI lookup got the DataSource: " + dataSource);
        } catch (NamingException e) {
            LOGGER.error("NamingException occurred in PeriodicalsController", e);
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response, "GET");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response, "POST");
    }


    private void processRequest(HttpServletRequest request, HttpServletResponse response, String method)
            throws IOException, ServletException {
        LOGGER.info(method + "method has been called");
        String uri = request.getRequestURI();
        HttpSession session = request.getSession();
        Strategy strategy = requestHandler.getStrategy(method, uri, session);
        strategy.handle(request, response);
    }
}
