package com.inna.shpota.periodicals.strategies;

import com.inna.shpota.periodicals.dao.SubscriptionDao;
import com.inna.shpota.periodicals.domain.Periodicals;
import com.inna.shpota.periodicals.domain.Reader;
import com.inna.shpota.periodicals.domain.Subscription;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ContinueSubscribeStrategyTest {
    @Test
    public void shouldHandle() throws ServletException, IOException {
        SubscriptionDao subscriptionDao = mock(SubscriptionDao.class);
        Strategy strategy = new ContinueSubscribeStrategy(subscriptionDao);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        prepareMock(subscriptionDao, request, session);

        strategy.handle(request, response);

        verify(response).sendRedirect("/periodicals/payment/" + 4);
    }

    private void prepareMock(SubscriptionDao subscriptionDao, HttpServletRequest request, HttpSession session) {
        Reader reader = reader();
        given(request.getSession()).willReturn(session);
        given(session.getAttribute("reader")).willReturn(reader);
        given(request.getParameter("monthQuantity")).willReturn("1");
        given(session.getAttribute("periodical")).willReturn(periodical());
        given(subscriptionDao.createPaymentBySubscription(any(Subscription.class), eq(new BigDecimal("24.00"))))
                .willReturn(4L);
    }

    private Periodicals periodical() {
        return new Periodicals(4, "name", "publisher", new BigDecimal("24.00"));
    }

    private Reader reader() {
        return Reader.builder()
                .id(3)
                .lastName("lastName")
                .firstName("firstName")
                .middleName("middleName")
                .email("email")
                .password("password")
                .build();
    }
}