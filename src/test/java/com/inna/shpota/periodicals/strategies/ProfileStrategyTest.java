package com.inna.shpota.periodicals.strategies;

import com.inna.shpota.periodicals.dao.ReaderDao;
import com.inna.shpota.periodicals.domain.Information;
import com.inna.shpota.periodicals.domain.Reader;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static java.time.Month.DECEMBER;
import static java.util.Collections.singletonList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ProfileStrategyTest {
    @Test
    public void shouldHandle() throws ServletException, IOException {
        ReaderDao readerDao = mock(ReaderDao.class);
        Strategy strategy = new ProfileStrategy(readerDao);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        given(request.getSession()).willReturn(session);
        given(session.getAttribute("reader")).willReturn(reader());
        given(readerDao.getInformationByReader(2)).willReturn(information());
        given(request.getRequestDispatcher("/WEB-INF/jsp/profile.jsp")).willReturn(dispatcher);

        strategy.handle(request, response);

        verify(session).setAttribute("information", information());
        verify(dispatcher).forward(request, response);
    }

    private List<Information> information() {
        return singletonList(Information.builder()
                .periodicalsPublisher("publisher")
                .periodicalsName("name")
                .periodicalsMonthPrice(new BigDecimal("3.33"))
                .subscriptionDate(LocalDateTime.of(2017, DECEMBER, 14, 13, 35))
                .monthQuantity(3)
                .paymentPaid(true)
                .build()
        );
    }

    private Reader reader() {
        return Reader.builder()
                .id(2)
                .lastName("lastName")
                .firstName("firstName")
                .middleName("middleName")
                .email("email")
                .password("password")
                .build();
    }
}