package com.gdu.cucu.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.gdu.cucu.dao.MemberMapper;
import com.gdu.cucu.dto.MemberDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

  private final MemberMapper memberMapper;
  
  @Override
  public int addMember(MemberDto memberDto) {
    return memberMapper.addMember(memberDto);
  }
  
  @Override
    public int deleteMember(int memberNo) {
      return memberMapper.deleteMember(memberNo);
    }
  
  @Override
    public int modifyMember(MemberDto memberDto) {
      return memberMapper.modifyMember(memberDto);
    }
  
  @Override
    public MemberDto getMember(int memberNo) {
      return memberMapper.getMember(memberNo);      
    }
  
  @Override
    public List<MemberDto> getMemberList() {
      return memberMapper.getMemberList();
    }
  
  
  
  
  
  @Override
  public int deleteMember(HttpServletRequest request) {
    Optional<String> opt = Optional.ofNullable(request.getParameter("memberNo"));
    int memberNo = Integer.parseInt(opt.orElse("0"));
    return memberMapper.deleteMember(memberNo);
  }


}
