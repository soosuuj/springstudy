package com.gdu.app06.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gdu.app06.dao.BoardDao;
import com.gdu.app06.dto.BoardDto;
import com.gdu.app06.service.BoardServiceImpl;
import com.gdu.app06.service.IBoardService;

@Configuration
public class BoardConfig {
  
  // 1) 스프링 컨테이너에 객체 3개 저장 
  
         // <bean class="BoardDto" id="boardDto1" />
  @Bean  // 메소드boardDto1()의 이름이 빈의 이름, BoardDto 빈의 클래스 타입 (클래스와 아이디)
  public BoardDto boardDto1() {
    return new BoardDto(1, "제목1", "작성자1");
  }
  
  @Bean  // 메소드boardDto1()의 이름이 빈의 이름, BoardDto 빈의 클래스 타입 (클래스와 아이디)
  public BoardDto boardDto2() {
    return new BoardDto(2, "제목2", "작성자2");
  }

  @Bean  // 메소드boardDto1()의 이름이 빈의 이름, BoardDto 빈의 클래스 타입 (클래스와 아이디)
  public BoardDto boardDto3() {
    return new BoardDto(3, "제목3", "작성자3");
  }
  
  @Bean
  public BoardDao boardDao() {
    return new BoardDao();
  }
  
  @Bean
  public IBoardService iBoardService() {
    return new BoardServiceImpl();
  }
  
  
  
  
}
