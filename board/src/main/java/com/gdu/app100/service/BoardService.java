package com.gdu.app100.service;

import java.util.List;

import com.gdu.app100.dto.BoardDto;

public interface BoardService {
  
  public int addBoard(BoardDto boardDto);
  public BoardDto getBoardDto(int no);
  public List<BoardDto> getBoardList();
  int deleteBoard(int no);


}
