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



</head>


<body>

  <div>
    <h3>선수 정보 입력</h3>
    <form id="frm_write" method="post" action="${contextPath}/play/add.do">
      
      <div>
        <label for="player">선수</label>
        <input type="text" name="player">
      </div>
      
      <div>
        <label for="age">나이</label>
        <input type="text" name="age">
      </div>
      
      <div>
        <label for="gender">성별</label>
        <input type="text" name="gender">
      </div>

      <div>
        <button type="submit">작성완료</button>
        <button type="reset">초기화</button>
        <button type="button">목록으로</button>
      </div>
    </form>
  </div>
  


</body>
</html>