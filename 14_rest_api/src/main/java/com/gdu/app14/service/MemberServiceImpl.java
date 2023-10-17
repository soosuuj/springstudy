package com.gdu.app14.service;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.gdu.app14.dao.MemberMapper;
import com.gdu.app14.dto.MemberDto;
import com.gdu.app14.util.PageUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

  private final MemberMapper memberMapper;
  private final PageUtil pageUtil;
  
  @Override
  public Map<String, Object> register(MemberDto memberDto, HttpServletResponse response) {
    
    Map<String, Object> map = null;
    
    response.setContentType("text/plain");  //에러쪽으로 보내는 데이터
    PrintWriter out = null;
    
    try {
      
      out = response.getWriter();  // 예외 처리 때문에 선언과 생성 분리
      int addResult = memberMapper.insertMember(memberDto);
      map = Map.of("addResult", addResult);  // 0,1 들어있음
      
                               
    } catch(DuplicateKeyException e) {   //UNIQUE 칼럼에 중복값이 전달된 경우에 발생함  // java catch -> ajax의 에러로 간다.
      
      response.setStatus(500);  // 메시지와코드를 따로 따로 보냄
      out.print( "이미 사용 중인 아이디입니다.");
      out.flush();
      out.close();
      
    } catch(Exception e) {
      System.out.println(e.getClass().getName());  // 발생한 예외 클래스의 이름 확인
    }
    
    return map;
    
  }
  
  @Override
  public Map<String, Object> getMembers(int page) {
    
    int total = memberMapper.getMemberCount();
    int display = 2;
    
    pageUtil.setPaging(page, total, display);
    
    Map<String, Object> map = Map.of("begin", pageUtil.getBegin()
                                   , "end", pageUtil.getEnd());
    
    List<MemberDto> memberList = memberMapper.getMemberList(map);
    String paging = pageUtil.getAjaxPaging();
    
    return Map.of("memberList", memberList
                , "paging", paging);
    
  }
  
  @Override
  public Map<String, Object> getMember(int memberNo){
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("member", memberMapper.getMember(memberNo));
    return map;
  }
  
  @Override
  public Map<String, Object> modifyMember(MemberDto memberDto) {
    int modifyResult = memberMapper.updateMember(memberDto);
    return Map.of("modifyResult", modifyResult);   // 1아니면 0 null 안들어감
  }
  
  // 삭제
  @Override
  public Map<String, Object> removeMember(int memberNo) {
    return Map.of("removeResult", memberMapper.deleteMember(memberNo));
  }
  

}