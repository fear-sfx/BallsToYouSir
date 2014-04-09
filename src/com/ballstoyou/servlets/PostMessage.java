package com.ballstoyou.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PostMessage
 **/
//@WebServlet("/PostMessage/*")
public class PostMessage extends HttpServlet {
/**
	 * 
	 */
	private static final long serialVersionUID = 1183188047248672817L;

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   **/
//  protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	  
//  }
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String msg = request.getParameter("message");
      
//      PrintWriter out = response.getWriter();  
//      response.setContentType("text/html");  
//      out.println("<script type=\"text/javascript\">");  
//      out.println("alert('"+msg+"');");  
//      out.println("logout();");
//      out.println("</script>");

      response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
      response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
      response.getWriter().write(msg);       // Write response body.
  }	
}