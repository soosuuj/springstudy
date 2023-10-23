package com.gdu.app500.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlayDto {
  
  private int playNo;
  private String player;
  private int age;
  private String gender;

}
