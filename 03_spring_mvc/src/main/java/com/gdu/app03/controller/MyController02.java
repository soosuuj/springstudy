package com.gdu.app03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller  // -> 꼭 추가!!
public class MyController02 {
  
  // Spring 4 버전 이후 사용 가능한 @RequestMapping
  // 1. @GetMapping
  // 2. @PostMapping

  // 메소드 적을 필요 없음.
  @GetMapping(value="/notice/list.do")
  public String boardList( ) {
    // ViewResolver의 prefix : /WEB-INF/views/
    // ViewResolver의 surffix : .jsp  
    
    //﻿(value="/board/list.do")스프링에서는 2개의 똑같은 주소가 있으면 돌아가지 않는다.-> controller1에서 씀
    return "notice/list";  //   /WEB-INF/views/board/list.jsp 
    
    // 주소가 똑같아도 메소드가 다르면 다른 요청(get, post) 으로 인지...
    // @GetMapping은 안되는데 @PostMapping는 됨
    
    // 
  }
  
}
