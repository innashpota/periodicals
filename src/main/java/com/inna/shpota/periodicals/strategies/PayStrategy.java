package com.inna.shpota.periodicals.strategies;

import com.inna.shpota.periodicals.dao.PaymentDao;
import com.inna.shpota.periodicals.domain.Payment;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PayStrategy extends Strategy {
    private final PaymentDao paymentDao;

    public PayStrategy(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = extractId(request);
        Payment payment = paymentDao.getById(id);
        Payment newPayment = new Payment(payment.getId(), payment.getSubscriptionId(), payment.getPrice(), true);
        paymentDao.update(newPayment);
        //Letter sent to reader's email
        response.sendRedirect("/periodicals");
    }

    private long extractId(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String postIdString = uri.replace("/periodicals/payment/", "");
        return Long.parseLong(postIdString);
    }
}
