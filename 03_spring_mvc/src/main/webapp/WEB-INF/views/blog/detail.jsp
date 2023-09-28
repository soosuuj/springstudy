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
  <!--  controller 마지막꺼 출력
      ${blogDto.blogNo}과 같은 방법인데,
      model.addAttribute("dto", yourDtoObject); // "dto"라는 이름으로 모델에 데이터를 추가 
      이런식으로 모델에 저장이 되어있어야 출력된다.-->
  ${dto.blogNo}
  
</body>
</html>