package com.gdu.app05.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.gdu.app05.dao.BoardDao;
import com.gdu.app05.dto.BoardDto;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor   // final field 전용 생성자 @AllArgsConstructor는 오바
                           // @Autowired를 이용한 생성자 주입을 위해서 추가한다.
@Service  // 서비스 계층(Business Layer) 전용 @component, Spring Container에 BoardService boardServiceImpl 객체를 생성해둔다.
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
  
  // 필드 직접 주입 불가 final
  //@Autowired
  private final BoardDao boardDao;
  
  // 생성자를 이용한 주입만 가능
  //주입된 boardService 객체의 변경 방지를 위한 final처리
  // @Autowired  -> 생략해도 작동 스프링 4버전 부터
//  public BoardServiceImpl(BoardDao boardDao) {
//    super();
//    this.boardDao = boardDao;
//  }
//  
  
  
  // setter
  
 

  @Override
  public List<BoardDto> getBoardList() {
    return boardDao.getBoardList();
  }



}
