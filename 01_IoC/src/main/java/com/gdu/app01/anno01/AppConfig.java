package com.gdu.app01.anno01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  // IocContainer에 bean을 등록하는 클래스이다.
public class AppConfig {

  // 메소드를 bean으로 등록하기 위해서 @Bean을 추가한다.
  // 반환타입 = 클래스, 메소드명 = id -> 블로그 참고
  @Bean
  public Calculator calc() { // 메소드 이름 == bean 이름
    return new Calculator();
  }
  @Bean
  public Person man() {
    Person person = new Person();
    person.setName("뽀로로");
    person.setAge(20);
    person.setCalculator(calc());
    return person;
  }
  @Bean
  public Person woman() {
    return new Person("루피", 20, calc());
  }
  
}
