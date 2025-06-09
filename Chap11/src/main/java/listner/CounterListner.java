package listner;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class CounterListner
 *
 */
@WebListener
public class CounterListner implements ServletContextListener {
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context =sce.getServletContext();
		Integer count=0;
		context.setAttribute("count", count);
	}
	public void contextDestroyed(ServletContextEvent sce) {
		
	}
	
    /**
     * Default constructor. 
     */
    public CounterListner() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */


	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */

}
