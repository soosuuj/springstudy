package com.gdu.app06.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gdu.app06.dao.BoardDao;
import com.gdu.app06.dto.BoardDto;

public class BoardServiceImpl implements IBoardService {
  
  // 서비스는 다오 필요
  private BoardDao boardDao;
  
  @Autowired  // 만들어진 객체를 파라미터로 가져와서 값으로 반환
  public void setBoardDao(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public List<BoardDto> getBoardList() {
    return boardDao.getBoardList();
  }

  @Override
  public BoardDto getBoardByNo(int boardNo) {
    return boardDao.getBoardByNo(boardNo);
  }

}
