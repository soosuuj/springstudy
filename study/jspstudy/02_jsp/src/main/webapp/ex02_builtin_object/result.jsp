<%@page import="java.net.URLDecoder"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%

  // 요청 인코딩
  request.setCharacterEncoding("UTF-8");

  // 요청 파라미터
  String dir = request.getParameter("dir");
  String filename = URLDecoder.decode( request.getParameter("filename"), "UTF-8");
  
  // dir 의 realPath
  String realPath = application.getRealPath(dir);
  
  // File 객체 생성
  File file = new File(realPath, filename);
  
  // 결과 화면 만들기 -> 가독성이 높은 코드 작성 가능 / 밑에 코드랑 비교해서 확인 / 실제 개발은 이렇게 하면 안됨!!!
  if(!file.exists()){
    out.println("<div>" +filename + "파일이 생성되지 않았습니다.</div>");
  } else {
    out.println("<div>" + filename + "파일이 생성되었습니다.</div>");
  }
  
  


%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%-- 

  <% if(!file.exists()){ %>
  <div><%=filename%> 파일이 생성되지 않았습니다.</div>
  <% }else { %>
  <div><%=filename %> 파일이 생성되었습니다.</div>
  <% } %>
--%>
  
</body>
</html>