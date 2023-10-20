package com.gdu.cucu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gdu.cucu.dto.MemberDto;
import com.gdu.cucu.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MemberController {
  
  private final MemberService memberService;
  
  @GetMapping(value="/member/list.do")
  public String memberList(Model model) {
    List<MemberDto> memberList = memberService.getMemberList();
    model.addAttribute("memberList", memberList);
    return "member/list";
  }
  // 상세
  @RequestMapping(value="/member/detail.do", method=RequestMethod.GET)
  public String detail(@RequestParam int memberNo, Model model) {
    MemberDto memberDto = memberService.getMember(memberNo);
    model.addAttribute("member", memberDto);
    return "member/detail";
  }

  // 작성
  @GetMapping(value="/member/write.do")
  public String write() {
    return "member/write";
  }
  // 삽입
  @PostMapping(value="/member/save.do")
  public String save(MemberDto memberDto
                   , RedirectAttributes redirectAttributes) {  // redirect할 데이터는 RedirectAttributes에 저장한다.
    int addResult = memberService.addMember(memberDto);
    redirectAttributes.addFlashAttribute("addResult", addResult);
    return "redirect:/member/list.do";
  }
  // 수정
  @PostMapping(value="/member/modify.do")
  public String modify(MemberDto memberDto, RedirectAttributes redirectAttributes) {
    int modifyResult = memberService.modifyMember(memberDto);
    redirectAttributes.addFlashAttribute("modifyResult", modifyResult);
    return "redirect:/member/detail.do?memberNo=" + memberDto.getMemberNo();
  }
  
  
//  // 삭제
//  @PostMapping(value="/member/delete.do")
//  public String delete(@RequestParam(value="memberNo", required=false, defaultValue="0") int memberNo, RedirectAttributes redirectAttributes) {
//    int deleteResult = memberService.deleteMember(memberNo);
//    redirectAttributes.addFlashAttribute("deleteResult", deleteResult);
//    return "redirect:/member/list.do";
//  }

  @GetMapping(value="/member/delete.do")
  public String remove(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    // /notice/list.do로 redirect할 때 삭제 결과(0 또는 1)를 보내기 위해서 RedirectAttributes를 사용한다. 삭제 결과에 따른 경고창 출력 코드는 list.jsp에 있다.
    redirectAttributes.addFlashAttribute("removeResult", memberService.deleteMember(request));
    return "redirect:/member/list.do";
  
  }
  
}
