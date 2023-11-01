package com.gdu.myhome.dto;

import lombok.Data;

import lombok.NoArgsConstructor;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AttachDto {
  
  private int attachNo;
  private String path;
  private String originalFilename;
  private String filesystemName;
  private int downloadCount;
  private int hasThumbnail;
  private int uploadNo;

}
