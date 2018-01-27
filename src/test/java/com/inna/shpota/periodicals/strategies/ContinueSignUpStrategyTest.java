package com.inna.shpota.periodicals.strategies;

import com.inna.shpota.periodicals.dao.ReaderDao;
import com.inna.shpota.periodicals.domain.Reader;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ContinueSignUpStrategyTest {
    @Test
    public void shouldHandle() throws ServletException, IOException {
        ReaderDao readerDao = mock(ReaderDao.class);
        Strategy strategy = new ContinueSignUpStrategy(readerDao);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        prepareMockForHandle(readerDao, request, session);

        strategy.handle(request, response);

        verify(session).setAttribute("lastName", "lastName");
        verify(session).setAttribute("firstName", "firstName");
        verify(session).setAttribute("middleName", "middleName");
        verify(session).setAttribute("email", "email");
        verify(session).setAttribute("message", "This email is used!");
        verify(response).sendRedirect("/signup");
    }

    @Test
    public void shouldHandleRedirect() throws ServletException, IOException {
        ReaderDao readerDao = mock(ReaderDao.class);
        Strategy strategy = new ContinueSignUpStrategy(readerDao);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        prepareMockForHandleRedirect(readerDao, request, session);

        strategy.handle(request, response);

        verify(session).setAttribute("reader", readerWithId());
        verify(response).sendRedirect("/periodicals");
    }

    private void prepareMockForHandle(ReaderDao readerDao, HttpServletRequest request, HttpSession session) {
        prepareMock(request);
        given(readerDao.getByEmail("email")).willReturn(reader());
        given(request.getSession()).willReturn(session);
    }

    private void prepareMockForHandleRedirect(
            ReaderDao readerDao, HttpServletRequest request, HttpSession session
    ) {
        prepareMock(request);
        given(readerDao.getByEmail("email")).willReturn(null);
        given(readerDao.create(reader())).willReturn(2L);
        given(readerDao.getById(2)).willReturn(readerWithId());
        given(request.getSession()).willReturn(session);
    }

    private void prepareMock(HttpServletRequest request) {
        given(request.getParameter("lastName")).willReturn("lastName");
        given(request.getParameter("firstName")).willReturn("firstName");
        given(request.getParameter("middleName")).willReturn("middleName");
        given(request.getParameter("email")).willReturn("email");
        given(request.getParameter("password")).willReturn("password");
    }

    private Reader reader() {
        return Reader.builder()
                .lastName("lastName")
                .firstName("firstName")
                .middleName("middleName")
                .email("email")
                .password("password")
                .build();
    }

    private Reader readerWithId() {
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