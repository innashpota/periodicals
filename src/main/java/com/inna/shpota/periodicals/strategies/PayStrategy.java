package com.inna.shpota.periodicals.strategies;

import com.inna.shpota.periodicals.dao.PaymentDao;
import com.inna.shpota.periodicals.domain.Payment;
import com.inna.shpota.periodicals.domain.Periodicals;
import com.inna.shpota.periodicals.domain.Reader;
import com.inna.shpota.periodicals.exception.EmailException;
import com.inna.shpota.periodicals.service.EmailService;
import org.apache.log4j.Logger;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

public class PayStrategy extends Strategy {
    private final static Logger LOGGER = Logger.getLogger(PayStrategy.class);
    private final PaymentDao paymentDao;
    private final EmailService service;

    public PayStrategy(PaymentDao paymentDao, EmailService service) {
        this.paymentDao = paymentDao;
        this.service = service;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = extractId(request);
        Payment payment = paymentDao.getById(id);
        BigDecimal price = payment.getPrice();
        Payment newPayment = new Payment(payment.getId(), payment.getSubscriptionId(), price, true);
        paymentDao.update(newPayment);
        HttpSession session = request.getSession();
        Reader reader = (Reader) session.getAttribute("reader");
        Periodicals periodical = (Periodicals) session.getAttribute("periodical");
        send(response, price, session, reader, periodical);
    }

    private void send(
            HttpServletResponse response,
            BigDecimal price, HttpSession session,
            Reader reader, Periodicals periodical
    ) throws IOException {
        try {
            service.send(reader, periodical, price);
            response.sendRedirect("/periodicals");
            LOGGER.info("Send email");
        } catch (MessagingException e) {
            LOGGER.error("MessagingException occurred in PaymentStrategy", e);
            session.setAttribute("message", null);
            response.sendRedirect("/error");
            throw new EmailException(e);
        }
    }

    private long extractId(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String postIdString = uri.replace("/periodicals/payment/", "");
        return Long.parseLong(postIdString);
    }
}
