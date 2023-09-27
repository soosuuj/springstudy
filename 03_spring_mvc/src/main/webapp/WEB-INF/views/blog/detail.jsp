<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <!-- 블로그 넘버 꺼내는 방법 el -> 속성값 그대로 적어주기 , 100 받아옴-->
  <!--  위에 2개 동작 안함, 밑에 2개 같은 의미 -->
  ${blogNo}
  <br>
  ${requestScope.blogNo}  
  <br>
  ${blogDto.blogNo}
  <br>
  ${blogDto.getBlogNo()}
  <br>
  ${dto.blogNo}
</body>
</html>