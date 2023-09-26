package com.gdu.app02.anno01;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Appconfig {

  @Bean
  public Calculator calc() {
    return new Calculator();
  }
  
  // Member 1개 
  @Bean
  public Member member() {
    Member m = new Member();
    m.setName("홍길동");
    m.setHeight(170.6);
    m.setWeight(99.99);
    m.setCalculator(calc());
    double w = m.getWeight();
    double h = m.getHeight();
    Calculator c = m.getCalculator();
    m.setBmi(c.div(w, c.div(c.mul(h, h), 1000)) );
    double bmi = m.getBmi();
    m.setStatus(bmi < 20 ? "저체중" : bmi < 25 ? "정상" : bmi < 30 ? "과체중" : "비만");
    return m;
  }
  
  
  
  // Fitness 멤버 등록 , (Arrays.asList(member(),member())); 한명 더 있으면 
  @Bean
  public Fitness fitness() {
    Fitness f = new Fitness();
    f.setName("구디체육관");
    f.setMembers(Arrays.asList(member()));
    return f;
  }
  
  
}
