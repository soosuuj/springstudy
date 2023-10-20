package com.gdu.myhome.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import com.gdu.myhome.dto.UserDto;

public interface UserService {
  
  // 세션 사용하기 위해
  public void login(HttpServletRequest request, HttpServletResponse response);
  public void logout(HttpServletRequest request, HttpServletResponse response);
  // 제이슨 반환 - map, responsEntity
  public ResponseEntity<Map<String, Object>> checkEmail(String email);
 
  public ResponseEntity<Map<String, Object>> sendCode(String email);
  
}
