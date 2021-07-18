package com.spraju.movieinfo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.spraju.movieinfo.configurations.ConfigData;
import com.spraju.movieinfo.model.MovieFromDB;
import com.spraju.movieinfo.model.MovieInfo;

@RestController
@RequestMapping("/movieInfo")
@RefreshScope
public class MovieInfoController {
	
	@Autowired
	ConfigData configData;
	
	@Value("${api.key}")
	String apikey;
	
	@Value("${configdemo.list}")
	List<String> demonames;
	
	@Value("#{${configdemo.map}}")
	Map<String, String> demomapname;
	
	RestTemplate restTemplate=new RestTemplate();
	
	@GetMapping("/{movieId}")
	public MovieInfo getMovieInfo(@PathVariable Integer movieId) {
		System.out.println(demonames);
		System.out.println(demomapname);
		System.out.println(demomapname.get("owner"));
		System.out.println(configData.getList());
		System.out.println(configData.getMap());
		
		
		
//		List<MovieInfo> movieInfos= Arrays.asList(new MovieInfo(1, "pulimurugen", "mass"),new MovieInfo(2, "one", "political")
//							,new MovieInfo(3, "ezra", "horror"),new MovieInfo(4, "coldcase", "mystery")
//							,new MovieInfo(5, "2 countries", "comedy"),new MovieInfo(6, "priest", "fiction"));
//		
//		MovieInfo specificMovieInfo=null;
//		ListIterator<MovieInfo> itr=movieInfos.listIterator();
//		while(itr.hasNext()) {
//			if(itr.next().getMovieId().equals(movieId)) {
//				itr.previous();
//				specificMovieInfo=itr.next();
//			}
//				
//		}
//		return specificMovieInfo;
//		try
//		{
//		    Thread.sleep(5000);
//		}
//		catch(InterruptedException ex)
//		{
//		    Thread.currentThread().interrupt();
//		}
		MovieFromDB movieFromDB=restTemplate.getForObject("https://api.themoviedb.org/3/movie/"+movieId+"?api_key="+apikey, MovieFromDB.class);
		return new MovieInfo(movieId, movieFromDB.getOriginal_title(), movieFromDB.getOverview());
	}

}
