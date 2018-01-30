package com.inna.shpota.periodicals.controller;

import com.inna.shpota.periodicals.strategies.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(JUnitParamsRunner.class)
public class RequestHandlerTest {
    @Test
    @Parameters
    public void shouldToGetStrategy(String method, String uri, Class strategyClass) {
        DataSource dataSource = mock(DataSource.class);
        HttpSession session = mock(HttpSession.class);
        RequestHandler requestHandler = RequestHandler.construct(dataSource);

        Strategy strategy = requestHandler.getStrategy(method, uri, session);

        assertEquals(strategyClass, strategy.getClass());
    }

    private Object[] parametersForShouldToGetStrategy() {
        return new Object[]{
                new Object[]{"GET", "/", RedirectPeriodicalsStrategy.class},
                new Object[]{"GET", "/periodicals", PeriodicalsStrategy.class},
                new Object[]{"GET", "/admin", RedirectAdminLogInStrategy.class},
                new Object[]{"GET", "/admin/login", AdminLogInStrategy.class},
                new Object[]{"POST", "/admin/login", AdminPeriodicalsStrategy.class},
                new Object[]{"GET", "/edit-periodicals", EditPeriodicalsStrategy.class},
                new Object[]{"GET", "/create", CreatePeriodicalStrategy.class},
                new Object[]{"POST", "/create", AddPeriodicalStrategy.class},
                new Object[]{"GET", "/periodicals/delete/4", DeletePeriodicalStrategy.class},
                new Object[]{"GET", "/periodicals/edit/4", EditPeriodicalStrategy.class},
                new Object[]{"POST", "/periodicals/edit/4", SavePeriodicalStrategy.class},
                new Object[]{"GET", "/login", ReaderLogInStrategy.class},
                new Object[]{"POST", "/login", ReaderPeriodicalsStrategy.class},
                new Object[]{"GET", "/signup", ReaderSignUpStrategy.class},
                new Object[]{"POST", "/signup", ContinueSignUpStrategy.class},
                new Object[]{"GET", "/logout", LogOutStrategy.class},
                new Object[]{"GET", "/periodicals/subscribe/4", SubscribeStrategy.class},
                new Object[]{"POST", "/periodicals/subscribe/4", ContinueSubscribeStrategy.class},
                new Object[]{"GET", "/periodicals/payment/4", PaymentStrategy.class},
                new Object[]{"POST", "/periodicals/payment/4", PayStrategy.class},
                new Object[]{"GET", "/profile", ProfileStrategy.class},
                new Object[]{"GET", "/pr654ofile", ErrorStrategy.class}
        };
    }
}