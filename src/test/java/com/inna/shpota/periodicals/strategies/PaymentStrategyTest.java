package com.inna.shpota.periodicals.strategies;

import com.inna.shpota.periodicals.dao.PaymentDao;
import com.inna.shpota.periodicals.domain.Payment;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PaymentStrategyTest {
    @Test
    public void shouldHandle() throws ServletException, IOException {
        PaymentDao paymentDao = mock(PaymentDao.class);
        Strategy strategy = new PaymentStrategy(paymentDao);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        prepareMock(paymentDao, request, session, dispatcher);

        strategy.handle(request, response);

        verify(session).setAttribute("payment", payment());
        verify(dispatcher).forward(request, response);
    }

    private void prepareMock(PaymentDao paymentDao, HttpServletRequest request, HttpSession session, RequestDispatcher dispatcher) {
        given(request.getRequestURI()).willReturn("/periodicals/payment/3");
        given(paymentDao.getById(3)).willReturn(payment());
        given(request.getSession()).willReturn(session);
        given(request.getRequestDispatcher("/WEB-INF/jsp/payment.jsp")).willReturn(dispatcher);
    }

    private Payment payment() {
        return new Payment(3, 2, new BigDecimal("2.22"), true);
    }
}