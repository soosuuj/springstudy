package com.gdu.app07.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gdu.app07.dto.AjaxDto;
import com.gdu.app07.service.AjaxService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/ajax2")
@RestController  // 모든 메소드에 @ResponseBody를 추가하는 @Controller이다.
@RequiredArgsConstructor
public class AjaxController2 {
  
  // 서비스를 필드로 부름
  private final AjaxService  ajaxService;
  
  // 목록 만들기 -> @ResponseBody 필요 없음
  @GetMapping(value = "/list.do", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<AjaxDto> getAjaxList(){
    return ajaxService.getDtoList();
  }
  
  @GetMapping(value = "/detail.do", produces = MediaType.APPLICATION_JSON_VALUE)
  public AjaxDto getDto(@RequestParam(value = "name")String name) {
    return ajaxService.getDto(name);
  }
  
  
  

}
