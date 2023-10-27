package com.gdu.myhome.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.gdu.myhome.dao.FreeMapper;
import com.gdu.myhome.dto.FreeDto;
import com.gdu.myhome.util.MyPageUtils;
import com.gdu.myhome.util.MySecurityUtils;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class FreeServiceImpl implements FreeService {

  private final FreeMapper freeMapper;
  private final MyPageUtils myPageUtils;
  private final MySecurityUtils mySecurityUtils;
  
  
  @Override
  public int addFree(HttpServletRequest request) {
    
    String email = request.getParameter("email");
    // < > 이런 값들어오지 않게 방지
    String contents = mySecurityUtils.preventXSS(request.getParameter("contents"));
    
    FreeDto free = FreeDto.builder()
                     .email(email)
                     .contents(contents)
                     .build();
      
    return freeMapper.insertFree(free);// 결과 0, 1 반환하는 방식
  }

  // select 2번하기 떄문에 트랜젝션 "성능향상" 하기 위해 내용 수정이 없는 서비스에 어노테이션 붙임 - 안해도됨
  @Transactional(readOnly = true)
  @Override
  public void loadFreeList(HttpServletRequest request, Model model) {
    
    Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
    int page = Integer.parseInt(opt.orElse("1"));
    
    int display = 10;
    
    int total = freeMapper.getFreeCount();
    
    myPageUtils.setPaging(page, total, display);
    
    Map<String, Object> map = Map.of("begin", myPageUtils.getBegin()
                                   , "end", myPageUtils.getEnd());
    
    List<FreeDto> freeList = freeMapper.getFreeList(map);
    
    model.addAttribute("freeList", freeList);
    model.addAttribute("paging", myPageUtils.getMvcPaging(request.getContextPath()+ "/free/list.do"));
    model.addAttribute("beginNo", total - (page - 1) * display);
    // 11 번은 2페이지 부터 시작 됨/ 11~20 시작값
    
    
  }
    
  @Override
  public int addReply(HttpServletRequest request) {
    
    // 요청파라미터 (댓글 작성 화면에서 받아오는 정보들)
    // 댓글 정보(EMAIL, CONTENTS)
    // 원글 정보(DEPTH, GROUP_NO, GROUP_ORDER)
    String email = request.getParameter("email");
    String contents = request.getParameter("contents");
    int depth = Integer.parseInt(request.getParameter("depth"));
    int groupNo = Integer.parseInt(request.getParameter("groupNo"));
    int groupOrder = Integer.parseInt(request.getParameter("groupOrder"));
    
    
    //원글 DTO
    // 기존댓글 없데이트(원글DTO)
    FreeDto free = FreeDto.builder()
                       .groupNo(groupNo)
                       .groupOrder(groupOrder)
                       .build();
    freeMapper.updateGroupOrder(free);  
    // 서비스 입장에서 사용자에게 의미가 없는 숫자이기 때문에 값을 받아올 수 있ㄴ느데 처리하지 않겠다는의미... 원글 업데이트 끝...
    
    //댓글DTO
    // 댓글 삽입(댓글DTO)
    
    FreeDto reply = FreeDto.builder()
                       .email(email)
                       .contents(contents)
                       .depth(depth + 1)
                       .groupNo(groupNo)
                       .groupOrder(groupOrder + 1)
                       .build();
    int addReplyResult = freeMapper.insertReply(reply);  //,결과 받음 보여줘야하니까 
                       
    return addReplyResult;  // addReplyResult 그대로 반환
    // 컨트롤러 로 addReplyResult 보내 댓글 삽입 결과 1성공, 0 실패 가보자고 하나의 서비스에 업데이틍 인서트 트랜잭션
    }
  
  @Override
  public int removeFree(int freeNo) {
    return freeMapper.deleteFree(freeNo);
  }
  
  @Transactional(readOnly = true)
  @Override
  public void loadSearchList(HttpServletRequest request, Model model) {
    
    // 칼럼하고 쿼리 보내진거 받기
    String column = request.getParameter("column");
    String query = request.getParameter("query");
    
    
    // 검색 결과 개수 구하기 - Mapper작업 필요! 
    Map<String, Object> map = new HashMap<>();
    map.put("column", column);
    map.put("query", query);
    
    int total = freeMapper.getSearchCount(map);
    
    // 페이지 처리할떄 페이지 번호 디스플레이 필요
    
    Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
    String strPage =  opt.orElse("1");
    int page = Integer.parseInt(strPage);
    
    int display = 10;
    
    myPageUtils.setPaging(page, total, display);
    
    //setPagin을 호출하면 begin 과 end 계산 된다...
    // 계산이 끝나면 
    map.put("begin", myPageUtils.getBegin());
    map.put("end", myPageUtils.getEnd());
    
    // 결과 받을 리스트
    List<FreeDto> freeList = freeMapper.getSearchList(map);
    
    model.addAttribute("freeList", freeList);
    model.addAttribute("paging", myPageUtils.getMvcPaging(request.getContextPath()+ "/free/search.do", "column=" + column + "&query=" + query));
    model.addAttribute("beginNo", total - (page - 1) * display);
    
    
  }
  
  
  
  
  
  
}
