package pkg09_bind;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DataConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	  // ServletContext 데이터 확인
	  System.out.println(request.getServletContext().getAttribute("a"));
	  
	  // HttpSession 데이터 확인
	  System.out.println(request.getSession().getAttribute("b"));
	  
	  // HttpServletRequest 데이터 확인
	  System.out.println(request.getAttribute("c"));
	  
	  // Session(로그인, 장바구니 등 자주 쓰임 꼭! )과 request 는 꼭 외워두기. 70% 이상은 request 를 사용한다고 생각하기.
	  
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
