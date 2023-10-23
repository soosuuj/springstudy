package com.gdu.app500.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gdu.app500.dao.PlayMapper;
import com.gdu.app500.dto.PlayDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PlayServiceImpl implements PlayService {

  private final PlayMapper playMapper;
  
  @Override
  public List<PlayDto> getPlayList() {
    return playMapper.getPlayList();
  }
  
  @Override
  public int addPlay(PlayDto playDto) {
    return playMapper.addPlay(playDto);
  }
  
  
}
