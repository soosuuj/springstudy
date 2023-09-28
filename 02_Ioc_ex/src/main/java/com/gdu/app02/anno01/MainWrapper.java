package com.gdu.app02.anno01;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class MainWrapper {

  /*
   * Spring 컨테이너를 초기화하고 빈을 관리합니다.
    Appconfig 클래스를 스캔하여 빈을 생성하고 관리합니다.
    "fitness" 빈을 가져와서 체육관 정보를 출력합니다.
   */
  
  public static void main(String[] args) {
    
    AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    
    Fitness fitness = ctx.getBean("fitness", Fitness.class);
    
    System.out.println("피트니스이름:" + fitness.getName());
    System.out.println("피트니스회원");
    fitness.info();
    
    ctx.close();
      
    }
    

  }

/*
 * MainWrapper 클래스의 main 메서드가 실행
  Spring 컨테이너가 초기화되고 AppConfig 클래스의 빈 설정 메서드가 실행.
  Calculator 객체와 Member 객체가 생성되고 빈으로 등록.
  Member 객체가 생성될 때, Calculator 객체가 주입.
  Fitness 객체가 생성되고 Member 객체가 주입.
  "fitness" 빈을 가져와서 체육관 정보를 출력하는 info() 메서드가 호출.
  따라서 체육관 정보와 회원 정보가 계산되고 출력. 
  이렇게 Spring의 DI와 Java Configuration을 사용하면 객체 간의 
  의존성을 효과적으로 관리하고 구성.
 */

