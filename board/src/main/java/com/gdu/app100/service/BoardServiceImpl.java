package com.gdu.app100.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.gdu.app100.dao.BoardMapper;
import com.gdu.app100.dto.BoardDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {
  
  private final BoardMapper boardMapper;

  @Override
  public int addBoard(BoardDto boardDto) {
    return boardMapper.addBoard(boardDto);
  }


  @Override
  public int deleteBoard(int no) {
      return boardMapper.deleteBoard(no);
  }

  @Override
  public BoardDto getBoardDto(int no) {
    return boardMapper.getBoardDto(no);
  }

  @Override
  public List<BoardDto> getBoardList() {
    return boardMapper.getBoardList();
  }
  

}
