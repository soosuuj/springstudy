package com.gdu.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.app.dto.NoticeDto;

@Mapper
public interface NoticeMapper {
  int addNotice(NoticeDto noticeDto);
  int modifyNotice(NoticeDto noticeDto);
  int deleteNotice(int noticeNo);
  NoticeDto getNotice(int noticeNo);
  List<NoticeDto> getNoticeList();
}
 