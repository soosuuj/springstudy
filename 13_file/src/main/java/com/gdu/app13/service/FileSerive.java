package com.gdu.app13.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface FileSerive {
  
  public int upload(MultipartHttpServletRequest multiparRequest);
  public Map<String, Object> ajaxUpload(MultipartHttpServletRequest multiparRequest);
  

}
