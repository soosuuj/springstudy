<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="dt" value="<%=System.currentTimeMillis()%>" />

<jsp:include page="../layout/header.jsp">
  <jsp:param value="업로드게시글작성" name="title"/>
</jsp:include>

<style>
.btn-primary {
  margin: 10px;
}
}

</style>

<div>

  <h1>Upload 게시글 작성하기</h1>
  
  <form method="post" action="${contextPath}/upload/add.do" enctype="multipart/form-data"  class="form-label">
    <div>
      <label for="email" >작성자</label>
      <input type="text" id="email"  value="${sessionScope.user.email}" readonly >
    </div>
    <div>
      <label for="title"  >제목</label>
      <input type="text"  class="form-control" name="title" id="title">
    </div>
    <div>
      <label for="contents"  >내용</label>
      <textarea rows="3" cols="50" name="contents" id="contents"  class="form-control"></textarea>
    </div>
    <div>
      <!-- multiple이 있어야 다중 첨부 가능! -->
      <label for="files"  >첨부</label>
      <input type="file" name="files" id="files" multiple  class="form-control">
    </div>
    <div>
      <input type="hidden" name="userNo" value="${sessionScope.user.userNo}" >
      <button type="submit" class="btn btn-primary">작성완료</button>
    </div>
  </form>
  <!-- 첨부파일 목록을 보여줌 -->
  <div id="file_list"></div>
  
</div>
  
<script>

  const fnFileCheck = () => {
    $('#files').change((ev) => {
      $('#file_list').empty();
      let maxSize = 1024 * 1024 * 100;
      let maxSizePerFile = 1024 * 1024 * 10;
      let totalSize = 0;
      let files = ev.target.files;
      for(let i = 0; i < files.length; i++){
        totalSize += files[i].size;
        if(files[i].size > maxSizePerFile){
          alert('각 첨부파일의 최대 크기는 10MB입니다.');
          $(ev.target).val('');
          $('#file_list').empty();
          return;
        }
        $('#file_list').append('<div>' + files[i].name + '</div>');
      }
      if(totalSize > maxSize){
        alert('전체 첨부파일의 최대 크기는 100MB입니다.');
        $(ev.target).val('');
        $('#file_list').empty();
        return;
      }
    })
  }
  
  fnFileCheck();
  
</script>
  
<%@ include file="../layout/footer.jsp" %>