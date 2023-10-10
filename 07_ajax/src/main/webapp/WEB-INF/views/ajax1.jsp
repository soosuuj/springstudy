<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>weff667``6euoooop[] 
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script>

  $(function(){
    fnList();
    fnDetail();
  })
  
  function fnList(){
	$('#btn_list').click(function(){
      $.ajax({
    	// 요청
        type: 'get',
    	url: '${contextPath}/ajax1/list.do',
    	
    	//find 제이쿼리 자바스크립트 -> $
		// 클릭한 row의 이름만 뽑아오는 jQury 
		// 파라미터 이름이 있어야 값을 받아옴
		
    	// 응답
    	dataType: 'json',
    	success: function(resData){  // 배열 [][]
    	  $('#list').empty();
    	  $.each(resData, function(i, elem){
    	    $('#list').append('<div class="row"><span>' + elem.name + '</span>, ' + elem.age + '</div>');
    	  })
    	}
      })
	})
  }
  
  // 이벤트 전부터 있었던 대상인 document로!! 잘 모르겠으면 이렇게
	// 위에서 쓰인 이벤트. 이벤트 전부터 있었던 대상, row는 이벤트 전에 없던 애이기 때문에 ('.row') 쓸 수 없음
  
  function fnDetail(){
    $(document).on('click', '.row', function(){
      $.ajax({
        // 요청
        type: 'get',
        url: '${contextPath}/ajax1/detail.do',
        data: 'name=' + $(this).find('span').text(),
        // 응답
        dataType: 'json',
        success: function(resData){
          alert(resData.name + ', ' + resData.age);
        }
      })
    })
  }

</script>
</head>
<body>

  <div>
    <button id="btn_list">목록보기</button>
  </div>
  
  <hr>
  
  <div id="list"></div>

</body>
</html>