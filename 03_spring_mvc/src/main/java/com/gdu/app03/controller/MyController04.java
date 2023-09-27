package com.gdu.app03.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@SessionAttributes("title")  // Model에 title이 저장되면 HttpSession에도 같은 값을 저장한다.
@Controller
public class MyController04 {
  
   /********************* 세션에 저장하기***********************/

  
  /*
   * 1. HttpServletRequest로부터 HttpSession 얻기
   */
  
  //@GetMapping("/article/add.do")  // 고전스타일
  public String articleAdd(HttpServletRequest request) { // jsp 넘어갈 땐 model 필수
    
    HttpSession session = request.getSession(); // java 웹 개발의 표준 request로 얻어내기
    session.setAttribute("title", request.getParameter("title")); // title request에서 꺼내기
    // 세션에 저장하면 어디서든 확인할 수 있음(모든 jsp)
    
    // ViewResolver의 prefix : /WEB-INF/views/
    // ViewResolver의 surffix : .jsp  
   
    return "article/result";
  }
  
 /*
  * 2. HttpSession 선언하기
  * 컨트롤러 매개변서 4번에 설명 추가
  */
  
  //@GetMapping("article/add.do")   // ★스프링은 선언하고 싶으면 할 수 있음★ - 원래 못했음!! ()안에 선언!!!
  public String add2(HttpSession session, HttpServletRequest request) {  // 선언 순서는 존재하지 않음
    session.setAttribute("title", request.getParameter("title"));
    return "article/result";
  }
  
  /*
   * 3. SessionAttributes
   *  1) 클래스 레벨의 annotiaton -> 클래스에 붙이는 거라는 뜻, getMapping -> 메소드 레벨
   *  2) Model에 값을 저장하면 HttpSession에 함께 저장된다. 
   */
  
  @GetMapping("/article/add.do")
  public String add3(HttpServletRequest request, Model model) {
    model.addAttribute("title", request.getParameter("title")); // 
    return "article/result";
    // 그 어디에도 세션에 저장하는 코드 없음 
  }

  /********************* 세션 정보 삭제하기**********************/
  
  /*
   * 1. HttpSession 의 invalidate() 메소드 - 고전방법 실제도 많이 사용
   */

  // @GetMapping("/article/main.do")
  public String main(HttpSession session) {
    
    // session 정보 초기화
    session.invalidate();   // title 값 다 지워짐
    return "index";  
  }
  
  /*
   * 2. SessionStatus의 setComplete() 메소드 - 스프링에서 지원하는 것이다.
   */
  
  @GetMapping("article/main.do")
  public String main2(SessionStatus sessionStatus) {
      
    // session attribute 의 삭제
    sessionStatus.setComplete();
    return "index";
  }
  
  /********************* 세션 정보 확인하기**********************/
  
  /*
   * 1. HttpSession의 getAttribute() 메소드 - 고전 방법
   */
  
  // @GetMapping("/article/confirm.do")
  public String confirm(HttpSession session) {
    
    String title = (String)session.getAttribute("title");
    System.out.println(title);
    return "index";
  }
  
  /*
   * 2. @SessionAttribute 
   */
  
  @GetMapping("/article/confirm.do")
  public String confirm2(@SessionAttribute ("title")String title) { // 세션의 어쩌구를 Strign title에 저장하시오
   // session에 저장된 "title" 속성을 String title에 저장한다.
   // session에 있는 정보를 꺼내 쓰는 과정은 오늘 소개한 꺼내기, 저장, 삭제하기 하나도 모르면 안됨!!!!
    
    System.out.println(title);
    return "index";
  }
  
  
  
  
}
