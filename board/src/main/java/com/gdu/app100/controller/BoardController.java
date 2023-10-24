package com.gdu.app100.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gdu.app100.dto.BoardDto;
import com.gdu.app100.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class BoardController {
  
  private final BoardService boardService;
  
  // 작성
  @RequestMapping(value="/board/write.do", method=RequestMethod.GET)
  public String write() {
    return "board/write";
  }
  
  //리스트
  @RequestMapping(value="/board/list.do", method=RequestMethod.GET)
  public String list(Model model) {
    List<BoardDto> boardList = boardService.getBoardList();
    model.addAttribute("boardList", boardList);
    
    return "board/list";
  }
  
  
  // 삽입
  @RequestMapping(value="/board/add.do", method=RequestMethod.POST)
  public String save(BoardDto boardDto
                   , RedirectAttributes redirectAttributes) {  
    int addResult = boardService.addBoard(boardDto);
    redirectAttributes.addFlashAttribute("addResult", addResult);
    return "redirect:/board/list.do";
  }
  
  
//  @RequestMapping(value = "board/delete.do", method = RequestMethod.GET)
//  public String deleteBoard(@RequestParam("no") int no, RedirectAttributes redirectAttributes) {
//      int deleteResult = boardService.deleteBoard(no);
//      redirectAttributes.addFlashAttribute("deleteResult", deleteResult);
//      return "redirect:/board/list.do"; 
//  }
//  
  
  // 삭제
  @RequestMapping(value="/board/delete.do", method=RequestMethod.POST)
  public String delete(@RequestParam(value="no", required=false, defaultValue="0") int no, RedirectAttributes redirectAttributes) {
    int deleteResult = boardService.deleteBoard(no);
    redirectAttributes.addFlashAttribute("deleteResult", deleteResult);
    return "redirect:/board/list.do";
  }

  
  // 상세
  @RequestMapping(value="/board/detail.do", method=RequestMethod.GET)
  public String detail(@RequestParam int no, Model model) {
    BoardDto boardDto = boardService.getBoardDto(no);
    model.addAttribute("boardDto", boardDto);
    return "board/detail";
  }
  
  // 수정
  @RequestMapping(value="/board/modify.do", method=RequestMethod.POST)
  public String modify(BoardDto boardDto, RedirectAttributes redirectAttributes) {
    int modifyResult = boardService.modifyBoard(boardDto);
    redirectAttributes.addFlashAttribute("modifyResult", modifyResult);
    return "redirect:/board/detail.do?no=" + boardDto.getNo();
  }
  

}
