package pkg08_ajax;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetData1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	  // 1시간마다 갱신되는 전국 날씨 정보
	  String spec = "http://www.kma.go.kr/XML/weather/sfc_web_map.xml";
	  
	  // URL 객체
	  URL url = new URL(spec);
	  
	  // HttpURLConnection 객체
	  HttpURLConnection con = (HttpURLConnection)url.openConnection();
	  
	  // 문자 입력 스트림 (전국 날씨 읽기)
	  BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	  
	  // StringBuilder 객체 (전체 XML)
	  StringBuilder builder = new StringBuilder();
	  
	  // String 객체 (한 줄)
	  String line = null;
	  
	  // 문자 입력 스트림을 통해 전국 날씨 읽어 오기
	  while((line = in.readLine()) != null ) {
	    builder.append(line);
	  }
	  
	  // 응답 데이터 타입 & 인코딩
	  response.setContentType("application/xml; charset=UTF-8");
	  
	  // 응답 스트림 생성
	  PrintWriter out = response.getWriter();
	  
	  // 응답 (전국 날씨를 요청한 곳으로 보내기)
	  out.println(builder.toString());
	  
	  out.flush();
	  out.close();
	  
	  
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
