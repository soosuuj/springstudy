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
    fnInit();
    fnEdit();
    fnRemove();
    fnList();
    fnBack();
  })

  function fnInit(){    
    $('#edit_screen').hide();
    $('#gubun').val(${member.memberNo});
    var modifyResult = '${modifyResult}';
    if(modifyResult != '') {
      if(modifyResult == '1'){
        alert('공지사항이 수정되었습니다.');
      } else {
        alert('공지사항이 수정되지 않았습니다.');
      }
    }
  }
  
  function fnEdit(){
	$('#btn_edit').click(function(){		
      $('#edit_screen').show();
      $('#detail_screen').hide();
	})
  }
  
  function fnRemove(){
		$('#btn_remove').click(function(){		
	      if(confirm('공지사항을 삭제할까요?')){
	        location.href = '${contextPath}/member/delete.do?memberNo=${member.memberNo}';
	      }
		})
	  }
  
  function fnList(){
	$('.btn_list').click(function(){		
      location.href = '${contextPath}/member/list.do';
	})
  }
  
  function fnBack(){
	$('#back').click(function(){		
      $('#edit_screen').hide();
      $('#detail_screen').show();
	})
  }

</script>
</head>
<body>

  <div id="detail_screen">
    <h1>${member.memberNo}번 공지사항</h1>
    <div>아이디 : ${member.id}</div>
    <div>이름 : ${member.name}</div>
    <div>성별 : ${member.gender}</div>
    <hr>
    <div>
        <button type="button" id="btn_edit">편집</button>
        <button type="button" id="btn_remove">삭제</button>
        <!-- GETMapping으로 바꿔서 써야함 -->
        <button type="button" class="btn_list">목록</button>
    </div>
  </div>
  
  
    <div id="edit_screen">
    <div style="cursor: pointer;" id="back">← 뒤로 가기</div>
    <h1>공지사항 편집하기</h1>
    <form method="post" action="${contextPath}/member/modify.do">

      <div>
        <label for="name">이름</label>
        <input type="text" id="name" name="name" placeholder="name">
      </div>
      <div>
        <label for="address">주소</label>
        <input type="text" id="address" name="address">
      </div>
    
      <div>
        <label for="woman">여자</label>
        <input type="radio" id="woman" name="gender" value="woman">
      
        <label for="man">남자</label>
        <input type="radio" id="man" name="gender" value="man">
      
        <label for="none">선택안함</label>
        <input type="radio" id="none" name="gender" value="none">
      </div>
      <hr>
      <div>
        <input type="hidden" name="memberNo" value="${member.memberNo}">
        <button type="submit">편집완료</button>
        <button type="button" class="btn_list">목록</button>
      </div>
    </form>
  </div>
  
 
  
</body>
</html>