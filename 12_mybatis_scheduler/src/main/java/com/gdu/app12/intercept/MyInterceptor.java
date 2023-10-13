package com.gdu.app12.intercept;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class MyInterceptor implements HandlerInterceptor {

  @Override  // 반환 타입이 boolean -> 
  // true : 인터셉터 실행 후 에 컨트롤러 동작,false 인터셉터만 실행하고 컨트롤러 동작을 막는다. 
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<script>");
    out.println("alert('인터셉터가 동작했습니다.')");
    out.println("history.back()");
    out.println("</script>");
    out.flush();
    out.close();
    
    return false;  // 컨트롤러의 요청이 처리되지 않는다.
  }
  
}
