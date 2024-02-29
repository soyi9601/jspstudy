package pkg07_Cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ReadCookie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	  // 쿠키 가져오기 (요청 : 클라이언트가 쿠키를 가지고 있다.)
	  Cookie[] cookies = request.getCookies();
	  
	  // 응답
	  response.setContentType("text/html; charset=UTF-8");
	  PrintWriter out = response.getWriter();
	  
	  // 쿠키 확인하기
	  if(cookies != null) {
	    
	    // 쿠키 정보
	    String cookieName = null;
	    String cookieValue = null;
	    String cookiePath = null;
	    int cookieExpire = 0;
	    out.println("<!DOCTYPE html>");
      out.println("<html lang=\"ko\">");
      out.println("<head>");
      out.println("<meta charset=\"UTF-8\">");
      out.println("<title>Insert title here</title>");
      out.println("</head>");
      out.println("<body>");
	    for(Cookie cookie : cookies) {
	      cookieName = cookie.getName();
	      cookieValue = URLDecoder.decode(cookie.getValue(), "UTF-8");
	      cookiePath = cookie.getPath();
	      cookieExpire = cookie.getMaxAge();
	      out.println("<div>쿠키이름 : " + cookieName + "</div>");
	      out.println("<div>쿠키값 : " + cookieValue + "</div>");
	      out.println("<div>쿠키경로 : " + cookiePath + "</div>");
	      out.println("<div>쿠키시간 : " + cookieExpire + "</div>");
	      out.println("<div><button type=\"button\" class=\"btn-delete\" data-cookie-name=\"" + cookieName + "\">삭제</button></div>");
	      
	      out.println("<hr>");
	    }
	    out.println("<script>");
	    out.println("let btnDelete = document.getElementsByClassName(\"btn-delete\")");
	    out.println("for(let i = 0; i < btnDelete.length; i++){");
	    out.println("  btnDelete[i].addEventListener('click', (evt)=>{");
	    out.println("    if(confirm('삭제할까요?')){");
	    out.println("    location.href=`"+ request.getContextPath() +"/deleteCookie?cookieName=${evt.target.dataset.cookieName}`;");
	    out.println("  }");
	    out.println("})");
	    out.println("}");
	    out.println("</script>");
	    out.println("</body>");
	    out.println("</html>");
	  }
	  
	  out.flush();
	  out.close();
	  
	  
	  
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
