package com.gdu.app11.aop;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class LogAop {
  
  // 포인트컷 : 어떤 메소드에서 동작하는지 표현식으로 작성
  @Pointcut("execution(* com.gdu.app11.controller.*Controller.*(..))")
  public void setPointCut() { }
  
  // 파라미터를 확인하는거라서 로직이 돌기 전에 확인해야함..
  // around 명확한 사유가 없으면 쓰지 xx 이전, 이후에도 동작해야함. 2개의 코드가 다 있어야해 
  // before, after 나눠서 쓰는게 나음
  
  // 어드바이스 : 포인트컷에서 실제로 동작할 내용
  @Before("setPointCut()")  // Before - 반환 void
  public void doLog(JoinPoint joinPoint) {   // joinPoint : 어드바이스로 전달 되는 메소드
    
    // 1. HttpServletRequest -> `RequestContextHolder`를 사용하여 현재 요청 속성을 검색
    ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = servletRequestAttributes.getRequest();

    // 2. 요청 파라미터 -> Map 변환 for문돌려서 순회 ->
    Map<String, String[]> map = request.getParameterMap();

    // 3. 요청 파라미터 출력 형태 만들기
    String params = "";
    if (map.isEmpty()) {
      params += "No Parameter";
    } else {
      for (Map.Entry<String, String[]> entry : map.entrySet()) {
        params += entry.getKey() + ":" + Arrays.toString(entry.getValue()) + " ";
      }
    }
    
    // 4. 로그 찍기(치환 문자 {} 활용)
    log.info("{} {}", request.getMethod(), request.getRequestURI());    // 요청방식, 주소
    log.info("{}", params);                                             // 요청 파라미터
    
  }

}
