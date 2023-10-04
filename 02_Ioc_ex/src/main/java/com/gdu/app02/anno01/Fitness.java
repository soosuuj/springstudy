package com.gdu.app02.anno01;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor 
@AllArgsConstructor
@Data
public class Fitness {
  
  private String name;
  // 여러 회원 객체를 저장하고 관리 - 확장성(회원수에 제한없이 필요한만큼의 정보 저장)
  private List<Member> members;
  

    public void info() {
      for(Member member : members) {
        System.out.println("이름 :" + member.getName());
        System.out.println("키 :" + member.getHeight()+ "cm");
        System.out.println("몸무게 :" + member.getWeight()+ "kg");
        System.out.println("BMI :" + member.getBmi() + "(" + member.getStatus() + ")");
      }
    }
}
