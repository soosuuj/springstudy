<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

 <%-- session에 저장된 정보는 el로 확인가능, 
 ${title} 이건 모델에 저장됐을 때
 ${sessionScope.title} session에 저장된걸 갑자기 불러다 쓸 때가 있어서 이걸 권장 --%>
 
 <%-- model에 저장된 title --%>
 ${requestScope.title}
 <br>
 <%-- Session에 저장된 title --%>
 ${sessionScope.title}
 
 <br>
 
 <a href="${contextPath}/article/main.do">세션 초기화하고 main 화면으로 가기</a>
  
 <br>
  
  <a href="${contextPath}/article/confirm.do">세션 확인하기</a>

</body>
</html>