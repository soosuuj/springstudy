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
      fnList();
    })
  
    function fnList() {
    	$('#btn_list').click(function(){
  	  location.href = '${contextPath}/member/list.do';
  	})
    }

</script>

</head>
<body>

  <div>
    <h1>MvcBoard 작성화면</h1>
    <form id="frm_list" method="post" action="${contextPath}/member/save.do">
      <div>
        <label for="writer">작성자</label>
        <input type="text" id="writer" name="writer">
      </div>
      <div>
        <label for="title">제목</label>
        <input type="text" id="title" name="title">
      </div>
      <div>
        <label for="content">내용</label>
        <textarea id="content" name="content"></textarea>
      </div>
    

      <div>
        <button type="submit">저장하기</button>
        <button type="reset">다시작성</button>
        <button type="button" id="btn_list">목록</button>
      </div>
    
    </form>
   </div>
    
    
   

</body>
</html>