package com.gdu.app500.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gdu.app500.dto.PlayDto;
import com.gdu.app500.service.PlayService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class PlayController {

  private final PlayService playService;
  
  // 리스트
  @GetMapping(value="/play/list.do")
  public String list(Model model) {
    List<PlayDto> playList = playService.getPlayList();
    model.addAttribute("playList", playList);
    return "play/list";
  }
  
  // 작성 페이지로 이동
  @GetMapping(value="/play/write.do")
  public String wrtie() {
    return "play/write";
  }
  
  // 삽입
  @PostMapping(value ="/play/add.do")
  public String add(PlayDto playDto, RedirectAttributes redirectAttributes) {
    int addResult = playService.addPlay(playDto);
    redirectAttributes.addFlashAttribute("addResult", addResult);
    return "redirect:/play/list.do";
  }
  
  
}
