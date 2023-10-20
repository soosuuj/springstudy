package com.gdu.cucu.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.gdu.cucu.dto.MemberDto;

public interface MemberService {
  
  int addMember(MemberDto memberDto);
  int deleteMember(int memberNo);
  int modifyMember(MemberDto memberDto);
  MemberDto getMember(int memberNo);
  List<MemberDto> getMemberList(); 
  
  public int deleteMember(HttpServletRequest request);

}
