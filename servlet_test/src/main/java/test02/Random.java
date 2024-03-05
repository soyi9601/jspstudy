package test02;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Random extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	  response.setContentType("text/html; charset=UTF-8");
	  
	  int random1 = (int)(Math.random() * 8 + 2);
	  int random2 = (int)(Math.random() * 9 + 1);
	  PrintWriter out = response.getWriter();
	  out.println("<span>" + random1 + "</span><span>X</span><span>" + random2 + "</span>");
	  
	  
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
