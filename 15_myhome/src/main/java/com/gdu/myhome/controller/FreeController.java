package com.gdu.myhome.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    int addresult = freeService.addFree(request);
    redirectAttributes.addFlashAttribute("addresult", addresult);
    return "redirect:/free/list";
  }
  
  @GetMapping("/list.do")  // 목록을 모델에 저장해서 넘김
  public String list(HttpServletRequest request, Model model) {
    // 서비스 구현 후 채우기
    freeService.loadFreeList(request, model);
    return "free/list";
  }
  
  
  
  
}
