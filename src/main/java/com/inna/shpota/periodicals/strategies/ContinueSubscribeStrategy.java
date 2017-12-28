package com.inna.shpota.periodicals.strategies;

import com.inna.shpota.periodicals.dao.SubscriptionDao;
import com.inna.shpota.periodicals.domain.Periodicals;
import com.inna.shpota.periodicals.domain.Reader;
import com.inna.shpota.periodicals.domain.Subscription;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ContinueSubscribeStrategy extends Strategy {
    private final SubscriptionDao subscriptionDao;

    public ContinueSubscribeStrategy(SubscriptionDao subscriptionDao) {
        this.subscriptionDao = subscriptionDao;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Reader reader = (Reader) request.getSession().getAttribute("reader");
        String monthQuantity = request.getParameter("monthQuantity");
        Subscription subscription = Subscription.builder()
                .readerId(reader.getId())
                .periodicalsId(extractId(request))
                .monthQuantity(Integer.parseInt(monthQuantity))
                .date(LocalDateTime.now())
                .build();
        Periodicals periodicals = (Periodicals) request.getSession().getAttribute("periodical");
        BigDecimal monthPrice = periodicals.getMonthPrice();
        long paymentId = subscriptionDao.createPaymentBySubscription(subscription, monthPrice);
        request.getSession().setAttribute("paymentId", paymentId);
        response.sendRedirect("/periodicals/payment/" + paymentId);
    }

    private int extractId(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String postIdString = uri.replace("/periodicals/subscribe/", "");
        return Integer.parseInt(postIdString);
    }
}
