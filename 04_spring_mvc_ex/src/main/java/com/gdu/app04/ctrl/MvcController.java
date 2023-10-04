package com.gdu.app04.ctrl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdu.app04.vo.ArticleVo;

@Controller  // 꼭 붙여주기 servlet-context.xml 얘가 이 class를 컨트롤러로 인식
public class MvcController {
  
  
  // 커스터마이징 중...
  // DispatcherServlet(servlet-context.xml)에서 viewResolver을 제거했으므로 JSP 전체 경로를 모두 작성해야한다.
  
  @RequestMapping(value = "/" , method = RequestMethod.GET)  // 메소드, value = "/" 이게 contextPath
  public String main() {
    return "/WEB-INF/main.jsp";
  }
  
  @RequestMapping(value = "/write.do", method = RequestMethod.GET)
  public String write() {
    return "/WEB-INF/article/write.jsp";
  }
  
  // 파라미터 받는 3가지 , 3가지 받아서 어떻게 넘기는지 연습    // 3가지 받아서 어떻게 넘기는지 연습
  // @RequestMapping(value = "/register.do", method = RequestMethod.POST)
   public String register(HttpServletRequest request, Model model) {
       int articleNo = Integer.parseInt(request.getParameter("articleNo"));
       String title = request.getParameter("title");
       String content = request.getParameter("content"); // 변수명을 "context"에서 "content"로 수정

       model.addAttribute("articleNo", articleNo);
       model.addAttribute("title", title);
       model.addAttribute("content", content);
       return "WEB-INF/article/result.jsp";
   }
   
   

  // @RequestMapping(value = "/register.do", method = RequestMethod.POST) 
   public String register2(@RequestParam(value="articleNo") int articleNo,
                           @RequestParam(value="title") String title,
                           @RequestParam(value="content") String content,
                           Model model) {
     ArticleVo vo = new ArticleVo(articleNo, title, content);
     // 저장한 이름 확인하고 어떻게 넘기는지 확인jsp
     model.addAttribute("vo", vo);
     return "/WEB-INF/article/result.jsp";
   }

   // @RequestMapping(value="/register.do", method=RequestMethod.POST)
   public String register3(ArticleVo vo) {
      // 코드가 없어도 객체에 저장이 됐다. ﻿객체 이름vo가 아니라 파일이름으로 저장됨 ArticleVo

      return "/WEB-INF/article/result.jsp";
   }
   
   @RequestMapping(value="/register.do", method=RequestMethod.POST)
   public String register4(@ModelAttribute(value = "vo") ArticleVo vo) {
     
     return "/WEB-INF/article/result.jsp";
   }
  
}
