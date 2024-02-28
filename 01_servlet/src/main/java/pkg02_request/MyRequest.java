package pkg02_request;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MyRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	  // 1. 요청 UTF-8 인코딩
	  request.setCharacterEncoding("UTF-8");
	  
	  // 2. 요청 파라미터
	  //  1) 모든 파라미터는 String 타입이다.
	  //  2) 파라미터가 없으면 null 이다.
	  //  3) 파라미터의 값이 없으면 ""(빈 문자열) 이다.
	  
	  /* if 문을 이용한 null / ""(빈 문자열) 처리 */
	  String strNumber = request.getParameter("number");
	  int number = 0;
	  if(strNumber != null && !strNumber.isEmpty())
	    number = Integer.parseInt(strNumber);
	  System.out.println(number);
	  
	  /* Optional<T> 클래스를 이용한 null 처리 */
	  String strNumber2 = request.getParameter("number2");
	  Optional<String> opt = Optional.ofNullable(strNumber2);
	  double number2 = Double.parseDouble(opt.orElse("0").isEmpty() ? "0" : opt.orElse("0"));
	  System.out.println(number2);
	  
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 
	  // 1. 요청 UTF-8 인코딩 (항상 제일 먼저!)
    request.setCharacterEncoding("UTF-8");
    
    // 2. 요청 파라미터
    //  1) name 속성이 파라미터이다.
    //  2) 동일한 name 속성을 가진 입력 요소들은 다음과 같이 처리한다.
    //    (1) type="radio" : 변수 처리한다.
    //    (2) 이외의 경우  : 배열 처리한다.
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    String gender = request.getParameter("gender");
    String[] hobbies = request.getParameterValues("hobbies");
    String[] mobile = request.getParameterValues("mobile");
    
    System.out.println(name);
    System.out.println(email);
    System.out.println(gender);
    System.out.println(Arrays.toString(hobbies));
    System.out.println(Arrays.toString(mobile));
    
    
	}

}
