package com.gdu.app.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.gdu.app.dto.NoticeDto;

public interface NoticeService {
  
  
  //int deleteNotice(HttpServletRequest request);
  int addNotice(NoticeDto noticeDto);
  int modifyNotice(NoticeDto noticeDto);
  int deleteNotice(int noticeNo);
  NoticeDto getNotice(int noticeNo);
  List<NoticeDto> getNoticeList();
}
