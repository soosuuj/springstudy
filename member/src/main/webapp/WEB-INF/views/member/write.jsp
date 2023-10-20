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
    <h1>회원 작성하기</h1>
    <form id="frm_list" method="post" action="${contextPath}/member/save.do">
      <div>
        <label for="id">아이디</label>
        <input type="text" id="id" name="id" placeholder="Member ID">
      </div>
      <div>
        <label for="name">이름</label>
        <input type="text" id="name" name="name" placeholder="name">
      </div>
      <div>
        <label for="address">주소</label>
        <input type="text" id="address" name="address">
      </div>
    
      <div>
        <label for="woman">여자</label>
        <input type="radio" id="woman" name="gender" value="woman">
      
        <label for="man">남자</label>
        <input type="radio" id="man" name="gender" value="man">
      
        <label for="none">선택안함</label>
        <input type="radio" id="none" name="gender" value="none">
      </div>

      <div>
        <button type="submit">작성완료</button>
        <button type="button" id="btn_list">목록</button>
      </div>
    
    </form>
   </div>
    
    
   

</body>
</html>