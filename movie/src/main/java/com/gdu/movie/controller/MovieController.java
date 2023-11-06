package com.gdu.movie.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gdu.movie.dto.MovieDto;
import com.gdu.movie.service.MovieServie;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController  // ajax
public class MovieController {
  
  private final MovieServie movieServie;
  
  @GetMapping(value = "/searchAllMovies", produces = "application/json")
  public Map<String, Object> searchAllMovies() {
    return movieServie.getMovieList();
  }
  
 
  @GetMapping("/search.do") //list 처럼
  public String search(HttpServletRequest request, Model model) {
    movieServie.loadSearchList(request, model);
    return "index";  // 포워드 - 리스트가 검색 결과로 바뀌어서 나오는 것으로 작성할거임
  }

}
