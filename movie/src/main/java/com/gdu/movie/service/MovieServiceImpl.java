package com.gdu.movie.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gdu.movie.dao.MovieMapper;
import com.gdu.movie.dto.MovieDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MovieServiceImpl implements MovieServie {
  
  private final MovieMapper movieMapper;
  
  @Override
  public Map<String, Object> getMovieList() {
    
    int movieCount = movieMapper.getMovieCount();
    List<MovieDto> list = movieMapper.getMovieList();
    
    
    
    return Map.of("message", "전체 " + movieCount + "개의 목록을 가져왔습니다."
                  , "list", list
                  , "status", 200);
    
    
    
  }
  
 
  @Override
  public Map<String, Object> loadSearchList(HttpServletRequest request, Model model) {
      String column = request.getParameter("column");
      String searchText = request.getParameter("searchText");
      
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("column", column);
      map.put("searchText", searchText);
      
      List<MovieDto> movieList = movieMapper.getSearchMovie(map);
      
      model.addAttribute("movie_list", movieList); // "movie_list"로 결과를 추가
      
      // 수정된 결과 데이터를 반환
      Map<String, Object> resultMap = new HashMap<String, Object>();
      resultMap.put("movie_list", movieList);
      return resultMap;
  }

  
  
  
  
  
  

}
