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

        verify(session).setAttribute("lastName", getLastName());
        verify(session).setAttribute("firstName", getFirstName());
        verify(session).setAttribute("middleName", getMiddleName());
        verify(session).setAttribute("email", getEmail());
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
        Reader reader = reader();
        given(request.getParameter("lastName")).willReturn(getLastName());
        given(request.getParameter("firstName")).willReturn(getFirstName());
        given(request.getParameter("middleName")).willReturn(getMiddleName());
        given(request.getParameter("email")).willReturn(getEmail());
        given(request.getParameter("password")).willReturn(getPassword());
        given(readerDao.getByEmail(getEmail())).willReturn(reader);
        given(request.getSession()).willReturn(session);
    }

    private void prepareMockForHandleRedirect(
            ReaderDao readerDao, HttpServletRequest request, HttpSession session
    ) {
        Reader reader = reader();
        given(request.getParameter("lastName")).willReturn(getLastName());
        given(request.getParameter("firstName")).willReturn(getFirstName());
        given(request.getParameter("middleName")).willReturn(getMiddleName());
        given(request.getParameter("email")).willReturn(getEmail());
        given(request.getParameter("password")).willReturn(getPassword());
        given(readerDao.getByEmail(getEmail())).willReturn(null);
        given(readerDao.create(reader)).willReturn(getId());
        given(readerDao.getById(getId())).willReturn(readerWithId());
        given(request.getSession()).willReturn(session);
    }

    private Reader reader() {
        return Reader.builder()
                .lastName(getLastName())
                .firstName(getFirstName())
                .middleName(getMiddleName())
                .email(getEmail())
                .password(getPassword())
                .build();
    }

    private Reader readerWithId() {
        return Reader.builder()
                .id(getId())
                .lastName(getLastName())
                .firstName(getFirstName())
                .middleName(getMiddleName())
                .email(getEmail())
                .password(getPassword())
                .build();
    }

    private long getId() {
        return 2;
    }

    private String getLastName() {
        return "lastName";
    }

    private String getFirstName() {
        return "firstName";
    }

    private String getMiddleName() {
        return "middleName";
    }

    private String getEmail() {
        return "email";
    }

    private String getPassword() {
        return "password";
    }
}