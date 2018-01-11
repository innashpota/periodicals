package com.inna.shpota.periodicals.strategies;

import com.inna.shpota.periodicals.dao.PaymentDao;
import com.inna.shpota.periodicals.domain.Payment;
import com.inna.shpota.periodicals.domain.Periodicals;
import com.inna.shpota.periodicals.domain.Reader;
import com.inna.shpota.periodicals.exception.EmailException;
import org.apache.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Properties;

public class PayStrategy extends Strategy {
    private final static Logger LOGGER = Logger.getLogger(PayStrategy.class);
    private final PaymentDao paymentDao;

    public PayStrategy(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = extractId(request);
        Payment payment = paymentDao.getById(id);
        BigDecimal price = payment.getPrice();
        Payment newPayment = new Payment(payment.getId(), payment.getSubscriptionId(), price, true);
        paymentDao.update(newPayment);
        try {
            sendEmail(request, price);
            LOGGER.info("Send email");
        } catch (AddressException e) {
            LOGGER.error("AddressException occurred in PaymentStrategy", e);
            throw new EmailException(e);
        } catch (MessagingException e) {
            LOGGER.error("MessagingException occurred in PaymentStrategy", e);
            throw new EmailException(e);
        }
        response.sendRedirect("/periodicals");
    }

    private long extractId(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String postIdString = uri.replace("/periodicals/payment/", "");
        return Long.parseLong(postIdString);
    }

    private void sendEmail(HttpServletRequest request, BigDecimal price)
            throws UnsupportedEncodingException, MessagingException {
        final String username = "periodicals.ua@gmail.com";
        final String password = "periodicaLs2017";
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        Session session = Session.getDefaultInstance(properties,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username, "Periodicals"));
        HttpSession requestSession = request.getSession();
        Reader reader = (Reader) requestSession.getAttribute("reader");
        String email = reader.getEmail();
        String personal = reader.getLastName() + " " + reader.getFirstName() + " " + reader.getMiddleName();
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(email, personal));
        Periodicals periodical = (Periodicals) requestSession.getAttribute("periodical");
        String periodicalName = periodical.getName();
        message.setSubject("Payment for periodical '" + periodicalName + "'");
        String text = "Payment"
                + "\nPayer:           " + personal
                + "\nPeriodical name: " + periodicalName
                + "\nPrice:           " + price + " ₴";
        message.setText(text);
        Transport.send(message);
    }
}
