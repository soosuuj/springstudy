package com.gdu.app05.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gdu.app05.dao.BoardDao;
import com.gdu.app05.dto.BoardDto;

public class BoardServiceImpl implements BoardService {
  
  /*
   * BoardService DI 처리 방법
   * 
   * 1. BoardDao 타입의 BoardDao 객체를 Spring Container에 넣는다. (아래 2가지 방법 중 하나 이용)
   *  1) <bean> 태그            : /WEB-INF/spring/root-context.xml
   *  2) @Configuration + @Bean : com.gdu.app05.config.AppConfig.java // 파일이름은 중요하지 않다.
   *  3) @Conponent
   *
   * 2. @Autowired를 이용해서 Spring Container에서 BoardDao 타입의 객체를 가져온다.
   *  1) 필드에 주입하기 (DI injection)
   *  2) 생성자에 주입하기
   *  3) setter 형식의 메소드에 주입하기 -> setter이름 규칙 틀려도 됨 public void sfe -> 매개변수만 @autowired 가붙은 매개 변수만 본다.
   *  
   */ 
  
  @Autowired
  private BoardDao boardDao;


  @Override
  public List<BoardDto> getBoardList() {
    return boardDao.getBoardList();
  }

}
