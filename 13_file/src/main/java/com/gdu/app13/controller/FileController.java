package com.gdu.app13.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gdu.app13.service.FileService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller
public class FileController {
  
 
  private final FileService fileService;
  
  
  @RequestMapping(value="/upload.do", method=RequestMethod.POST)
  public String upload(MultipartHttpServletRequest multipartRequest, RedirectAttributes redirectAttributes) {
   int addResult =  fileService.upload(multipartRequest);
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
    return fileService.ajaxUpload(multipartRequest);
  }
  
  // 이미지 업로드 포스트, MultipartHttpServletRequest 정삭적으로 동작하려면 리졸버가 되어야 첨부가 됨 App
  // 반환 타입 Map - 반환타입이 jsp 이동경로 말하는게 아니고 {url:xx} @ResponseBody 붙여서 ajax 반환...?
  // 1) RestController 붙이면 진짜 경로 작성하는 애들이 있기 떄문에 따로 붙여야함
  // 2) 반환타입을 responseEntity로 바꾸면 안적어도 됨 저 안에 Map을 저장해서 반환하면 body안붙여도 됨
  
  // contextPath 빼는 방법 request
  
  @RequestMapping(value="/ckeditor/upload.do", method=RequestMethod.POST, produces="application/json")
  @ResponseBody
  public Map<String, Object> ckeditorUpload(MultipartFile upload, HttpServletRequest request){
    return fileService.ckeditorUpload(upload, request.getContextPath());
  }
  
  @RequestMapping(value ="/add.do", method=RequestMethod.POST)
  public void add(@RequestParam String contents) {
    System.out.println(contents);
  }
  
  
}
