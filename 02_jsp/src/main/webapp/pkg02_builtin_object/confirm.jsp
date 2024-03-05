<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


  <%
  
    // 업로드 된 파일 목록 가져오기
    String uploadPath = application.getRealPath("upload");
    File uploadDir = new File(uploadPath);
    File[] uploadFiles = uploadDir.listFiles();
  
    // 가장 최근에 업로드 한 파일명 출력하기
    out.println("<div>최근 업로드 파일명 : " + session.getAttribute("uploadName") + "</div>");
    
    // 결과 화면 만들기
    for(File uploadFile : uploadFiles) {
      out.println("<div>");
      out.println("<a href=\"" + uploadFile.getPath() + "\">" + uploadFile.getName() + "</a>" );
      out.println("</div>");
    }
  %>
  
  <%--
  
  <% for (File uploadFile : uploadFiles) { %>
  <div>
    <a href="<%=uploadFile.getPath()%>"><%=uploadFile.getName()%></a>
  </div>
  <% } %>
   --%>
   
  <div>
    <a href="<%=request.getContextPath()%>/pkg02_builtin_object/write.jsp">작성화면</a>
  </div>

</body>
</html>