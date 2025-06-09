package listner;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class NoAppScopeListner
 * A.java(Servlet),B.java(WebFilter),C.java(ServletContextListener) 
 * 問1.サーバ起動：contextInitialized()が実行（C.java）
 * →A.javaをリクエスト：Aのinit(),BのdoFilter(),AのdoGet()の順に実行
 * →A.javaをリクエスト２回目：BのdoFilter(),AのdoGet()の順に実行
 * →サーバ終了：Aのdestroy(),CのcontextDestroyed()の順に実行
 */
@WebListener
public class NoAppScopeListner implements ServletContextAttributeListener {

    /**
     * Default constructor. 
     */
    public void  appBann(ServletContextAttributeEvent warn) {
        // TODO Auto-generated constructor stub
    	System.err.println("applicationスコープは使用禁止");
    }

	/**
     * @see ServletContextAttributeListener#attributeAdded(ServletContextAttributeEvent)
     */
    public void attributeAdded(ServletContextAttributeEvent event)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextAttributeListener#attributeRemoved(ServletContextAttributeEvent)
     */
    public void attributeRemoved(ServletContextAttributeEvent event)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextAttributeListener#attributeReplaced(ServletContextAttributeEvent)
     */
    public void attributeReplaced(ServletContextAttributeEvent event)  { 
         // TODO Auto-generated method stub
    }
	
}
