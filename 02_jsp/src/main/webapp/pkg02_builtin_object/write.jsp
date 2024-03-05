<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<% String contextPath = request.getContextPath(); %>

  <!-- <div><%=contextPath%></div> -->   <!-- /jsp 를 선언한 것. servlet 에서도 사용했음. -->

  <form method="POST" 
        action="<%=contextPath%>/pkg02_builtin_object/save.jsp">
    <div>
      <label for="created-at">작성일자</label>    
      <input type="text" id="created-at" name="created-at" value="<%=LocalDate.now()%>" readonly>
    </div>
    <div>
      <label for="title"></label>
      <input type="text" id="title" name="title">
    </div>
    <div>
      <textarea rows="5" cols="50" name="contents" placeholder="내용"></textarea>
    </div>
    <div>
      <button type="submit">작성완료</button>
      <button type="reset">다시작성</button>
    </div>
  </form>


</body>
</html>