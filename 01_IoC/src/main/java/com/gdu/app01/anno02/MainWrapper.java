package com.gdu.app01.anno02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class MainWrapper {

  public static void main(String[] args) {
    
    // 썩 중요하지 않은 코드.. 직접 연습하고있지만 나중에 이 코드를 한줄에 바꿀 수 있다!!

    // AppConfig.java 에 등록된 bean 생성
    AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    
    // bean 가져오기
    Board board = ctx.getBean("board", Board.class);
    
    // 확인
    System.out.println(board.getTitle() + ", " + board.getEditor());
    
    // ctx 닫기
    ctx.close();
    
  }

}
