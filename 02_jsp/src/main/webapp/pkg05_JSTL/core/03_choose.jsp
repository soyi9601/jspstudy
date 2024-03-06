<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

  <%--
    <c:choose>
    1. else 개념이 있는 if 문 태그이다.
    2. 형식
      <c:choose>
        <c:when test="조건식1">
          실행문
        </c:when>
        <c:when test="조건식2">
          실행문
        </c:when>
        <c:otherwise>
        </c:otherwise>
      </c:choose>
  --%>
  
  <c:set var="age" value="<%=(int)(Math.random()*100+1) %>" scope="page" />
  <c:choose>
    <c:when test="${age >= 20}">
      <c:set var="result" value="성인" />
    </c:when>
    <c:otherwise>
      <c:set var="result" value="미성년자" />
    </c:otherwise>
  </c:choose>
  
  <div>${age}살은 ${result}입니다.</div>
  
  
</body>
</html>