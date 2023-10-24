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
	fnDeleteContact();
	//fnRemove();
	fnList();
	

  })
  
    function fnDeleteContact(){
	$('#btn_delete').click(function(){
	  if(confirm('연락처를 삭제할까요?')){
	    $('#frm_detail').attr('action', '${contextPath}/board/delete.do');
	    $('#frm_detail').submit();
	  }
	})
  }
  
function fnRemove(){
	$('#btn_delete').click(function(){		
      if(confirm('게시글을 삭제할까요?')){
        location.href = '${contextPath}/board/delete.do?no=${board.no}';
        alert('삭제 되었습니다.');
      }
	})
  }
 

  function fnList() {
  	$('#btn_list').click(function(){
	  location.href = '${contextPath}/board/list.do';
	})
  }

  

</script>

</head>
<body>
  <div id="a">
    <h1>MvcBoard 게시글 상세화면</h1>
    <h3>${boardDto.no}번 게시글</h3>
    <h3>작성자: ${boardDto.author}</h3>
    <h3>작성일: ${boardDto.postDate}</h3>
    <h3>작성IP: ${boardDto.ip}</h3>
    <h3>조회수: ${boardDto.hit}</h3>
    <h3>제목: ${boardDto.title}</h3>
    <h3>내용</h3>
    <h3>${boardDto.content}</h3> 
    <div>
      <button type="button" id="btn_delete">삭제하기</button>
      <button type="button" id="btn_edit">수정하기</button>
      <button type="button" id="btn_list">목록보기</button>
    </div>
  </div>
  
  <div id="b">
    <form  id="frm_detail" method="post" action="${contextPath}/board/modify.do">
      <div>
        <div>작성자</div>
        <input type="text" id="author" name="author">
      </div>
      <div>
        <div>제목</div>
        <input type="text" id="title" name="title">
      </div>
      <div>
        <div>내용</div>
        <textarea id="content" name="content"></textarea>
        <input type="hidden" name="no" id="no">
        
      </div>    
      <button>편집완료</button>
    </form>
    
    <script>
      $('#author').val('${boardDto.author}');
      $('#title').val('${boardDto.title}');
      $('#content').val('${boardDto.content}');
      $('#no').val('${boardDto.no}');
    </script>
  </div>    
  

  
  <script>
  $(function(){
	    fnDeleteNotice();
	  })
	  
  // 초기 화면
  $('#a').show();
  $('#b').hide();
  
  // 편집하러가기 클릭
  $('#btn_edit').click(function(){
  	$('#a').hide();
  	$('#b').show();
  })
  
  // 뒤로가기 클릭
  $('#btn_back').click(function(){
    $('#a').show();
    $('#b').hide();
  })
  
  var modifyResult = '${modifyResult}';  // '', '1', '0'
  if(modifyResult !== ''){
  	if(modifyResult === '1'){
  		alert('공지사항이 수정되었습니다.');
  	} else {
  		alert('공지사항이 수정되지 않았습니다.');
  	}
  }
  </script>


</body>
</html>