package com.gdu.app07.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gdu.app07.dto.AjaxDto;
import com.gdu.app07.service.AjaxService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/ajax3")
@RequiredArgsConstructor //final 필드의 데이터 채우기 반드시 필요하기 때문에 필수  args로!!
@Controller
public class AjaxController3 {
  // 컨드롤러가 필요한건 서비스, 선언
  private final AjaxService ajaxService;  // @RequiredArgsConstructor로 여기 자동 주입

  // ResponseEntity, ajax 응답 할 때 필요, 일종의 Wrapper, <> 실제 저장하려는 데이터
  //   public ResponseEntity<List<AjaxDto>> list(){  그대로 쓰면 타입이 안맞아서 오류남
  //    return ajaxService.getDtoList();

  @GetMapping(value = "/list.do", produces = "application/json; charset=UTF-8")
  public ResponseEntity<List<AjaxDto>> list(){
    return new ResponseEntity<List<AjaxDto>>(  //ResponseEntity는 @ResponseBody를 작성하지 않는다.
        ajaxService.getDtoList()   // 실제 응답 데이터
      , HttpStatus.OK);            // 응답 코드(200)
  }
  
  @PostMapping(value = "/detail.do")
  public ResponseEntity<AjaxDto> detail(@RequestBody AjaxDto ajaxDto){  // post 방식(@RequestBoby)으로 전송된 json 데이터(AjaxDto ajaxDto)
    // 응답 헤더: Content-Type  produces = "application/json; charset=UTF-8" 을 대체 한다,
    // 를 직접 제작해서 보내는 방법
    HttpHeaders header = new HttpHeaders();
    header.add("Content-Type", "application/json; charset=UTF-8");
    
    // 반환 -> 블로그 
    return new ResponseEntity<AjaxDto>                       // ResponseEntity는 @RequestBody 를 작성하지 않는다.
                     (ajaxService.getDto(ajaxDto.getName())  // 실제 응답 데이터
                    , header                                 // 응답 헤더
                    , HttpStatus.OK);                        // 응답 코드(200)
  }
  
  
  
  
  
}
