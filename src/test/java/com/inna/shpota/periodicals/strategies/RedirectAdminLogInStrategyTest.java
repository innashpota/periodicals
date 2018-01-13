package com.inna.shpota.periodicals.strategies;

import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class RedirectAdminLogInStrategyTest {
    @Test
    public void shouldHandle() throws ServletException, IOException {
        Strategy strategy = new RedirectAdminLogInStrategy();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        strategy.handle(request, response);

        verify(response).sendRedirect("/admin/login");
    }
}