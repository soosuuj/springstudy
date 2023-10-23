package com.gdu.app500.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.app500.dto.PlayDto;

@Mapper
public interface PlayMapper {
  
  int addPlay(PlayDto playDto);
  List<PlayDto> getPlayList();
  

}
