package com.gdu.movie.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gdu.movie.dto.MovieDto;

public interface MovieServie {
  
  public Map<String, Object> getMovieList();
  
  public Map<String, Object> searchMovie(HttpServletRequest request, HttpServletResponse response, MovieDto movie);

}
