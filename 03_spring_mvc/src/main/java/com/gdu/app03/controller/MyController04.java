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
   * 아래는 주어진 코드의 한 줄씩 자세한 설명입니다:

1. `@GetMapping("/article/add.do")`: 
이 어노테이션은 Spring Framework에서 GET 요청을 처리하는 메서드. 
즉, "/article/add.do" 경로로의 GET 요청을 이 메서드가 처리합니다.

2. `public String articleAdd(HttpServletRequest request)`: 
이 메서드는 클라이언트로부터의 HTTP 요청을 처리하기 위한 컨트롤러 메서드입니다.  
이 메서드는 `HttpServletRequest` 객체를 매개변수로 받습니다. 
이 객체는 현재 요청과 관련된 다양한 정보를 담고 있는 Java EE 표준의 HTTP 요청 객체입니다.

3. `HttpSession session = request.getSession();`: 
이 코드는 현재 요청과 연관된 세션(`HttpSession`)을 얻어옵니다. 
세션은 클라이언트와 서버 간에 데이터를 저장하고 공유하는 데 사용됩니다.

4. `session.setAttribute("title", request.getParameter("title"))`: 
이 코드는 세션에 데이터를 저장합니다. 요청 파라미터에서 
"title" 파라미터 값을 얻어와서 세션에 "title"이라는 이름으로 저장합니다. 
세션에 저장된 데이터는 다른 요청에서도 접근 가능합니다.

5. `return "article/result";`: 
이 메서드는 "article/result"라는 문자열을 반환합니다. 
이것은 Spring의 ViewResolver에 의해 실제 뷰 페이지 경로로 해석됩니다. 
즉, "/WEB-INF/views/article/result.jsp"와 같이 뷰 페이지로 이동하게 됩니다.

요약하면, 이 메서드는 클라이언트로부터의 GET 요청을 처리하고, 
요청 파라미터에서 "title" 값을 읽어와 세션에 저장한 후, 
"article/result" 뷰 페이지로 이동하는 역할을 합니다. 
이를 통해 세션을 활용하여 데이터를 유지하고 뷰 페이지로 전달할 수 있습니다.
   */
  
  
  
  
  
 /*
  * 2. HttpSession 선언하기
  * 컨트롤러 매개변수 4번에 설명 추가
  */
  
  //@GetMapping("/article/add.do")   // ★스프링은 선언하고 싶으면 할 수 있음★ - 원래 못했음!! ()안에 선언!!!
  public String add2(HttpSession session, HttpServletRequest request) {  // 선언 순서는 존재하지 않음
    session.setAttribute("title", request.getParameter("title"));
    return "article/result";
  }
  
  /*
   * 3. SessionAttributes
   *  1) 클래스 레벨의 annotation -> 클래스에 붙이는 거라는 뜻, getMapping -> 메소드 레벨
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
  
  @GetMapping("/article/main.do")
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
   * HttpSession을 통해 세션에 저장된 데이터는 기본적으로 Object 타입으로 저장
   * 이는 세션에 어떤 종류의 데이터든 저장할 수 있도록 유연성을 제공합니다. 
   * 그러나 세션에서 데이터를 꺼내어 사용할 때에는 데이터의 원래 타입으로 형변환해야함
   */
  
  
  
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
