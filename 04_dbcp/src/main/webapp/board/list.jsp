<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<style>
  .row > span {
    display: inline-block;
  }
  .row > span:nth-child(3) {
    width: 150px;
  }
  .row > span:last-of-type {
    width: 100px;
  }
  .paging {
    display: flex;
  }
  .paging > div {
    width: 30px;
    height: 20px;
    text-aling: center;
    line-height: 20px;    
  }
  .paging a {
    text-decoration: none;
    color: black;
  }
  .dont-click {
    color: silver;
  }
  .paging .current-page {
    color: limegreen;
  }
</style>
</head>
<body>

  <div>
    <a href="${contextPath}/board/write.brd">새게시글작성</a>
  </div>

  <hr>
  
  <div>
    <span>게시글 개수</span>
    <span>${total}</span>
  </div>
  
  <div>
    <a href="${contextPath}/board/list.brd?page=1&sort=DESC&display=${display}">내림차순</a>
    <span>|</span>
    <a href="${contextPath}/board/list.brd?page=1&sort=ASC&display=${display}">오름차순</a>
  </div>
  
  <div>
    <select id="display">
      <option>20</option>
      <option>50</option>
      <option>100</option>
    </select>
  </div>
  <script>
  document.getElementById('display').value = ${display};
  document.getElementById('display').addEventListener('change', (evt)=>{
	  location.href= '${contextPath}/board/list.brd?page=1&sort=${sort}&display=' + evt.target.value;
  })
  </script>
  
  <div class="paging">${paging}</div>
  
  <div>
    <c:if test="${empty boardList}">
      <div>작성된 게시글이 없습니다. 첫 게시글의 주인공이 되어보세요.</div>
    </c:if>
    <c:if test="${not empty boardList}">
      <c:forEach items="${boardList}" var="board">
        <div class="row">
          <span><input type="checkbox" class="chk-each" value="${board.board_no}"></span>
          <span>${board.board_no}</span>
          <span><a href="${contextPath}/board/detail.brd?board_no=${board.board_no}">${board.title}</a></span>
          <span>${board.created_at}</span>
        </div>      
      </c:forEach>    
      <div>
        <button type="button" id="btn-remove">선택삭제</button>
      </div>
      
      <script>
        const chkEach = $('.chk-each');
        const btnRemove = $('#btn-remove');
        btnRemove.on('click', (evt)=>{
        	if(!confirm('선택한 게시글을 삭제할까요?')){
        		return;
        	}
        	let array = [];
        	$.each(chkEach, (i, elem)=>{
        		if(elem.checked){   // if($(elem).is(':checked')){
        			array.push(elem.value);
        		}
        	})
        	// array           === [3, 2, 1]
        	// array.join(',') === '3,2,1'
        	location.href = '${contextPath}/board/removes.brd?param=' + array.join(',');
        })
      </script>
    </c:if>
     
    <%-- true 아니면 false 가 나온는지 확인. 작성한 게시물이 없으니까 empty = true 가 나올 것 --%>
  </div>
  
  
  
</body>
</html>