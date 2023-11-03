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

 
  
</script>

</head>
<body>

  
  <div>
    <form id="frm_search">
      <select name="column" id="column">
        <option value="">선택하세요</option>
        <option value="TITLE">제목</option>
        <option value="GENRE">장르</option>
        <option value="DESCRIPTION">내용</option>
      </select>
      <input type="text" name="searchText" id="searchText">
      <button type="button" id="btn_search">검색</button>
      <button type="button" id="btn_init">초기화</button>
    </form>
  </div>

  
  <hr>
  
  <div>
    <table border="1">
      <thead>
        <tr>
          <td>제목</td>
          <td>장르</td>
          <td>내용</td>
          <td>평점</td>
        </tr>
      </thead>
      <tbody id="movie_list"></tbody>
    </table>
  </div>
  
  <script>
 
  const fnMovieList = () => {
	$.ajax({
		type:'get',
		url: '${contextPath}/searchAllMovies',
		dataType: 'json',
		success: (resData) => {
			alert(resData.message);
			$('#movie_list').empty();
			$.each(resData.list, (i, movie)=> {
				let str = '<tr>';
				str += '<td>' + movie.title + '</td>';
				str += '<td>' + movie.genre + '</td>';
				str += '<td>' + movie.description + '</td>';
				str += '<td>' + movie.star + '</td>';
				str += '</tr>';
				$('#movie_list').append(str);
			})
		}
	})  
  }
  
  
  const fnInit = () => {
	  $('#btn_init').click(() => { 
	    $('#column').val(''); 
	    $('#searchText').val('');
	    fnMovieList();
	  });
	};

  
	// 검색 버튼 클릭 이벤트 핸들러
	const fnMovieSearch = () => {
	  $('#btn_search').click(() => {
	    // 사용자 입력 가져오기
	    const column = $('#column').val();
	    const searchText = $('#searchText').val();

	    // 검색 조건이 선택되지 않았을 때, 경고 메시지를 표시하고 검색 중단
    if (column === '' || searchText === '') {
	      alert('검색 조건과 검색어를 입력하세요.');
	      return;
	    }

	    // 검색 조건과 검색어를 서버에 전송
	    $.ajax({
	      type: 'get',
	      url: '${contextPath}/searchMovie', // 서버의 검색 URL
	      data: { column: column, query: searchText },
	      dataType: 'json',
	      success: (resData) => {
	        alert(resData.message);
	        $('#movie_list').empty();
	        $.each(resData.list, (i, movie) => {
	          let str = '<tr>';
	          str += '<td>' + movie.title + '</td>';
	          str += '<td>' + movie.genre + '</td>';
	          str += '<td>' + movie.description + '</td>';
	          str += '<td>' + movie.star + '</td>';
	          str += '</tr>';
	          $('#movie_list').append(str);
	        });
	      },
	      error: (error) => {
	        alert('검색에 실패했습니다.');
	      },
	    });
	  });
	};
  
  
  
  fnMovieList();
  fnInit();
  fnMovieSearch();
  
  
  </script>

</body>
</html>