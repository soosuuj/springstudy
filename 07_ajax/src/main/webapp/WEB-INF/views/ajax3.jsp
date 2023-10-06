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
    fnList();
    fnDetail();
  })
  
  function fnList(){
	$('#btn_list').click(function(){
      $.ajax({
    	// 요청
        type: 'get',
    	url: '${contextPath}/ajax3/list.do',
    	// 응답
    	dataType: 'json',
    	success: function(resData){
    	  $('#list').empty();
    	  $.each(resData, function(i, elem){
    		  // 데이터 속성에 넣기
    	    $('#list').append('<div class="row" data-name="'+ elem.name + '">' + elem.name + ', ' + elem.age + '</div>');
    	  })
    	}
      })
	})
  }
  
  function fnDetail(){
    $(document).on('click', '.row', function(){
      $.ajax({
                         // 요청 (JSON 데이터를 서버로 보내기)
                         // 요청으로 json 자체를 보낼 수 있음
        
        type: 'post',    // post ★방식(필수)은 서버로 보낼 데이터를 요청 본문에 저장하는 방식이다. -> 주소창에 보이지 않아 보안이 좋음
        url: '${contextPath}/ajax3/detail.do',
        	// data-name="뽀로로", 데이터 속성을 꺼내온다 
        //data: 'name=' + $(this).data('name'),
        // json 내장객체 자바스크립트
        
        contentType: 'application/json',   // 서버로 보내는 요청 데이터의 타입(JSON), 'application/json' 자바가 알고 있는 타입이라 이렇게 적어야함, json이라고 적으면 자바가 모름..
        data: JSON.stringify({   // 문자열 형식의 JSON(자바스크립트)데이터를 보낸다. 파라미터로 보내는 방식이 아니다.
        	    'name': $(this).data('name')
       	      }),
        
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