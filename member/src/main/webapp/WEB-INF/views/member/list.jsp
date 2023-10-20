<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    .title_text {
        color: gray; 
    }
</style>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script>

</script>

</head>
<body>

  <div>
    <h1 class="title_text">모임 회원 정보</h1>
    <img alt="로고" src="../resources/image/google.png">
  </div>
  
  <hr>
  
    <div>
        <a href="${contextPath}/member/write.do">회원등록하러가기</a>
    
    
    <table border="1">
      <thead>
        <tr>
          <td>번호</td>
          <td>아이디</td>
          <td>이름</td>
        </tr>
      </thead>
      <tbody>
        <c:if test="${empty memberList}">
          <tr>
            <td colspan="3">등록된 회원정보가 없습니다.</td>
          </tr>
        </c:if>
        <c:if test="${not empty memberList}">
          <c:forEach items="${memberList}" var="member">
            <tr>
              <td>${member.memberNo}</td>
              <td><a href="${contextPath}/member/detail.do?memberNo=${member.memberNo}">${member.id}</a></td>
              <td>${member.name}</td>            
            </tr>
          </c:forEach>
        </c:if>

      </tbody>
    </table>
  </div>
  
  

</body>
</html>