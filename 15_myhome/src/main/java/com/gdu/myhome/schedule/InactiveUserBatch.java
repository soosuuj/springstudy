package com.gdu.myhome.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gdu.myhome.service.UserService;

import lombok.RequiredArgsConstructor;

//@EnableScheduling     // appConfig 에도 붙음..  그래서 여기서 안붙여도 됨
@RequiredArgsConstructor
@Component
public class InactiveUserBatch {
  
  private final UserService userService;
  
 // @Scheduled(cron="0 0 0 1/1 * ?")  // 매일 자정에 동작
  @Scheduled(cron="0 27 9 1/1 * ?")
  public void execute() {
    userService.inactiveUserBatch();
  }

}
