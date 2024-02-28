package pkg05_redirect;

import java.io.IOException;
import java.net.URLEncoder;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Destination1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	  /*
     * redirect
     * 
     * 1. 이동할 때 요청과 응답을 모두 전달하지 않는다.
     * 2. 이동 경로를 작성할 때 contextPath부터 작성해야 한다.
     * 3. 클라이언트는 redirect 경로를 확인할 수 있다. 
     * 4. forward 하는 경우
     *   1) 쿼리 insert
     *   2) 쿼리 update
     *   3) 쿼리 delete
     */
	  
	  request.setCharacterEncoding("UTF-8");
	  String luggage = request.getParameter("luggage");
	  
	  response.sendRedirect("/servlet/destination2?luggage=" + URLEncoder.encode(luggage, "UTF-8"));
	  // 이렇게 받아도 한글은 보내주지 않음.. 영어만 보내줌. 한글을 보낼거면 URLEncoder 를 사용해줘야함.
	  
	  
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
