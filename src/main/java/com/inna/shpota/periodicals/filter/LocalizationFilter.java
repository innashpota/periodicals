package com.inna.shpota.periodicals.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

import static java.util.ResourceBundle.getBundle;

public class LocalizationFilter implements Filter {
    private final Locale enLocale = new Locale("en", "EN");
    private final Locale uaLocale = new Locale("ua", "UA");
    private Map<String, String> enProperties;
    private Map<String, String> uaProperties;

    @Override
    public void init(FilterConfig filterConfig) {
        ResourceBundle bundleEn = getBundle("language", enLocale);
        ResourceBundle bundleUa = getBundle("language", uaLocale, new UTF8Control());
        enProperties = toMap(bundleEn);
        uaProperties = toMap(bundleUa);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain)
            throws IOException, ServletException {
        if (req instanceof HttpServletRequest && resp instanceof HttpServletResponse) {
            HttpServletRequest request = (HttpServletRequest) req;
            HttpSession session = request.getSession();
            String parameter = request.getParameter("language");
            String language = (String) session.getAttribute("language");
            if (language == null && parameter == null || "en".equals(parameter)) {
                session.setAttribute("properties", enProperties);
                session.setAttribute("language", enLocale.getLanguage());
            } else if ("ua".equals(parameter)) {
                session.setAttribute("properties", uaProperties);
                session.setAttribute("language", uaLocale.getLanguage());
            }
            filterChain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() { }

    private Map<String, String> toMap(ResourceBundle resource) {
        Map<String, String> map = new HashMap<>();
        Enumeration<String> keys = resource.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            map.put(key, resource.getString(key));
        }
        return map;
    }
}
