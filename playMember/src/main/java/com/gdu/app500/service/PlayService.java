package com.gdu.app500.service;

import java.util.List;

import com.gdu.app500.dto.PlayDto;

public interface PlayService {
  
  List<PlayDto> getPlayList();
  
  int addPlay(PlayDto playDto);

}
