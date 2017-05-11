package by.nahorny.mvc.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by Dmitri_Nahorny on 4/4/2017.
 */
@WebListener
public class CustomSessionListener implements HttpSessionListener{
    @Override
    public void sessionCreated(HttpSessionEvent e) {
        HttpSession currentSession = e.getSession();
        currentSession.setAttribute("userLocale", "ru_RU");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent e) {

    }
}
