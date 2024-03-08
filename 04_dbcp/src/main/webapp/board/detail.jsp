<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세화면</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
</head>
<body>

  <div>제목 : ${board.title}</div>
  <div>내용</div>
  ${board.contents}
  <div>최종수정일 ${board.modified_at}</div>
  <div>작성일 ${board.created_at}</div>
  
  <div>
    <%-- 원하는 action 이 2개이기 때문에 form 에 action 을 주지 않고 btn 에 clickEvent 를 주기 --%>
    <%-- 삭제를 POST 하는게 안전장치 -> GET으로 주면 url 에 텍스트를 입력한다고 삭제가 되면 안되기 때문 --%>
    <form id="frm-btn" 
          method="POST">
      <input type="hidden" name="board_no" value="${board.board_no}">
      <button type="button" id="btn-edit">편집</button>
      <button type="button" id="btn-remove">삭제</button> 
    </form>
  </div>
  <script>
  
    const frmBtn = $('#frm-btn');
    const btnEdit = $('#btn-edit');
    const btnRemove = $('#btn-remove');
    
    btnEdit.on('click', (evt)=>{
    	frmBtn.attr('action', '${contextPath}/board/edit.brd');
    	frmBtn.submit();
    })
    
    btnRemove.on('click', (evt)=>{
    	if(confirm('게시글을 삭제할까요?')) {
    		frmBtn.attr('action', '${contextPath}/board/remove.brd');
    		frmBtn.submit();
    	}
    })
  
  </script>

</body>
</html>