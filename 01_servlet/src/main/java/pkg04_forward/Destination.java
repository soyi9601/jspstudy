package pkg04_forward;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Destination extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
	  // 요청
	  request.setCharacterEncoding("UTF-8");
	  
	  String luggage = request.getParameter("luggage");
	  
	  // 응답
	  
	  // 인코딩!
	  response.setContentType("text/html; charset=UTF-8");
	  
	  PrintWriter out = response.getWriter();
	  out.println("<script>");
	  out.println("alert('목적지에 도착했습니다. 수하물 " + luggage + " 도착했습니다.');");
	  out.println("</script>");
	  out.flush();
	  out.close();
	  
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
