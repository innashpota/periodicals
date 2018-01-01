package com.inna.shpota.periodicals.strategies;

import com.inna.shpota.periodicals.dao.ReaderDao;
import com.inna.shpota.periodicals.domain.Information;
import com.inna.shpota.periodicals.domain.Reader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProfileStrategy extends Strategy {
    private final ReaderDao readerDao;

    public ProfileStrategy(ReaderDao readerDao) {
        this.readerDao = readerDao;
    }

    @Override

    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Reader reader = (Reader) request.getSession().getAttribute("reader");
        long id = reader.getId();
        List<Information> information = readerDao.getInformationByReader(id);
        request.getSession().setAttribute("information", information);
        request.getRequestDispatcher("/jsp/profile.jsp").forward(request, response);
    }
}
