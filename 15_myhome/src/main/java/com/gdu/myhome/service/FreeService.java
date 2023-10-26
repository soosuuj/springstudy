package com.gdu.myhome.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface FreeService {
  
  public int addFree(HttpServletRequest request);
  public void loadFreeList(HttpServletRequest request, Model model);
  public int addReply(HttpServletRequest request);
  public int removeFree(int freeNo);  // request에서 꺼내는건 불편할 수 있으니 이거 선택 /HttpServletRequest 사용 ㄱ가능  
  public void loadSearchList(HttpServletRequest request, Model model);
}
