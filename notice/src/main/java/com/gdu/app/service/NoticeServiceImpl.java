package com.gdu.app.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.gdu.app.dao.NoticeMapper;
import com.gdu.app.dto.NoticeDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NoticeServiceImpl implements NoticeService {

  // 서비스가 꼭 매퍼를 이용해서 디비로 가야하는건 아님..디비 안가고 그냥 사용할 수 있다. 1:1 매칭이 아님
  // 삽입  - 위에-> 컨트롤러에서 받아오고, 아래 -> dao로 반환


  private final NoticeMapper noticeMapper;
  

//  @Override
//  public int deleteNotice(HttpServletRequest request) {
//    Optional<String> opt = Optional.ofNullable(request.getParameter("noticeNo"));
//    int notice_no = Integer.parseInt(opt.orElse("0"));
//    return noticeMapper.deleteNotice(notice_no);
//  }
  
  @Override
  public int deleteNotice(int noticeNo) {
    return noticeMapper.deleteNotice(noticeNo);
  }
  
  @Override
  public int modifyNotice(NoticeDto noticeDto) {
    return noticeMapper.modifyNotice(noticeDto);
  }
  
  @Override
  public NoticeDto getNotice(int noticeNo) {
    return noticeMapper.getNotice(noticeNo);
  }

  @Override
  public int addNotice(NoticeDto noticeDto) {
    return noticeMapper.addNotice(noticeDto);
  }
  
  @Override
  public List<NoticeDto> getNoticeList() {
    return noticeMapper.getNoticeList();
  }
  
}