package com.gdu.myhome.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gdu.myhome.dto.BlogDto;
import com.gdu.myhome.service.BlogService;

import lombok.RequiredArgsConstructor;
import oracle.jdbc.proxy.annotation.Post;

@RequestMapping("/blog")
@RequiredArgsConstructor
@Controller
public class BlogController {
  
  private final BlogService blogService;
  
  @GetMapping("/list.do")
  public String list(HttpServletRequest request, Model model) {
    blogService.loadBlogList(request, model);
    return "blog/list";
  }

  @GetMapping("/write.form")
  public String write() {
    return "blog/write";
  }
  // 이미지 경로 바로 적어줘야함
  @PostMapping(value = "/imageUpload.do", produces = "application/json" )
  @ResponseBody
  public Map<String, Object> imageUpload(MultipartHttpServletRequest multipartrequest){
    return blogService.imageUpload(multipartrequest);
  }
  
  // 이미지 첨부는 완료 됐으니까 그거 보내면 또 이미지가 가는거니까 
  // 이미지 태그 형태로 넘어갈꺼임 - 글씨 - 단순 text 넘기기!! 저장은!!! 
  @PostMapping("/addBlog.do")
  public String addBlog(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    int addResult = blogService.addBlog(request);
    redirectAttributes.addFlashAttribute("addResult", addResult);
    return "redirect:/blog/list.do";
  }
  //조회수 증가
  @GetMapping("/increseHit.do")
  public String increseHit(@RequestParam(value="blogNo", required=false, defaultValue="0") int blogNo) {
    int increseResult = blogService.increseHit(blogNo);
    if(increseResult == 1) {
      return "redirect:/blog/detail.do?blogNo=" + blogNo;
    } else {
      return "redirect:/blog/list.do";
    }
  }
  
  @GetMapping("/detail.do")
  public String detail(@RequestParam(value="blogNo", required=false, defaultValue="0") int blogNo
                     , Model model) {
    BlogDto blog = blogService.getBlog(blogNo);
    model.addAttribute("blog", blog);
    return "blog/detail";
  }
  
  //수정
  @PostMapping("/modifyBlog.do")
  public String modifyBlog(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    int modifyResult = blogService.modifyBlog(request);
    redirectAttributes.addFlashAttribute("modifyResult", modifyResult);
    return "redirect:/blog/detail.do?blogNo=" + request.getParameter("blogNo");
  }
  
  // 삭제
  @PostMapping("/remove.do")
  public String remove(@RequestParam(value="blogNo", required=false, defaultValue="0") int blogNo
                     , RedirectAttributes redirectAttributes) {
    int removeResult = blogService.removeBlog(blogNo);
    redirectAttributes.addFlashAttribute("removeResult", removeResult);
    return "redirect:/blog/list.do";
  }
  
  @PostMapping("/edit.form") 
  public String edit(@ModelAttribute("blog") BlogDto blog) { // jsp 에서 보낸 3개가 들어있음..
    return "blog/edit";
  }
  
  
  
  @ResponseBody
  @PostMapping(value="/addComment.do", produces="application/json")
  public Map<String, Object> addComment(HttpServletRequest request) {
    return blogService.addComment(request);
  }
  
  @ResponseBody
  @GetMapping(value="/commentList.do", produces = "application/json")
  public Map<String, Object> commentList(HttpServletRequest request){
    return blogService.loadCommentList(request);
  }
  
  @ResponseBody
  @PostMapping(value="/addCommentReply.do", produces="application/json")
  public Map<String, Object> addCommentReply(HttpServletRequest request) {
    return blogService.addCommentReply(request);
  }
  
  @ResponseBody
  @PostMapping(value = "/removeComment.do", produces="application/json")
  public Map<String, Object> removeComment(@RequestParam(value = "commentNo", required = false, defaultValue = "0") int commentNo) {
    return blogService.removeComment(commentNo);
  }
  
  
  
}
