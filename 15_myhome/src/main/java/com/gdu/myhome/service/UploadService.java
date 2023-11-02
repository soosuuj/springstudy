package com.gdu.myhome.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface UploadService {
  
  public boolean addUpload(MultipartHttpServletRequest multipartRequest) throws Exception;
  
  // ajax으로 목록 반환 반환타입? list 보다 Map - 맵에 리스트를 담아 ... 이런식으로 확장!! list로 목록을 만드는 것 비추 확장xx
  // 무한 스크롤 - 컨드롤러로 페이지 파라미터, 페이징처리랑 비슷하게 생각하면 됨, 
  // 1페이지라고 안써있어도 걍 1페이지라고 본다... 바닥 즈음에 페이지 1개 늘어나게 구성(새로운 목록 페이지 열림) 이벤트는 스크롤이 바닥쯤에 닿앗을 때 동작하는 이벤트 -> 페이지 3으로 널려서 비긴 앤드값 게산해서 append ..
  // 페이지 번호 없이 휠을 내려도 모든 목록을 확인할 수 있는 무한 스크롤 연출 가넝
  // 확장을 생각해서 HttpServletRequest 를 많이 쓰게 됨...이렇게 정형화 되는거임
  public Map<String, Object> getUploadList(HttpServletRequest request);  // int page만 쓸거같긴함.. 
  
  
}
