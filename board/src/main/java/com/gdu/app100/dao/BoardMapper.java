package com.gdu.app100.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.app100.dto.BoardDto;

@Mapper
public interface BoardMapper {
  
 public int addBoard(BoardDto boardDto);
 public int deleteBoard(int no);
 public BoardDto getBoardDto(int no);
 public List<BoardDto> getBoardList();
 int modifyBoard(BoardDto boardDto);
 


}
