package com.gdu.movie.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface MovieServie {
  
  public Map<String, Object> getMovieList();
  
  public Map<String, Object> loadSearchList(HttpServletRequest request, Model model);

}
