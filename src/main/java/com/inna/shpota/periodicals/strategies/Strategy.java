package com.inna.shpota.periodicals.strategies;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class Strategy {
    public abstract void handle(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException;
}
