package com.cts.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cts.utils.Room;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ArrayList<String> clients = (ArrayList<String>) request.getServletContext().getAttribute("clients");
		Room.clients = clients;
		
		String user = request.getParameter("username");

		if(!clients.contains(user) && user != " " && user != "") {
			clients.add(request.getParameter("username"));
			Room.clients = clients;
			Cookie userCookie = new Cookie("chatClient", "1");
			userCookie.setMaxAge(-1);
			response.addCookie(userCookie);
			response.sendRedirect("/ChatTheSeventh/index.html");
		} else {
			response.sendRedirect("/ChatTheSeventh/login.html");
		}
	}
}
