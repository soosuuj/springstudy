package com.gdu.cucu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.cucu.dto.MemberDto;

@Mapper
public interface MemberMapper {

   int addMember(MemberDto memberDto);
   int deleteMember(int memberNo);
   int modifyMember(MemberDto memberDto);
   MemberDto getMember(int memberNo);
   List<MemberDto> getMemberList(); 
}
