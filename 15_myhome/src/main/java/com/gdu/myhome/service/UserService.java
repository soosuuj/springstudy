package com.gdu.myhome.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;

import com.gdu.myhome.dto.UserDto;

public interface UserService {
  
  // 세션 사용하기 위해
  public void login(HttpServletRequest request, HttpServletResponse response) throws Exception;
  
  public String getNaverLoginURL(HttpServletRequest request) throws Exception;
  
  public String getNaverLoginAccessToken(HttpServletRequest request) throws Exception;
  
  public UserDto getNaverProfile(String accessToken) throws Exception;
//이메일을 가진 유저 반환
  public UserDto getUser(String email); 
    
  public void naverJoin(HttpServletRequest request, HttpServletResponse response);
  
  public void naverLogin(HttpServletRequest request, HttpServletResponse response, UserDto naverProfile) throws Exception;

  
  public void logout(HttpServletRequest request, HttpServletResponse response);
  // 제이슨 반환 - map, responsEntity
  public ResponseEntity<Map<String, Object>> checkEmail(String email);
 
  public ResponseEntity<Map<String, Object>> sendCode(String email);
  
  public void join(HttpServletRequest request, HttpServletResponse response);
  
  public ResponseEntity<Map<String, Object>> modify(HttpServletRequest request);
  
  public void modifyPw(HttpServletRequest request, HttpServletResponse response);
  
  public void leave(HttpServletRequest request, HttpServletResponse response);
  // 스케줄러가 부름
  public void inactiveUserBatch();
  public void active(HttpSession session, HttpServletRequest request, HttpServletResponse response);
}
