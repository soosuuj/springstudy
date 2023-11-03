package com.gdu.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieDto {
  private int no;
  private String title;
  private String genre;
  private String description;
  private double star;

    
 


}
