package com.inna.shpota.periodicals.strategies;

import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CreatePeriodicalStrategyTest {
    @Test
    public void shouldHandle() throws ServletException, IOException {
        Strategy strategy = new CreatePeriodicalStrategy();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        given(request.getRequestDispatcher("/jsp/create.jsp")).willReturn(dispatcher);

        strategy.handle(request, response);

        verify(dispatcher).forward(request, response);
    }
}