package com.inna.shpota.periodicals.strategies;

import com.inna.shpota.periodicals.annotation.RequestAttributes;
import com.inna.shpota.periodicals.dao.ReaderDao;
import com.inna.shpota.periodicals.domain.Reader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequestAttributes(method = "POST", uri = "/signup")
public class ContinueSignUpStrategy extends Strategy {
    private final ReaderDao readerDao;

    public ContinueSignUpStrategy(ReaderDao readerDao) {
        this.readerDao = readerDao;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lastName = request.getParameter("lastName");
        String firstName = request.getParameter("firstName");
        String middleName = request.getParameter("middleName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Reader readerByEmail = readerDao.getByEmail(email);
        if (readerByEmail != null) {
            request.getSession().setAttribute("lastName", lastName);
            request.getSession().setAttribute("firstName", firstName);
            request.getSession().setAttribute("middleName", middleName);
            request.getSession().setAttribute("email", email);
            request.getSession().setAttribute("message", "This email is used!");
            response.sendRedirect("/signup");
        } else {
            Reader newReader = Reader.builder()
                    .lastName(lastName)
                    .firstName(firstName)
                    .middleName(middleName)
                    .email(email)
                    .password(password)
                    .build();
            long id = readerDao.create(newReader);
            Reader reader = readerDao.getById(id);
            request.getSession().setAttribute("reader", reader);
            response.sendRedirect("/periodicals");
        }
    }
}
