package com.inna.shpota.periodicals.controller;

import com.inna.shpota.periodicals.strategies.RequestHandler;
import com.inna.shpota.periodicals.strategies.Strategy;
import org.apache.log4j.Logger;

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

@WebServlet(name = "PeriodicalsController",
        urlPatterns = {"", "/periodicals", "/periodicals/*", "/admin", "/admin/*", "/edit-periodicals",
                "/create", "/edit-periodicals/*", "/login", "/signup", "/reader/*", "/profile"}
) //don't write here
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response, "doGet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response, "doPost");
    }


    private void processRequest(
            HttpServletRequest request,
            HttpServletResponse response,
            String method
    ) throws IOException, ServletException {
        LOGGER.info(method + "method has been called");
        Strategy strategy = requestHandler.getStrategy(request);
        strategy.handle(request, response);
    }
}
