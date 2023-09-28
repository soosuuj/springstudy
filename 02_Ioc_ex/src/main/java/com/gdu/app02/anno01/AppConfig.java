package com.gdu.app02.anno01;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  // Java Configuration 클래스로 지정
public class AppConfig {

  
  //calc() 메서드: Calculator 클래스의 객체를 생성하고 빈으로 등록
  @Bean
  public Calculator calc() {
    return new Calculator();
  }
  
  // member() 메서드: Member 클래스의 객체를 생성하고 빈으로 등록. 
  // 이때 calc() 메서드에서 생성한 Calculator 빈을 주입받음.
  @Bean
  public Member member() {
    Member m = new Member();
    m.setName("이구디");
    m.setHeight(170);
    m.setWeight(80);
    m.setCalculator(calc());
    double w = m.getWeight();
    double h = m.getHeight();
    Calculator c = m.getCalculator();
    m.setBmi(c.div(w, c.div(c.mul(h, h), 10000)));
    double bmi = m.getBmi();
    m.setStatus(bmi < 20 ? "저체중" : bmi < 25 ? "정상" : bmi < 30 ? "과체중" : "비만");
    return m;
  }
  
  // fitness() 메서드: Fitness 클래스의 객체를 생성하고 빈으로 등록. 
  // member() 메서드에서 생성한 Member 빈을 주입받음.
  @Bean
  public Fitness fitness() {
    Fitness f = new Fitness();
    f.setName("가산피트니스");
    f.setMembers(Arrays.asList(member()));
    return f;
  }
  
}
