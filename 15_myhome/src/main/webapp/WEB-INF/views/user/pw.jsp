<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="dt" value="<%=System.currentTimeMillis()%>" />

<jsp:include page="../layout/header.jsp">
  <jsp:param value="로그인" name="title"/>
</jsp:include>
<!-- 파라미터 타이틀을 jsp로 보내시오..-->


  <form method="post" action="${contextPath}/user/login.do">
  <h1>비밀번호 변경</h1>

    <div>
      <label for="pw">비밀번호</label>
      <input type="password" name="pw" id="pw">
      <span id="msg_pw"></span>
    </div>
    <div>
      <label for="pw2">비밀번호 확인</label>
      <input type="password" id="pw2">
      <span id="msg_pw2"></span>
    </div>
    
    <div>
      <button type="submit">비밀번호 수정하기</button>
    
    </div>
  </form>
  
  <div>
    <ul>
      <li><a href="${contextPath}">자동로그인</a>
      <li><a href="${contextPath}">아이디/비밀번호 찾기</a>
    </ul>
  </div>

<%@ include file="../layout/footer.jsp" %>
<!-- 파라미터 전달이 없을 때 쓰는거 -->