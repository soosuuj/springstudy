package com.gdu.app03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

             // 컨트롤러 만드는  Annotation
@Controller  // 요거 추가하면 controller가 된다!!  요청을 받을 수 있는 서블릿이 되는것! // HttpServletRequest, response 사용 가능
public class MyController01 {
  
  /* 요청과 응답을 처리할 메소드 
   * 1. 반환타입
   *  1) String : 응답할 Jsp의 이름을 반환한다.
   *  2) void   : 컨트롤러가 호출한 서비스에서 직접 응답한다.
   *              요청 주소를 Jsp 이름으로 인식한다. 
   *              -> void로 개발하는 사람 1도 없음 - 추천안함!!
   *  3) 기타   : 비동기 통신(ajax)에서 데이터를 응답한다.
   *  
   * 2. 메소드명
   *  - 아무 일도 안 한다.
   * 3. 매개변수  //controller 이기 때문에 가능
   *  1) HttpServletRequest를 선언해서 사용 할 수 있다.
   *  2) HttpServletRespose를 선언해서 사용할 수 있다.
   * 4. 요청(@RequestMapping) 
   *  1) 요청 메소드 : GET, POST
   *  2) URL         : 요청 주소 
   *  
   */
  
  // value="/" : contextPath 요청을 의미한다. http://localhost/app03/ 주소를 의미한다.
  // value="/main.do" : /main.do 요청을 의미한다. http://localhost/app03/main.do 주소를 의미한다.
  @RequestMapping(value={"/", "/main.do"},  method=RequestMethod.GET)
  public String welcome() {
    // webapp 밑 contextPath부터 적음 
    // ViewResolver 에 모든 jsp의 경로는 이걸로 시작한다! 이걸 맡김 -> /WEB-INF/views 안적어도 됨, 자동으로 붙여줌
    
    // ViewResolver의 prefix : /WEB-INF/views/
    // ViewResolver의 surffix : .jsp  -> 모든 jsp 확장자도 같기 떄문에 확장자도 제외
    return "index";
  }
  
  @RequestMapping(value="/board/list.do", method=RequestMethod.GET)
  public String boardList() {
    return "board/list";    //  /WEB-INF/views/board/list.jsp
  }
  
  // 도착지의 주소가 공개되지 않음 -> "forward"
  // 스프링은 forward 기반임!! 
  
  // 반환이 없는 경우에는 요청 주소를 jsp 경로로 인식한다.
  // /member/list.do 요청을 /member/list.jsp 경로로 인식한다.
  // /member/list    요청을 /member/list.jsp 경로로 인식한다.
  // 이 방법 안쓸거임!!!
  // 쌤은 -=> .do 는 주소, 안붙어있으면 jsp로 구분하는 용도로 쓴다함!!!!
  @GetMapping(value="/member/list.do")
  public void memberList() {
    // void 본문 비우기 -> 본문이 없어도 작동하는게 point!
    // void 이고 반환타입이 없으면 .do 뜯어서 주소를 경로로 바꿔서 찾음
    // .do 가 없으면 member .jsp 붙여줌!!!!! .do 가 있으나 없으나 상관 없다!!
   
  }
  
  
}
