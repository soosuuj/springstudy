package com.gdu.staff.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gdu.staff.dto.StaffDto;
import com.gdu.staff.service.StaffService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class StaffController {

  private final StaffService staffService;
  
    // 사원등록
    @PostMapping(value="/add.do", produces="application/json")
    public ResponseEntity<Map<String, Object>> add(StaffDto staff){
      return staffService.registerStaff(staff);
    }
    
//    // 사원목록
//    @GetMapping(value ="/staff", produces="application/json")
//    public List<StaffDto> list(Model model){
//      return staffService.listStaff();
//    }
   
    
  
}
