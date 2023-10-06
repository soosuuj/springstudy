package com.gdu.app08.config;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.app08.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MemberController {
  
  // DI 1) 서비스를 bean으로 만들고, 가져오기 - 이거 안함 -> @Service 추가

    private final MemberService memberService;
    
    @ResponseBody  // 이걸 붙이는걸 연습하는게 좋음  - 이걸 붙여야 ajax이 동작함
    @GetMapping(value="/member/health.check", produces="application/json; charset=UTF-8")
    public Map<String, Object> bmiInfo(@RequestParam("memberNo") int memberNo) {
      return memberService.getBmiInfo(memberNo);
    }
      
    @ResponseBody
    @GetMapping(value="/member/profile.display", produces="application/octet-stream")
    public byte[] profile(@RequestParam("memberNo") int memberNo) {
      return memberService.getProfileImage(memberNo);
        
    }
        
        
}
