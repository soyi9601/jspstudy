package pkg01_lifecycle;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * Servlet
 * 
 * 1. 웹 브라우저를 통해서 실행되는 Java 클래스이다.
 * 2. 클라이언트의 요청을 받는 Java 클래스이다.
 * 3. jakarta.servlet.http.HttpServlet 클래스를 상속 받는다.
 *    (Tomcat 이 지원하는 클래스이므로 Tomcat 이 없으면 실행할 수 없다.)
 * 4. 동일한 프로젝트(컨텍스트)에서는 동일한 이름의 Servlet 이 존재할 수 없다.
 *    (Servlet이 저장되는 장소가 JSP/Servlet Container:톰캣이기 때문이다.) * 
 */

/*
 * 실행 주소(URL)
 * 
 * 1. 형식
 *    프로토콜(protocol)://호스트(host):포트번호(port)/컨텍스트패스(contextPath)/URLMapping
 * 2. contextPath
 *    1) 프로젝트 경로를 의미한다.
 *    2) 프로젝트를 생성할 때 결정한다.
 *    3) 프로젝트 속성(Properties)에서 변경할 수 있다.
 *        프로젝트 우클릭 - 속성 - Web Project Settings
 * 3. URLMapping
 *    1) 프로젝트 내부 경로를 의미한다.
 *    2) Servlet을 생성할 때 결정한다.
 *    3) 변경 방법
 *      (1) @WebServlet Annotation
 *      (2) WEB-INF/web.xml : 필요한 코드를 추가해서 수정가능.
 */

/**
 * Servlet implementation class LifeCycle
 */

@WebServlet("/life")  /* http://localhost:8080/servlet/life 주소를 입력하면 현재 Servlet이 실행된다. */
public class LifeCycle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     * 
     * 생성자
     * 1. Servlet 생성할 때 호출된다.
     * 2. 생성자 호출 이후에는 자동으로 init() 메소드가 호출된다.
     */
    public LifeCycle() {
      super();
      System.out.println("LifeCycle 생성자 호출");
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 * 
	 * init() 메소드
	 * 1. Servlet 환경 설정을 담당하는 메소드이다.
	 * 2. init() 메소드 호출 뒤 자동으로 service() 메소드가 호출된다.
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init() 메소드 호출");
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
