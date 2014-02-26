package com.ballstoyou.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PostMessage
 **/
@WebServlet("/PostMessage")
public class PostMessageServlet extends HttpServlet {
  private static final long serialVersionUID = 187436524897235619654937851L;

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   **/
  protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

  }
}
