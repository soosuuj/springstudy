package com.gdu.app01.xml01;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainWrapper {
  
  public static void ex01() {
    
   // app-context.xml 파일 읽기 (여기서 <bean> 태그로 정의해 둔 객체가 생성된다.)
    
    // ApplicationContext이게 최고 부모  GenericXml
   AbstractApplicationContext ctx = new GenericXmlApplicationContext("xml01/app-context.xml");
    
   // <bean> 테그로 정의된 객체 가져오기 ctx.getBean("calc"); -> 형변환
   Calculator calculator = (Calculator)ctx.getBean("calc");  // ctx.getBean("calc", Calculator.class) 코드도 동일함
   
   // 객체 사용해 봅기
   calculator.add(1, 2);
   calculator.subtract(3, 4);
   calculator.multiply(5, 6);
   calculator.divide(7, 8);
   
   // app-context.xml 파일닫기
   ctx.close();
   
 /* 결과
  * 
  * 1+2=3
    3-4=-1
    5*6=30
    7/8=0
 */
  }
  
  public static void ex02() {
    
    // app-context.xml 파일 읽어서 <bean> 태그로 정의된 객체 만들기
    AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("xml01/app-context.xml");
    
    // 객체 가져오기(man,
    
    Person man = (Person)ctx.getBean("man"); 
    Person woman = ctx.getBean("woman", Person.class);
    
    // 객체 확인
   System.out.println(man.getName() + "," + man.getAge());
   man.getCalculator().add(1, 2);
   System.out.println(woman.getName() + "," + woman.getAge());
   woman.getCalculator().add(3, 4);
   
   //app -context.xml 파일닫기
   ctx.close();
   
   /* 출력 
    * 뽀로로,20
      1+2=3
      루피,20
      3+4=7
    */

  }

  public static void main(String[] args) {
    //ex01();
    ex02();
 
  }

}
