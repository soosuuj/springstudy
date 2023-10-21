<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script>
$(function(){    
	fnRemove();
	fnList();

  })
  
function fnRemove(){
	$('#btn_delete').click(function(){		
      if(confirm('게시글을 삭제할까요?')){
        location.href = '${contextPath}/board/delete.do?no=${board.no}';
        alert('삭제 되었습니다.');
      }
	})
  }
 

  function fnList() {
  	$('#btn_list').click(function(){
	  location.href = '${contextPath}/board/list.do';
	})
  }

  

</script>

</head>
<body>
  <div>
    <h1>MvcBoard 게시글 상세화면</h1>
    <h3>${board.no}번 게시글</h3>
    <h3>작성자: ${board.author}</h3>
    <h3>작성일: ${board.postDate}</h3>
    <h3>작성IP: ${board.ip}</h3>
    <h3>조회수: ${board.hit}</h3>
    <h3>제목: ${board.title}</h3>
    <h3>내용</h3>
    <h3>${board.content}</h3> 
    <div>
      <button type="button" id="btn_delete">삭제하기</button>
      <button type="button" id="btn_list">목록보기</button>
    </div>
  </div>
  


</body>
</html>