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

<h5><a href="${contextPath}/play/write.do">선수정보작성하기</a></h5>

<hr>


<body>
  <div>
    <h3>선수 목록</h3>
    <div>
      <table border="1">
        <thead>
          <tr>
            <td>번호</td>
            <td>선수</td>
            <td>나이</td>
            <td>성별</td>
          </tr>
        </thead>
        <tbody>
          <c:forEach items="${playList}" var="p">
            <tr>
              <td>${p.playNo}</td>
              <td><a href="${contextPath}/play/detail.do=?--수정">${p.player}</a></td>
              <td>${p.age}</td>
              <td>${p.gender}</td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
    
  </div>
  


</body>
</html>