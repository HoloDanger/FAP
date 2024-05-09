
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext attribute = sce.getServletContext();
        attribute.setAttribute("header", "aaa");
        attribute.setAttribute("footer", "aaa");
        attribute.setAttribute("hiddenheader", new java.util.Date());
        attribute.setAttribute("hiddenfooter", "aaaa");
      
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
}