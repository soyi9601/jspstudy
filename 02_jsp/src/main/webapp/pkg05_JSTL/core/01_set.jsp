<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <c:set></c:set> 모든 JSP에 위 문장은 쓰인다. 필수 요소로 인식하기 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

  <%-- 
    <c:set></c:set> 태그
    1. Bind 영역에 데이터를 저장할 때 사용한다.
    2. 형식
      <c:set var="속성명" value="값" scope="bind 영역"></c:set>
  --%>
  
  <c:set var="a" value="1" scope="page"></c:set>
  <c:set var="a" value="2" scope="request"></c:set>
  <c:set var="a" value="3" scope="session"></c:set>
  <c:set var="a" value="4" scope="application"></c:set>
  
  <div>${pageScope.a}</div>
  <div>${requestScope.a}</div>
  <div>${sessionScope.a}</div>
  <div>${applicationScope.a}</div>
  
  <%-- 자주 사용하게 될 <c:set> --%>
  <c:set var="contextPath" value="${pageContext.request.contextPath}" scope="page"></c:set>
  <c:set var="contextPath" value="<%=request.getContextPath()%>" />   <%-- scope="page"는 생략 가능 + <c:set></c:set> 사이에 아무 내용이 없기 때문에 <c:set /> 로 작성할 수 있음 --%>
  
  <div>${contextPath}</div>
  
  
  
</body>
</html>