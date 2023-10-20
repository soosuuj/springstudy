package com.gdu.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gdu.app.dto.NoticeDto;
import com.gdu.app.service.NoticeService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class NoticeController {

  private final NoticeService noticeService;
  
  // 리스트
  @RequestMapping(value="/notice/list.do", method=RequestMethod.GET)
  public String list(Model model) {
    List<NoticeDto> noticeList = noticeService.getNoticeList();
    model.addAttribute("noticeList", noticeList);
    return "notice/list";
  }
  
  // 작성
  @RequestMapping(value="/notice/write.do", method=RequestMethod.GET)
  public String write() {
    return "notice/write";
  }
  
  // 삽입
  @RequestMapping(value="/notice/save.do", method=RequestMethod.POST)
  public String save(NoticeDto noticeDto
                   , RedirectAttributes redirectAttributes) {  // redirect할 데이터는 RedirectAttributes에 저장한다.
    int addResult = noticeService.addNotice(noticeDto);
    redirectAttributes.addFlashAttribute("addResult", addResult);
    return "redirect:/notice/list.do";
  }
  
  // 상세
  @RequestMapping(value="/notice/detail.do", method=RequestMethod.GET)
  public String detail(@RequestParam int noticeNo, Model model) {
    NoticeDto noticeDto = noticeService.getNotice(noticeNo);
    model.addAttribute("notice", noticeDto);
    return "notice/detail";
  }
  // 수정
  @RequestMapping(value="/notice/modify.do", method=RequestMethod.POST)
  public String modify(NoticeDto noticeDto, RedirectAttributes redirectAttributes) {
    int modifyResult = noticeService.modifyNotice(noticeDto);
    redirectAttributes.addFlashAttribute("modifyResult", modifyResult);
    return "redirect:/notice/detail.do?noticeNo=" + noticeDto.getNoticeNo();
  }
  
//  @RequestMapping(value="/notice/delete.do", method=RequestMethod.GET)
//  public String delete(@RequestParam(value="noticeNo", required=false, defaultValue="0") int noticeNo, RedirectAttributes redirectAttributes) {
//    int deleteResult = noticeService.deleteNotice(noticeNo);
//    redirectAttributes.addFlashAttribute("deleteResult", deleteResult);
//    return "redirect:/notice/list.do";
//  }
  
  // 삭제
  @RequestMapping(value="/notice/delete.do", method=RequestMethod.POST)
  public String delete(@RequestParam(value="noticeNo", required=false, defaultValue="0") int noticeNo, RedirectAttributes redirectAttributes) {
    int deleteResult = noticeService.deleteNotice(noticeNo);
    redirectAttributes.addFlashAttribute("deleteResult", deleteResult);
    return "redirect:/notice/list.do";
  }
  
  
  

  
  
  
  
  
}