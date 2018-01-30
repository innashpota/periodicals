package com.inna.shpota.periodicals.strategies;

import com.inna.shpota.periodicals.annotation.RequestAttributes;
import com.inna.shpota.periodicals.dao.SubscriptionDao;
import com.inna.shpota.periodicals.domain.Periodicals;
import com.inna.shpota.periodicals.domain.Reader;
import com.inna.shpota.periodicals.domain.Subscription;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

import static java.time.LocalDateTime.now;

@RequestAttributes(method = "POST", uri = "\\/periodicals\\/subscribe\\/.*[0-9]")
public class ContinueSubscribeStrategy extends Strategy {
    private final SubscriptionDao subscriptionDao;

    public ContinueSubscribeStrategy(SubscriptionDao subscriptionDao) {
        this.subscriptionDao = subscriptionDao;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Periodicals periodicals = (Periodicals) session.getAttribute("periodical");
        Subscription subscription = extractSubscription(request, session, periodicals);
        BigDecimal monthPrice = periodicals.getMonthPrice();
        long paymentId = subscriptionDao.createPaymentBySubscription(subscription, monthPrice);
        response.sendRedirect("/periodicals/payment/" + paymentId);
    }

    private Subscription extractSubscription(HttpServletRequest request, HttpSession session, Periodicals periodicals) {
        long periodicalsId = periodicals.getId();
        Reader reader = (Reader) session.getAttribute("reader");
        long readerId = reader.getId();
        String monthQuantity = request.getParameter("monthQuantity");
        int quantity = Integer.parseInt(monthQuantity);
        return Subscription.builder()
                .readerId(readerId)
                .periodicalsId(periodicalsId)
                .monthQuantity(quantity)
                .date(now())
                .build();
    }
}
