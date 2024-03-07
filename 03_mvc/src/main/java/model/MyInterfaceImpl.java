package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;

/* 뷰 <----------------------> 컨트롤러 <----------------------> 모델 */
/*                                         service(Interface)         */

/*
 * Stirng 을 반환하는건 날짜, 시간을 문자열로 반환한다고 하지만 jsp 자체를 반환하겠다. 라는 것을 의미함.
 * jsp 정보를 가지고 가는 이동방식은 forward (request 정보를 저장하고 이동해야함.)
 * getDate 정보를 request 에 저장해서 저장된 정보를 가지고 forward 를 이용하여 jsp 로 이동한다.
 * Interface가 service 라고 생각할 수 있음
 * 원래는 요청이 아니라 응답을 하는것으로 response 를 사용하는 것이 통상적이지만, response 에는 별도의 저장소가 없기 때문에 request 를 사용하는 것.
 */

public class MyInterfaceImpl implements MyInterface {

  @Override
  public ActionForward getDate(HttpServletRequest request) {
    request.setAttribute("date", DateTimeFormatter.ofPattern("yyyy.MM.dd.").format(LocalDate.now()));
    // return "/view/date.jsp";    // date.jsp 로 전달하는 정보의 이름이 "date"  String 일때는 문자열 반환이 맞는데 actionforward 로 바꿔서 오류 남
    return new ActionForward("/view/date.jsp", false);    // 이게 actionForward 의 view
  }

  @Override
  public ActionForward getTime(HttpServletRequest request) {
    request.setAttribute("time", DateTimeFormatter.ofPattern("HH:mm:ss.SSS").format(LocalTime.now()));
    return new ActionForward("/view/time.jsp", false);
  }

  @Override
  public ActionForward getDateTime(HttpServletRequest request) {
    request.setAttribute("datetime", DateTimeFormatter.ofPattern("yyyy. MM. dd HH:mm:ss.SSS").format(LocalDateTime.now()));
    return new ActionForward("/view/datetime.jsp", false);
  }

}
