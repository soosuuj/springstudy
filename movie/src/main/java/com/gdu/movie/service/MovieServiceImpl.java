package com.gdu.movie.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

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
public Map<String, Object> searchMovie(HttpServletRequest request, HttpServletResponse response, MovieDto movie) {
  Map<String, Object> map = new HashMap<>();

  String column = request.getParameter("column");
  String searchText = request.getParameter("searchText");

  // 필수 파라미터가 누락되었을 때의 오류 처리
  if (column == null || searchText == null) {
    map.put("message", "검색 조건과 검색어를 모두 입력해야 합니다.");
    map.put("status", 400); // Bad Request
    return map;
  }

  // 검색을 수행하는 SQL 쿼리
  Map<String, Object> searchResults = movieMapper.searchMovie(movie);

  Map.of("message", searchResults.size() + "개의 검색 결과가 있습니다.");
  Map.of("list", searchResults);
  Map.of("status", 200);

  return map;
  
}
  
  
  
  
  
  

}
