package com.inna.shpota.periodicals.controller;

import com.inna.shpota.periodicals.dao.*;
import com.inna.shpota.periodicals.service.EmailService;
import com.inna.shpota.periodicals.strategies.*;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

import static com.inna.shpota.periodicals.dao.DaoFactory.*;

public class RequestHandler {
    private final Map<Class<? extends Strategy>, Strategy> strategies;

    private RequestHandler(Map<Class<? extends Strategy>, Strategy> strategies) {
        this.strategies = strategies;
    }

    public static RequestHandler construct(DataSource dataSource) {
        AdminDao adminDao = createAdminDao(dataSource);
        PaymentDao paymentDao = createPaymentDao(dataSource);
        PeriodicalsDao periodicalsDao = createPeriodicalsDao(dataSource);
        ReaderDao readerDao = createReaderDao(dataSource);
        SubscriptionDao subscriptionDao = createSubscriptionDao(dataSource);
        EmailService service = EmailService.getInstance();

        Map<Class<? extends Strategy>, Strategy> strategies = new HashMap<>();
        strategies.put(ErrorStrategy.class, new ErrorStrategy());
        strategies.put(RedirectPeriodicalsStrategy.class, new RedirectPeriodicalsStrategy());
        strategies.put(PeriodicalsStrategy.class, new PeriodicalsStrategy(periodicalsDao));
        strategies.put(RedirectAdminLogInStrategy.class, new RedirectAdminLogInStrategy());
        strategies.put(AdminLogInStrategy.class, new AdminLogInStrategy());
        strategies.put(AdminPeriodicalsStrategy.class, new AdminPeriodicalsStrategy(adminDao));
        strategies.put(EditPeriodicalsStrategy.class, new EditPeriodicalsStrategy());
        strategies.put(LogOutStrategy.class, new LogOutStrategy());
        strategies.put(CreatePeriodicalStrategy.class, new CreatePeriodicalStrategy());
        strategies.put(AddPeriodicalStrategy.class, new AddPeriodicalStrategy(periodicalsDao));
        strategies.put(DeletePeriodicalStrategy.class, new DeletePeriodicalStrategy(periodicalsDao));
        strategies.put(EditPeriodicalStrategy.class, new EditPeriodicalStrategy(periodicalsDao));
        strategies.put(SavePeriodicalStrategy.class, new SavePeriodicalStrategy(periodicalsDao));
        strategies.put(ReaderLogInStrategy.class, new ReaderLogInStrategy());
        strategies.put(ReaderPeriodicalsStrategy.class, new ReaderPeriodicalsStrategy(readerDao));
        strategies.put(ReaderSignUpStrategy.class, new ReaderSignUpStrategy());
        strategies.put(ContinueSignUpStrategy.class, new ContinueSignUpStrategy(readerDao));
        strategies.put(SubscribeStrategy.class, new SubscribeStrategy(periodicalsDao));
        strategies.put(ContinueSubscribeStrategy.class, new ContinueSubscribeStrategy(subscriptionDao));
        strategies.put(PaymentStrategy.class, new PaymentStrategy(paymentDao));
        strategies.put(PayStrategy.class, new PayStrategy(paymentDao, service));
        strategies.put(ProfileStrategy.class, new ProfileStrategy(readerDao));
        return new RequestHandler(strategies);
    }

    public Strategy getStrategy(HttpServletRequest request) {
        String uri = request.getRequestURI();
        if ("/".equals(uri)) {
            return strategies.get(RedirectPeriodicalsStrategy.class);
        }
        if ("/periodicals".equals(uri)) {
            return strategies.get(PeriodicalsStrategy.class);
        }
        if ("/admin".equals(uri)) {
            return strategies.get(RedirectAdminLogInStrategy.class);
        }
        if ("/admin/login".equals(uri)) {
            if ("GET".equals(request.getMethod())) {
                return strategies.get(AdminLogInStrategy.class);
            } else {
                return strategies.get(AdminPeriodicalsStrategy.class);
            }
        }
        if ("/edit-periodicals".equals(uri)) {
            return strategies.get(EditPeriodicalsStrategy.class);
        }
        if ("/create".equals(uri)) {
            if ("GET".equals(request.getMethod())) {
                return strategies.get(CreatePeriodicalStrategy.class);
            } else {
                return strategies.get(AddPeriodicalStrategy.class);
            }
        }
        if (uri.matches("\\/periodicals\\/delete\\/.*[0-9]")) {
            return strategies.get(DeletePeriodicalStrategy.class);
        }
        if (uri.matches("\\/periodicals\\/edit\\/.*[0-9]")) {
            if ("GET".equals(request.getMethod())) {
                return strategies.get(EditPeriodicalStrategy.class);
            } else {
                return strategies.get(SavePeriodicalStrategy.class);
            }
        }
        if ("/login".equals(uri)) {
            if ("GET".equals(request.getMethod())) {
                return strategies.get(ReaderLogInStrategy.class);
            } else {
                return strategies.get(ReaderPeriodicalsStrategy.class);
            }
        }
        if ("/signup".equals(uri)) {
            if ("GET".equals(request.getMethod())) {
                return strategies.get(ReaderSignUpStrategy.class);
            } else {
                return strategies.get(ContinueSignUpStrategy.class);
            }
        }
        if ("/logout".equals(uri)) {
            return strategies.get(LogOutStrategy.class);
        }
        if (uri.matches("\\/periodicals\\/subscribe\\/.*[0-9]")) {
            if ("GET".equals(request.getMethod())) {
                return strategies.get(SubscribeStrategy.class);
            } else {
                return strategies.get(ContinueSubscribeStrategy.class);
            }
        }
        if (uri.matches("\\/periodicals\\/payment\\/.*[0-9]")) {
            if ("GET".equals(request.getMethod())) {
                return strategies.get(PaymentStrategy.class);
            } else {
                return strategies.get(PayStrategy.class);
            }
        }
        if ("/profile".equals(uri)) {
            return strategies.get(ProfileStrategy.class);
        }
        request.getSession().setAttribute("message", null);
        return strategies.get(ErrorStrategy.class);
    }
}
