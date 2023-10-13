package com.gdu.app13.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gdu.app13.service.FileSerive;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller
public class FileController {
  
 
  private final FileSerive fileSerive;
  
  
  @RequestMapping(value="/upload.do", method=RequestMethod.POST)
  public String upload(MultipartHttpServletRequest multipartRequest, RedirectAttributes redirectAttributes) {
   int addResult =  fileSerive.upload(multipartRequest);
   redirectAttributes.addFlashAttribute("addResult", addResult);
    return "redirect:/main.do";  
  }
  
 // 1. 컨드롤러 restController -> 안에 바디 포함
 // 2. reponseBody로만 동작하는 @responseEntity>> 7, 8장에 있음
  @RequestMapping(value="/ajax/upload.do", method=RequestMethod.POST, produces="application/json")
  @ResponseBody    // {"success":true} -> 제이슨/ success 필드 가지고 있는 객체, 성공 키를 가진 Map!!
  // json 보낼거면 Map / 배열 보낼 때 list ..
  // jackson map이나 list보내면 쟤가 알아서 배열로 바꿔줌.. 7장 이후로 계속 가지고 다님 pom.xml 있다..
  public Map<String, Object> ajaxUpload(MultipartHttpServletRequest multipartRequest) {
    return fileSerive.ajaxUpload(multipartRequest);
  }
  
  
}
