package com.inna.shpota.periodicals.strategies;

import com.inna.shpota.periodicals.dao.PaymentDao;
import com.inna.shpota.periodicals.domain.Payment;
import com.inna.shpota.periodicals.domain.Periodicals;
import com.inna.shpota.periodicals.domain.Reader;
import com.inna.shpota.periodicals.service.EmailService;
import org.junit.Test;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PayStrategyTest {
    @Test
    public void shouldHandle() throws ServletException, IOException, MessagingException {
        PaymentDao paymentDao = mock(PaymentDao.class);
        EmailService service = mock(EmailService.class);
        Strategy strategy = new PayStrategy(paymentDao, service);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        given(request.getRequestURI()).willReturn("/periodicals/payment/3");
        given(paymentDao.getById(3)).willReturn(payment());
        given(request.getSession()).willReturn(session);
        given(session.getAttribute("reader")).willReturn(reader());
        given(session.getAttribute("periodical")).willReturn(periodical());

        strategy.handle(request, response);

        verify(paymentDao).update(paymentWithTruePaid());
        verify(service).send(reader(), periodical(), payment().getPrice());
        verify(response).sendRedirect("/periodicals");
    }

    private Payment payment() {
        return new Payment(3, 2, new BigDecimal("33.00"), false);
    }

    private Payment paymentWithTruePaid() {
        return new Payment(3, 2, new BigDecimal("33.00"), true);
    }

    private Reader reader() {
        return Reader.builder()
                .id(5)
                .lastName("lastName")
                .firstName("firstName")
                .middleName("middleName")
                .email("email")
                .password("password")
                .build();
    }

    private Periodicals periodical() {
        return new Periodicals(4, "name", "publisher", new BigDecimal("24.00"));
    }
}