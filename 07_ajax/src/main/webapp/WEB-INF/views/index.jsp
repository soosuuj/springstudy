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
    <h3><a href="${contextPath}/ajax1.do">연습하러가기-1</a></h3>
    <h3><a href="${contextPath}/ajax2.do">연습하러가기-2</a></h3>
    <h3><a href="${contextPath}/ajax3.do">연습하러가기-3</a></h3>
    <h3><a href="${contextPath}/ajax4.do">연습하러가기-4</a></h3>
  </div>
  

</body>
</html>