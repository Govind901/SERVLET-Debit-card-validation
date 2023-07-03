package Connections;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Application Lifecycle Listener implementation class Listner
 *
 */
public class Listner implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public Listner() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    	connectionn obj=new connectionn();
    	ServletContext context=sce.getServletContext();
    	context.setAttribute("dbcon", obj);
    	System.out.println("server started");
    }
	
}
