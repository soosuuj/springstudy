package com.gdu.app.service;

import java.util.List;

import com.gdu.app.dto.NoticeDto;

public interface NoticeService {
  
  
  int deleteNotice(int noticeNo);
  int modifyNotice(NoticeDto noticeDto);
  NoticeDto getNotice(int noticeNo);
  int addNotice(NoticeDto noticeDto);
  List<NoticeDto> getNoticeList();
}