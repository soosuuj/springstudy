package com.gdu.myhome.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gdu.myhome.service.FreeService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/free")
@RequiredArgsConstructor
@Controller
public class FreeController {

  private final FreeService freeService;
  
  @GetMapping("/write.form")
  public String writeForm() {
    return "free/write";
  }
  
  @PostMapping("/add.do")
  public String add(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    int addResult = freeService.addFree(request);
    redirectAttributes.addFlashAttribute("addResult", addResult);
    return "redirect:/free/list.do";
  }
  
  @GetMapping("/list.do") // 목록을 모델에 저장해서 넘김
  public String list(HttpServletRequest request, Model model) {
    // 서비스 구현 후 채우기
    freeService.loadFreeList(request, model);
    return "free/list";
  }
  
  @PostMapping("/addReply.do")
  public String addReply(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    int addReplyResult = freeService.addReply(request);
    redirectAttributes.addFlashAttribute("addReplyResult", addReplyResult);
    return "redirect:/free/list.do";
  }
  
  @PostMapping("/remove.do")
  public String remove(@RequestParam(value = "freeNo") int freeNo, RedirectAttributes redirectAttributes ) {
    int removeResult = freeService.removeFree(freeNo);
    redirectAttributes.addFlashAttribute("removeResult", removeResult);
    return "redirect:/free/list.do";
  }
  
  
//  @PostMapping("/remove.do")
//  public String remove(HttpServletRequest request, RedirectAttributes redirectAttributes ) {
//    int removeResult = freeService.removeFree(Integer.parseInt(request.getParameter("freeNo")));
//    redirectAttributes.addFlashAttribute("removeResult", removeResult);
//    return "redirect:/free/list.do";
//  }
  
  @GetMapping("/search.do") //list 처럼
  public String search(HttpServletRequest request, Model model) {
    freeService.loadSearchList(request, model);
    return "free/list";  // 포워드 - 리스트가 검색 결과로 바뀌어서 나오는 것으로 작성할거임
  }
  
  
  
  
  
  
}
