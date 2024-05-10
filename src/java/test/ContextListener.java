package test;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {
    private static final String MAIN_HEADER = "IT and Project Management Training Philippines - ActiveLearning, Inc.";
    private static final String MAIN_FOOTER = "© 2024 ActiveLearning, Inc. All Rights Reserved.";
    private static final String HEADER_ATTRIBUTE = "header";
    private static final String FOOTER_ATTRIBUTE = "footer";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        if (servletContext != null) {
            servletContext.setAttribute(HEADER_ATTRIBUTE, MAIN_HEADER);
            servletContext.setAttribute(FOOTER_ATTRIBUTE, MAIN_FOOTER);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        if (servletContext != null) {
            String[] attributeNames = {HEADER_ATTRIBUTE, FOOTER_ATTRIBUTE};
            for (String attributeName : attributeNames) {
                servletContext.removeAttribute(attributeName);
            }
        }
    }
}