package com.gdu.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.app.dto.NoticeDto;

@Mapper
public interface NoticeMapper {
  int deleteNotice(int noticeNo);
  int modifyNotice(NoticeDto noticeDto);
  int addNotice(NoticeDto noticeDto);
  NoticeDto getNotice(int noticeNo);
  List<NoticeDto> getNoticeList();
}
 