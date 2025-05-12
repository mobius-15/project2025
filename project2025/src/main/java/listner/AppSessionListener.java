package listner;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class AppSessionListener
 *
 */
@WebListener
public class AppSessionListener implements HttpSessionListener {

    /**
     * Default constructor. 
     */
    public AppSessionListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
        HttpSession session = se.getSession();
        System.out.println("[SESSION CREATED] ID: " + session.getId());

        // デフォルト設定
        session.setAttribute("defaultTankCount", 2);
//        session.setAttribute("defaultCapTime", 45); // 分
        session.setAttribute("defaultThrottleRatio", 0.4);
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
    	   System.out.println("[SESSION DESTROYED] ID: " + se.getSession().getId());
    }
	
}
