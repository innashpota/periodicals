package com.inna.shpota.periodicals.service;

import com.inna.shpota.periodicals.domain.Periodicals;
import com.inna.shpota.periodicals.domain.Reader;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Properties;

public class EmailService {
    private static final String USERNAME = "periodicals.ua@gmail.com";
    private static final String PASSWORD = "periodicaLs2017";
    private static volatile EmailService service;

    private EmailService() { }

    public static EmailService getInstance() {
        EmailService localService = service;
        if (localService == null) {
            synchronized (EmailService.class) {
                localService = service;
                if (localService == null) {
                    service = localService = new EmailService();
                }
            }
        }
        return localService;
    }

    public void send(Reader reader, Periodicals periodicals, BigDecimal price)
            throws UnsupportedEncodingException, MessagingException {
        Properties properties = prepareProperties();
        Session session = Session.getDefaultInstance(properties,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USERNAME, PASSWORD);
                    }
                });
        Message message = prepareMessage(session, reader, periodicals, price);
        Transport.send(message);
    }

    private Message prepareMessage(Session session, Reader reader, Periodicals periodicals, BigDecimal price)
            throws MessagingException, UnsupportedEncodingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(USERNAME, "Periodicals"));
        String email = reader.getEmail();
        String personal = reader.getLastName() + " " + reader.getFirstName() + " " + reader.getMiddleName();
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(email, personal));
        String periodicalName = periodicals.getName();
        message.setSubject("Payment for periodical '" + periodicalName + "'");
        String text = "Payment"
                + "\nPayer:           " + personal
                + "\nPeriodical name: " + periodicalName
                + "\nPrice:           " + price + " ₴";
        message.setText(text);
        return message;
    }

    private Properties prepareProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        return properties;
    }
}
