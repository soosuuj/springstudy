package com.gdu.myhome.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdu.myhome.dto.BlogDto;

public interface BlogService {
  public Map<String, Object> imageUpload(MultipartHttpServletRequest multipartRequest);
  public int addBlog(HttpServletRequest request);
  public void blogImageBatch();
  public void loadBlogList(HttpServletRequest request, Model model);
  public int increseHit(int blogNo);
  public BlogDto getBlog(int blogNo);
  public int modifyBlog(HttpServletRequest request);
  public int removeBlog(int blogNo);
  
  //반환타입이 json - map, response 둘중 하나 - 답글
  // 반환이 왜 map -> ajax일거니까 0, 1로 보낼거라...  잭슨에 의해 map json...
  public Map<String, Object> addComment(HttpServletRequest request);
  
  // 목록 반환 뭐가 필요할까... 목록이네 modelxx
  public Map<String, Object> loadCommentList(HttpServletRequest request);
  
  public Map<String, Object> addCommentReply(HttpServletRequest request);
  public Map<String, Object> removeComment(int commentNo);
  
}