package com.gdu.movie.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
  
//  @RequestMapping(value = "/searchMovie", method = RequestMethod.GET)
//  @ResponseBody
//  public Map<String, Object> searchMovie(@RequestParam(value = "column", required = true) String column,
//                                         @RequestParam(value = "searchText", required = true) String searchText) {
////    MovieDto movie = new MovieDto();
////    movie.setColumn(column);
////    movie.setSearchText(searchText);
//
//    return Map.of(); //movieService.searchMovie(movie);
//  }
 
  @GetMapping(value = "/searchMovie", produces = "application/json")
  public Map<String, Object> searchMovie(HttpServletRequest request, HttpServletResponse response){
    
    return Map.of();
  }

}
