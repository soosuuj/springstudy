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
 $(() => {
	 fnRegisterStaff();
 })
 
 const fnRegisterStaff = () => {
	 $('#btn_register').click(() => {
		 $.ajax({
			 type: 'post',
			 url: '${contextPath}/add.do',
			 data: $('#frm_register').serialize(),
			 dataType: 'json',
			 success: (resData) => {
			   if(resData.addResult === 1){
				   alert('사원 등록이 성공했습니다.');
				   //fnGetStaffList();
				   //fnInit();
			   } else {
				   alert('사원 등록이 실패했습니다.');
			   }
			 },
			 error: (jqXHR) => {
			   if(jqXHR.responseJSON.addResult === 0){
				   alert('사원등록이 실패했습니다.');
			   }
				 
			 }
		 })
	 })
	 
 }
</script>
</head>
<div>
	<h1> 사원등록</h1>
	<div>
		<form id="frm_register">
			<input type="text" name="sno" id="sno" placeholder="사원번호">
			<input type="text" name="name" id="name" placeholder="사원명">
			<input type="text" name="dept" id="dept" placeholder="부서">
			<button type="button" id="btn_register">등록</button>						
		</form>
		
	</div>
</div>
	<hr>
<div>
<h1> 사원조회</h1>
<div>
	<input type="text" name="query"  id="query" placeholder="사원번호입력">
	<button type="button" id="btn_query">조회</button>
	<button type="button" id="btn_list">전체</button>
	
</div>
</div>
<hr>

<div>
	<h1> 사원 목록</h1>
	<div>
	<table border="1">
		<thead>
			<tr>
				<td>사원번호</td>
				<td>사원명</td>
				<td>부서명</td>
				<td>연봉</td>				
			</tr>
			</thead>
			<tbody id="staff_list">
              <tr>
                <td></td>
              </tr>
            </tbody>
	</table>
</div>
</div>
</body>
<script type="text/javascript">
// 사원목록
@GetMapping(value ="list.do", produces="application/json")
public Map<String, Object> list(Model model){
  return staffService.listStaff();
}

@Transactional(readOnly=true)
@Override
public List<StaffDto> listStaff() {
  // TODO Auto-generated method stub
  return staffMapper.getStaffList();
}

package com.gdu.staff.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.staff.dto.StaffDto;

@Mapper
public interface StaffMapper {

public List<StaffDto> getStaffList();
public StaffDto getStaff(String sno);
public int insertStaff(StaffDto staff);


}
<div>
<h1> 사원 목록</h1>
<div>
<table border="1">
	<thead>
		<tr>
			<td>사원번호</td>
			<td>사원명</td>
			<td>부서명</td>
			<td>연봉</td>				
		</tr>
		</thead>
		<tbody id="staff_list">
          <tr>
            <td></td>
          </tr>
        </tbody>
</table>
</script>

</html>
