package com.gdu.app03.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdu.app03.dto.BlogDto;

@Controller
public class MyController03 {
  
  /*
   * 1. HttpServletRequest request를 이용한 요청 파라미터 처리
   *  1) Java EE 표준 방식이다.
   *  2) 파라미터뿐만 아니라 HttpSession, String contextPath 와 같은 정보도 꺼낼 수 있으므로 여전히 강력한 도구이다.
   */
  
  // -> 얘 주석처리하면 동작 안함!!!
   //@RequestMapping("/blog/detail.do")  //  GET 방식의 method는 생략할 수 있다. value만 작성할 땐 value= 생략할 수 있다.
  public String blogDetail(HttpServletRequest request, Model model) {
    //HttpServletRequest request 가장 강력!!, 3개 중 하나만 쓸 수 있음 이거쓰기, session, contextPath
    // 나머지 2개는 파라미터만 가져올 수 있음4
    
    // Model 포워딩할 정보는 모델에 저장, request는 요청 정보
    
    // ViewResolver의 prefix : /WEB-INF/views/
    // ViewResolver의 surffix : .jsp  
    
    //${contextPath}/blog/detail.do?blogNo=100">   100 : blogNo
    // 기본!! forward라고 안써있으면 forward , redirect만 뭐라고 써줌
    
    String blogNo = request.getParameter("blogNo");
  
    // model에 정보 저장하는 방법
    model.addAttribute("blogNo", blogNo);
    
    // 이건 jsp로 포워딩 할 정보는 스프링에서 이렇게 쓰지 않을거임!! 새로운 개발 코드 배워보자
    // request.setAttribute("blogNo", blogNo);   // request에 정보저장하는 코드
    return "/blog/detail";          // /WEB-INF/views/blog/detail.jsp 로 forward한다 /forward는 request 전달
  }
  /*
   * 2. @RequestParam을 이용한 요청 파라미터 처리
   *  1) 파라미터의 개수가 적은 경우에 유용한다. (@RequestParam(value="blogNo") 파라미터 1개일 때 쓰기
   *  2) 주요 메소드
   *    (1) value      : 요청 파라미터의 이름
   *    (2) required   : 요청 파라미터의 필수 여부(디폴트 true - 요청 파라미터가 없으면 서버측 오류 발생)
   *    (3) defaultValue : 요청 파라미터가 없는 경우에 사용할 값
   *  3) @RequestParam을 생략할 수 있다.
   *      -> 
   */
  
  //@RequestMapping("/blog/detail.do")
  public String blogDetail2(@RequestParam(value="blogNo") int blogNo, Model model) {
  //public String blogDetail2(int blogNo, Model model) {  // 지워도 돌아감
    model.addAttribute("blogNo", blogNo);
    return "blog/detail";
    
  }
  
  /*
   * 3 커맨드 객체를 이용한 요청 파리미터 처리   // 블로그 참고
   *  1) 요청 파라미터를 필드로 가지고 있는 객체를 커맨드 객체라고 한다.  // bolgNo를 객체가 가지고 있는 것
   *  2) 요청 파라미터를 필드에 저장할 때 Setter가 사용된다.
   *  3) 요청 파라미터가 많은 경우에 유용하다. 
   *        -> 요기까지 중요
   *  4) 커맨드 객체는 자동으로 Model에 저장된다. 저장될 때 객체명(dto)이 아닌 클래스명(BlogDto)으로 저장된다. (클래스 명을 LowerCamelCase로 바꿔서 저장한다.)
            -> 이걸 쓸일이 없어서 별로 안중요.. 저장 정보는 db로 보내지 jsp로 보내지 않기 때문에
   */
  
  //@RequestMapping("/blog/detail.do")   
  public String blogDetail3(BlogDto dto) {  // ★주의 - Model에 저장된 이름은 dto가 아니라  ★blogDto ★이다.
    // model.addAttribute("blogDto", dto);      // 저장하는 이름 의도적으로 변경 , 포워딩 정보가 없어진상태 
    // System.out.println(dto.getBlogNo());  // 3번
    // return null;
    return "blog/detail";
    
    // 출력 : 콘솔에 100 
  }
  
  /*
   * 주어진 코드와 `BlogDto` 클래스를 기반으로 실행되는 과정을 설명하겠습니다. 
   * 주어진 코드에는 Spring Framework에서 사용되는 `@Controller`와 `@GetMapping` 어노테이션은 빠져있지만, 
   * 가정하에 코드를 설명하겠습니다.

1. **HTTP GET 요청 수신:**
   - 클라이언트에서 웹 브라우저를 통해 "/blog/detail3" 경로로 HTTP GET 요청이 서버로 전송됩니다.

2. **Spring 컨트롤러 메서드 호출:**
   - Spring Framework는 "/blog/detail3" 경로에 대한 요청을 매핑하는 컨트롤러 메서드를 찾습니다. 
   주어진 코드에서는 `blogDetail3()` 메서드가 이 역할을 합니다.

3. **BlogDto 객체 생성 및 파라미터 바인딩:**
   - Spring Framework는 요청 파라미터와 `BlogDto` 클래스의 필드를 자동으로 매핑합니다.
   - `BlogDto` 클래스에 `@Data` 어노테이션이 사용되었기 때문에 Lombok을 통해 getter 및 setter 메서드가 자동 생성됩니다.
   - 요청에서 넘어온 "blogNo" 파라미터의 값은 `BlogDto` 객체의 `blogNo` 필드에 자동으로 할당됩니다.

4. **blogDetail3() 메서드 실행:**
   - `blogDetail3()` 메서드가 실행됩니다. 이 메서드는 `BlogDto` 객체를 파라미터로 받으므로, 
   파라미터로 전달된 `dto` 객체에는 요청에서 받은 "blogNo" 값이 할당되어 있습니다.

5. **뷰 이름 반환:**
   - `blogDetail3()` 메서드는 "blog/detail" 문자열을 반환합니다. 이것은 실제 뷰 이름을 나타냅니다.

6. **ViewResolver를 통한 뷰 경로 결정:**
   - Spring Framework는 `ViewResolver`를 사용하여 뷰 이름을 실제 JSP 파일 경로로 변환합니다. 
   주로 사용되는 설정은 "prefix"와 "suffix"입니다. 
   예를 들어, "/WEB-INF/views/" (prefix)와 ".jsp" (suffix)로 설정되어 있다면, 
   "blog/detail"은 "/WEB-INF/views/blog/detail.jsp"로 변환됩니다.

7. **HTTP 응답:**
   - 변환된 JSP 파일("/WEB-INF/views/blog/detail.jsp")이 클라이언트로 응답되어, 
   클라이언트의 웹 브라우저에서 해당 JSP 페이지가 렌더링되어 화면에 표시됩니다.

요약하면, 주어진 코드는 HTTP GET 요청을 받아서 `BlogDto` 객체를 생성하고, 
해당 객체를 이용하여 뷰로 데이터를 전달하여 웹 페이지를 렌더링하는 과정을 보여줍니다. 
이 과정에서 Spring Framework는 요청 파라미터와 `BlogDto` 객체를 자동으로 연결해주어 편리한 데이터 처리를 가능하게 합니다.
   */
  
  
  
  // @ModelAttribute를 이용해서 Model에 저장되는 커맨드 객체의 이름을 지정할 수 있다. 
  // 특이한 특징이 안중요함
  
  @RequestMapping("/blog/detail.do")
  public String bligDetail4(@ModelAttribute("dto") BlogDto blogDto) {  // Model에 저장되는 이름은 dto이다. 이름 바꾸고 싶으면 바꿔라..modelAttribute 이용.. 아주 재밌죠?
    return "blog/detail";
  }
  
  
  
}
