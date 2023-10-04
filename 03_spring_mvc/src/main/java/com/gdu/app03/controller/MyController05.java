package com.gdu.app03.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MyController05 {
  
  /*
    redirect 하는 방법   -> 새로 배운거니까 이거 연습할거임
    1. return "redirect:이동경로"; - 스프링이 지원하는 방법
        public String add(){
          return "redirect:/list.do";
          }
          
       // jsp에서 많이 쓰던 고전적인 방법
    2. location.href='이동경로';  - 자바스크립트 코드를 만듦  -> alet 창을 띄우기 위한 방법..
        public void add(HttpServletResponse response) {
          printWriter out = response.getWriter();
           out.println("<script>")
           out.println("locataion.href='이동경로'")
           out.println("</script>"));
           }
  */
  
  /*
   * redirect 이동경로
   * 1. 반드시 URLMapping값을 작성한다.
   * 2. 이동할 JSP 경로를 작성할 수 없다.
   */
  //@RequestMapping(value = "/faq/add.do", method=RequestMethod.POST)
  public  String add(HttpServletRequest request) {
    
    // 요청파라미터 
    String title = request.getParameter("title");
    String content = request.getParameter("content");

    // title 이 빈 문자열이면 add 실패로 가정(DB 처리할 때 insert 성공은 1, 실패는 0이다.)
    int addResult = title.isEmpty() ? 0 : 1;
    
    
    // redirect 모델에 전달 안되는게 원칙 -> 다른 방법 사용!!
    // addResult 가지고 faq 목록보기로 이동
    return "redirect:/faq/list.do?addResult=" + addResult;   //// Mapping - 폴더 경로 적으면 안됨(jsp)
  
  }
   // redirect 값이 전달되지 않는다. 그래서 쪼금 불편함 
  // 어디? -? 게시글을 작성할 떄 성공 실패 여부를 가늠, 제목 입력ㅇ-> 성공ㅇ
  // viewresolver 있는 값으로 계속 쓸거임 커스터마이징 ㄴㄴ
  //@RequestMapping(value = "/faq/list.do", method=RequestMethod.GET)
 public String list(@RequestParam(value="addResult", required=false) String addResult, Model model) {  // model에 저장
   
   model.addAttribute("addResult", addResult);  // 1과 0을 전달
   
   // ViewResolver prefix :  /WEB-INF/views/
   // ViewResolver suffix :  .jsp
   
   return "faq/list";  //  /WEB-INF/views/faq/list.jsp
   
 }
  
 
 /*
  * redirect: faq/list.do?deleteResult=" -> addResult 필수가 아님
  * 파리미터 없으면 null,null  이 들어있으면 그대로 들어가서 전달되어 나타암..
  */
 
 @RequestMapping(value = "/faq/add.do", method=RequestMethod.POST)
 public String add2(HttpServletRequest request
                  , RedirectAttributes redirectAttributes) { // redirect 상황에서 값을 전달할 때 사용한다.
   
   
   // 리다이렉트 할 때 속성 전달 할 때 사용하는 것, 리다이렉트까지 속성 전달
   // 요청 파라미터
   String title = request.getParameter("title");
   
   // title이 빈문자열이면 add 실패
   int addResult = title.isEmpty() ? 0 : 1;
   
   // faq 목록보기로 redirect 할 때 addResult를 "flash attribute"로 곧바로 전달하기 -> 스프링에서 새로 생긴거임
   redirectAttributes.addFlashAttribute("addResult", addResult);
   
   // faq 목록보기로 redirect
   return "redirect:/faq/list.do";
   
 }

   // 추가로 할 것 없음
   @RequestMapping(value = "/faq/list.do", method=RequestMethod.GET)
   public String list2() {
     return "faq/list";
   }
   
 
 
  
}
