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
	  fnImge();
  })
  
  function fnImge(){
	  $('#btn_image').click(function(){
		  // 백슬래시를 두 번 사용하여 이스케이프 처리 , 자스언어..
		  var path = encodeURIComponent( 'E:\\study\\assets\\image');
		  var filename = $('#image').val();
		  $('#display').empty();  // 가지고 있는 태그 지우기 remove 안됨 -> 자기가 지워짐
		  $('#display').append('<img src="${contextPath}/ajax4/display.do?path=' + path + '&filename=' + filename + '" width="192px">');
	  })
  }
 

</script>
</head>
<body>

  <%-- HDD에 저장된 이미지를 표시하기 --%>

  <div>
    <select id="image">
      <c:forEach var="n" begin="1" end="10" step="1">
        <option>animal${n}.jpg</option>
      </c:forEach>
    </select>
    <button id="btn_image">이미지 가져오기</button>
  </div>
  
  <hr>
  
  <div id="display"></div>

</body>
</html>