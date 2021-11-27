package ru.gb.component;


import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;
import ru.gb.service.SessionsManagerAuth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class ConcurrentSessionStrategyAuth extends ConcurrentSessionControlAuthenticationStrategy {
    //параметр для определения выбора пользователя(true - закрываем прошлую активную сессию)
    private static final String FORCE_PARAMETER_NAME = "force";
    // сервис для управления сессиями
    private final SessionsManagerAuth sessionsManager;

    public ConcurrentSessionStrategyAuth(SessionRegistry sessionRegistry, SessionsManagerAuth sessionsManager) {
        super(sessionRegistry);
        this.sessionsManager = sessionsManager;
        //такую же настройку указывали в конфиге
        super.setExceptionIfMaximumExceeded(true);
        //в нашей стратегии указываем, что активная сессия может быть только 1
        super.setMaximumSessions(1);
    }

    @Override
    public void onAuthentication(Authentication authentication, HttpServletRequest request,
                                 HttpServletResponse response)
            throws SessionAuthenticationException {
        try {
            //отдаем обработку методу суперкласса(он вернет SessionAuthenticationException если активных сессий больше чем 1)
            super.onAuthentication(authentication, request, response);
        } catch (SessionAuthenticationException e) {
            log.debug("onAuthentication#SessionAuthenticationException");
            //получаем детали пользователя текущей сессии(в них можем хранить все, что нам нужно о пользователе)
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            String force = request.getParameter(FORCE_PARAMETER_NAME);

            //если параметр из хидера  'force' = false, значит, пользователь выбрал инвалидировать текущую сессию(по сути она и так будет не валидной)
            if (!Boolean.parseBoolean(force)) {
                log.debug("onAuthentication#Invalidate current session for user: {}", userDetails.getUsername());
                throw e;
            }

            log.debug("onAuthentication#Invalidate old session for user: {}", userDetails.getUsername());
            //удаляем все активные сессии пользователя, кроме текущей
            sessionsManager.deleteSessionExceptCurrentByUser(userDetails.getUsername());

        }
    }
}