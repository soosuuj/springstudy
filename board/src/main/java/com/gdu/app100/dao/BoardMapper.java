package com.gdu.app100.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.app100.dto.BoardDto;

@Mapper
public interface BoardMapper {
  
 public int addBoard(BoardDto boardDto);
 public int deleteBoard(int no);
 public BoardDto getBoardDto(int no);
 public List<BoardDto> getBoardList();
 int deleteNotice(int no, HttpServletRequest request);

 


}
