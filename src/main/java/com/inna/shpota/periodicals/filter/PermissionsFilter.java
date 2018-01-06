package com.inna.shpota.periodicals.filter;

import com.inna.shpota.periodicals.domain.Admin;
import com.inna.shpota.periodicals.domain.Reader;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class PermissionsFilter implements Filter {
    private Set<String> adminUri = new HashSet<>();
    private Set<String> readerUri = new HashSet<>();
    private Set<String> unauthorizedUri = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) {
        adminUri.add("/admin");
        adminUri.add("/admin/login");
        adminUri.add("/edit-periodicals");
        adminUri.add("/admin/logout");
        adminUri.add("/create");
        adminUri.add("\\/periodicals\\/edit\\/.*[0-9]");
        adminUri.add("\\/periodicals\\/delete\\/.*[0-9]");
        readerUri.add("/login");
        readerUri.add("/signup");
        readerUri.add("/logout");
        readerUri.add("/profile");
        readerUri.add("\\/periodicals\\/subscribe\\/.*[0-9]");
        readerUri.add("\\/periodicals\\/payment\\/.*[0-9]");
        unauthorizedUri.add("/admin/logout");
        unauthorizedUri.add("/edit-periodicals");
        unauthorizedUri.add("/create");
        unauthorizedUri.add("\\/periodicals\\/edit\\/.*[0-9]");
        unauthorizedUri.add("\\/periodicals\\/delete\\/.*[0-9]");
        unauthorizedUri.add("/logout");
        unauthorizedUri.add("/profile");
        unauthorizedUri.add("\\/periodicals\\/subscribe\\/.*[0-9]");
        unauthorizedUri.add("\\/periodicals\\/payment\\/.*[0-9]");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain)
            throws IOException, ServletException {
        if (req instanceof HttpServletRequest && resp instanceof HttpServletResponse) {
            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) resp;
            HttpSession session = request.getSession();
            String uri = request.getRequestURI();
            Admin admin = (Admin) session.getAttribute("admin");
            Reader reader = (Reader) session.getAttribute("reader");
            if (admin != null && readerUri.stream().anyMatch(uri::matches) ||
                    reader != null && adminUri.stream().anyMatch(uri::matches)) {
                redirectErrorPage(request, response, "Access denied!");
            } else if (admin == null && reader == null &&
                    unauthorizedUri.stream().anyMatch(uri::matches)) {
                redirectErrorPage(request, response, "Please authorization!");
            }
        }
        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() { }

    private void redirectErrorPage(HttpServletRequest request, HttpServletResponse response, String message)
            throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
    }
}
