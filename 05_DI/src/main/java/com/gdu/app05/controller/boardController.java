package com.gdu.app05.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gdu.app05.service.BoardService;
import com.gdu.app05.service.BoardServiceImpl;

@Controller
public class boardController {
  
  //private BoardService boardService = new BoardServiceImpl();
  // 더이상 new 쓰지 않는다 2단계. 
  // 1) 컨테이너에 서비스 넣는다 (넣는방법 
  //    1) <bean>태그, 2) @configuration + @bean, 3) 
  // 2) 컨터에너에서 서비스 빼낸다 
  
  //
  
  
  /*
   * DI
   * 1. Dependency Injection
   * 2. Spring Container에 저장된 객체를 가져오는 방식이다.
   * 3. 주요 Annotation
   *  1) @Inject
   *    (1) javax.inject.Inject
   *    (2) 타입이 일치하는 객체를 찾아서 가져온다. 없으면 오류가 발생한다.
   *    (3) 동일한 타입의 객체가 2개 이상 있다면(뭔가 잘못된 상황) 이름이 일치하는 객체를 가져온다.
   *        그래도 없으면 오류임...
   *   2) @Resource
   *    (1) javax.annotation.Resource (jar 필요)
   *    (2) javax-annotation-api dependency를 pom.xml에 추가해야 사용할 수 있다(jar필요)
   *    <!-- https://mvnrepository.com/artifact/javax.annotation/javax.annotation-api -->
              <dependency>
                  <groupId>javax.annotation</groupId>
                  <artifactId>javax.annotation-api</artifactId>
                  <version>1.3.2</version>
              </dependency>
        (3) 이름이 일치하는 객체를 찾아서 가져온다. 없으면 오류가 발생한다.
      3) @AutoWired
         (1) org.springframework.beans.factory.annotation.Autowired
         (2) @Inject 기반의 Spring Annotation이다. (타입 기반)
         (3) 객체 이름을 기준으로 가져올 수 있도록 @Qualifier(식별하다) org.springframework.beans.factory.annotation.Quqlifier)를 사용할 수 있다.
      
   */
  
  
  /*
   * BoardService DI 처리 방법
   * 
   * 1. BoardService 타입의 BoardServiceImpl 객체를 Spring Container에 넣는다. (아래 2가지 방법 중 하나 이용)
   *  1) <bean> 태그            : /WEB-INF/spring/root-context.xml
   *  2) @Configuration + @Bean : com.gdu.app05.config.AppConfig.java // 파일이름은 중요하지 않다.
   *  3) @Conponent
   *
   * 2. @Autowired를 이용해서 Spring Container에서 BoardService 타입의 객체를 가져온다.
   *  1) 필드에 주입하기 (DI injection)
   *  2) 생성자에 주입하기
   *  3) setter 형식의 메소드에 주입하기 -> setter이름 규칙 틀려도 됨 public void sfe -> 매개변수만 @autowired 가붙은 매개 변수만 본다.
   *  
   */ 
  
    // private BoardService boardService = new BoardServiceImpl();
    
    // <bean class="com.gdu.app05.service.BoardServiceImpl" id="boardService" /> 자동 주입
    // inject 기반은 타입이 없으면 이름으로 찾아줌 @Autowired 
  @Autowired  
  private BoardService boardService;
    
//    @Autowired  // 생성자를 만들고 생성자에 주입하기
//    public boardController(BoardService boardService) {
//      super();
//      this.boardService = boardService;
//    }

    @RequestMapping(value="/board/list.do", method=RequestMethod.GET)
    public String list(Model model) {
      model.addAttribute("boardList", boardService.getBoardList());
      return "board/list";
    // 콘트 -> 서비스 -> 다오 -> 디비작업
  }

    @Autowired // setter 형식의 메소드에 주입하기
    public void setBoardService(BoardService boardService) {
      this.boardService = boardService;
    }
    
    // 스프링컨테이너 1 빈테그 -> 필드에 오토와이어 -> 생성자에 오토와이어/세터 메소드에 오토와이어 중 한가지 방법 쓰기
    // 컨테이너에 만들어진 객체를 가져올 수 있다.. ㅎ
}
