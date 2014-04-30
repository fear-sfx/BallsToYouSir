package com.cts.init;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextInitialiser implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) {
		ServletContext appScope = sce.getServletContext();
		final ArrayList<String> clients = new ArrayList<String>();
		appScope.setAttribute("clients", clients);
	}

	public void contextDestroyed(ServletContextEvent sce) {
	}
}