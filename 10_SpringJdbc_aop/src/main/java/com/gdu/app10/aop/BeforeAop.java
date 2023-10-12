package com.gdu.app10.aop;

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
          //private static final Logger log = LoggerFactory.getLogger(BeforeAop.class);
@Slf4j    // log 쉽게 찍을 수 있음, 로그 찍기 선언과 동일, 로그라는 객체 자동으로 찍어줌
@Aspect   // AOP
@Component
public class BeforeAop {
  
  //포인트컷 : 언제 동작하는가? - 모든 메소드 * <- *(..) 저기가 메소드(..)파라미터  , 
  // 클래스의 모든 메서드 실행을 일치시킴
  @Pointcut("execution(* com.gdu.app10.controller.*Controller.*(..))")
  public void setPointCut() { } // 이름만 제공하는 메소드(동작x) (이름은 마음대로, 본문도 필요 없다.)

  //어드바이스 : 무슨 동작을 하는가? -
  @Before("setPointCut()")  //ContactController모든 메소드가 동작하기  setPointCut 가 이전에 수행됨
  public void beforeAdvice(JoinPoint joinPoint) {
    /*
     * Before 어드바이스
     * 1. 반환타입 : void
     * 2. 메소드명 : 마음대로
     * 3. 매개변수 : JoinPoint 선언하여 사용
     */
    
    /* ContactController의 모든 메소드가 동작하기 전에 요청(방식/주소/파라미터) 출력하기 */
    
    
   // 1. HttpServletRequest -> `RequestContextHolder`를 사용하여 현재 요청 속성을 검색
    ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = servletRequestAttributes.getRequest();

    // 2. 요청 파라미터 -> Map 변환 for문돌려서 순회 ->
    // 파라미터 확인.. 문제가 있음 -> request를 받아올 수 없음 -> 새로배운다!!!
    // `servletRequestAttributes`에서 `HttpServletRequest` 객체를 얻어냄
    Map<String, String[]> map = request.getParameterMap();

    // 3. 요청 파라미터 출력 형태 만들기
    //HttpServletRequest` 객체에서 `getParameterMap` 메서드를 호출하여 `map`이라는 `Map`을 생성. 
    //`map`에는 요청 파라미터와 그 값들이 포함.
    /*
     * `map`을 생성하는 목적은 이후의 코드에서 요청 파라미터를 조사하고 로그에 남기기 위한 것. 
     * 이 코드는 `map`을 반복하여 요청 파라미터의 문자열 표현을 생성하고, 이를 로그로 기록.
     * `map`은 외부 소스에서 나온 것이 아니라,
        `HttpServletRequest` 객체를 사용하여 들어오는 요청의 파라미터에서 생성된 것입니다.
     */
    
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
