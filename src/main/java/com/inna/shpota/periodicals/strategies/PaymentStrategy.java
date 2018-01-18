package com.inna.shpota.periodicals.strategies;

import com.inna.shpota.periodicals.dao.PaymentDao;
import com.inna.shpota.periodicals.domain.Payment;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PaymentStrategy extends Strategy {
    private final PaymentDao paymentDao;

    public PaymentStrategy(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }

    @Override

    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = extractId(request);
        Payment payment = paymentDao.getById(id);
        request.getSession().setAttribute("payment", payment);
        request.getRequestDispatcher("/WEB-INF/jsp/payment.jsp").forward(request, response);
    }

    private long extractId(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String postIdString = uri.replace("/periodicals/payment/", "");
        return Long.parseLong(postIdString);
    }
}
