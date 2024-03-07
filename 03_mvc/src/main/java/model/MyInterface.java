package model;

import common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;

public interface MyInterface {

  /* 뷰 <-> 컨트롤러 <-> 모델 */
  /* 현재 페이지는 모델! 모델은 작업을해서 컨트롤러에 반환을 해주고 컨트롤러가 다시 반환을 해주는 시스템 */
  /* 요청이 3개였으니까 메소드를 3개 만들어주기 */
  
  ActionForward getDate(HttpServletRequest request);   // 본문이 없음 -> 추상 메소드 
  ActionForward getTime(HttpServletRequest request);
  // String getDateTime(HttpServletRequest request);    <- actionForward 배우기 전에 String 타입으로 선언했으나 변경함
  ActionForward getDateTime(HttpServletRequest request);
  
}
