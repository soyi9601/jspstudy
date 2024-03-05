package test01;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Request extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
	  response.setContentType("text/html; charset=UTF-8");
	  
	  String id = request.getParameter("id");
    String pw = request.getParameter("password");
    String name = request.getParameter("name");
    String year = request.getParameter("year");
    String month = request.getParameter("month");
    String day = request.getParameter("day");
    String gender = request.getParameter("gender");
    String email = request.getParameter("email");
    
    
    
    PrintWriter out = response.getWriter();
    out.println("<ul>");
    out.println("<li>아이디 : " + id + "</li>");
    out.println("<li>비밀번호 : " + pw + "</li>");
    out.println("<li>이름 : " + name + "</li>");
    out.println("<li>생년월일 : " + year + "년" + month + "월" + day + "일" + "</li>");
    out.println("<li>성별 : " + gender + "</li>");
    out.println("<li>이메일 : " + email + "</li>");
    out.flush();
    out.close();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  doGet(request, response);
	}

}
