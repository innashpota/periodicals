package com.inna.shpota.periodicals.controller;

import com.inna.shpota.periodicals.annotation.RequestAttributes;
import com.inna.shpota.periodicals.dao.*;
import com.inna.shpota.periodicals.service.EmailService;
import com.inna.shpota.periodicals.strategies.*;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static com.inna.shpota.periodicals.dao.DaoFactory.*;

public class RequestHandler {
    private final Set<? super Strategy> strategies;

    private RequestHandler(Set<? super Strategy> strategies) {
        this.strategies = strategies;
    }

    public static RequestHandler construct(DataSource dataSource) {
        AdminDao adminDao = createAdminDao(dataSource);
        PaymentDao paymentDao = createPaymentDao(dataSource);
        PeriodicalsDao periodicalsDao = createPeriodicalsDao(dataSource);
        ReaderDao readerDao = createReaderDao(dataSource);
        SubscriptionDao subscriptionDao = createSubscriptionDao(dataSource);
        EmailService service = EmailService.getInstance();

        Set<? super Strategy> strategies = new HashSet<>();
        strategies.add(new RedirectPeriodicalsStrategy());
        strategies.add(new PeriodicalsStrategy(periodicalsDao));
        strategies.add(new RedirectAdminLogInStrategy());
        strategies.add(new AdminLogInStrategy());
        strategies.add(new AdminPeriodicalsStrategy(adminDao));
        strategies.add(new EditPeriodicalsStrategy());
        strategies.add(new LogOutStrategy());
        strategies.add(new CreatePeriodicalStrategy());
        strategies.add(new AddPeriodicalStrategy(periodicalsDao));
        strategies.add(new DeletePeriodicalStrategy(periodicalsDao));
        strategies.add(new EditPeriodicalStrategy(periodicalsDao));
        strategies.add(new SavePeriodicalStrategy(periodicalsDao));
        strategies.add(new ReaderLogInStrategy());
        strategies.add(new ReaderPeriodicalsStrategy(readerDao));
        strategies.add(new ReaderSignUpStrategy());
        strategies.add(new ContinueSignUpStrategy(readerDao));
        strategies.add(new SubscribeStrategy(periodicalsDao));
        strategies.add(new ContinueSubscribeStrategy(subscriptionDao));
        strategies.add(new PaymentStrategy(paymentDao));
        strategies.add(new PayStrategy(paymentDao, service));
        strategies.add(new ProfileStrategy(readerDao));
        return new RequestHandler(strategies);
    }

    public Strategy getStrategy(String method, String uri, HttpSession session) {
        Iterator<? super Strategy> iterator = strategies.iterator();
        while (iterator.hasNext()) {
            Object object = iterator.next();
            Strategy strategy = (Strategy) object;
            Annotation annotation = strategy.getClass().getAnnotation(RequestAttributes.class);
            RequestAttributes requestAttributes = (RequestAttributes) annotation;
            String requestMethod = requestAttributes.method();
            String requestUri = requestAttributes.uri();
            if (method.matches(requestMethod) && uri.matches(requestUri)) {
                return strategy;
            }
        }
        session.setAttribute("message", null);
        return new ErrorStrategy();
    }
}
