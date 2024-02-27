package pkg02_request;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MyRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	  // 1. 요청 UTH-8 인코딩
	  request.setCharacterEncoding("UTF-8");
	  
	  // 2. 요청 파라미터
	  String strNumber = request.getParameter("number");
	  int number = Integer.parseInt(strNumber);
	  System.out.println(number);
	  
	  String strNumber2 = request.getParameter("number2");
	  double number2 = Double.parseDouble(strNumber2);
	  System.out.println(number2);
	  
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
