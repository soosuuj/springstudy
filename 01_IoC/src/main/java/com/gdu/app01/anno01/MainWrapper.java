package com.gdu.app01.anno01;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class MainWrapper {
  
  public static void ex01() {
    
    // AppConfig.java에 등록된 bean 생성하기
    AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    
    // bean 가져오기
    Calculator calc = ctx.getBean("calc", Calculator.class);

    
    // 계산기 확인
    calc.add(1, 2);
    calc.subtract(3, 4);
    calc.multiply(5, 6);
    calc.divide(7, 8);
    

    
    // ctx 닫기
    ctx.close();
    
  }

  public static void ex02() {
    
    // com.gdu.app01.anno01 패키지에 정의된 bean 생성
    AbstractApplicationContext ctx = new AnnotationConfigApplicationContext("com.gdu.app01.anno01");
    
    // bean 가져오기
    Person man = ctx.getBean("man", Person.class);
    Person woman = ctx.getBean("woman", Person.class);
    
    // 확인
    System.out.println(man.getName() + ", " + man.getAge());
    man.getCalculator().add(1, 2);
    System.out.println(woman.getName() + ", " + woman.getAge());
    woman.getCalculator().add(3, 4);
    
    // ctx 닫기
    ctx.close();
    
    
  }
  
  public static void main(String[] args) {
    //ex01();
    ex02();
    
    
    
  }

}
