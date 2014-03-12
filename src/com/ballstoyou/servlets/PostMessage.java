package com.ballstoyou.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String text = "some text";

      response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
      response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
      response.getWriter().write(text);       // Write response body.
  }
}
