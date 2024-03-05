package test03;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Time extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  response.setContentType("text/html; charset=UTF-8");
	  
	  String select = request.getParameter("select");
	  
	  LocalDate nowDate = LocalDate.now();
	  LocalTime nowTime = LocalTime.now();
	  String localTimeStr = nowTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
	  
	  PrintWriter out = response.getWriter();
	  
	  
	  switch(select) {
	  case "nowdate":
	    out.println("<script>");
	    out.println("alert('요청결과는 " + nowDate + " 입니다.')");
	    out.println("</script>");
	    break;
	  case "nowtime":
	    out.println("<script>");
      out.println("alert('요청결과는 " + localTimeStr + " 입니다.')");
      out.println("</script>");
      break;
	  }
	  

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
