package com.NextWatch.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.NextWatch.beans.MovieBean;
import com.NextWatch.models.Movie;
import com.NextWatch.service.MovieService;

@Controller
public class MovieController {
	
	@Autowired
	MovieService movieService;
	
	//Function recieves name of the movie and calls API to gen movie info
	//Creates bean of type MovieBean and with that bean creates Movie object
	//Saves movie object to database and returns saved object
	public Movie getFromAPI(String name) throws ParseException {
		RestTemplate restTemplate = new RestTemplate();
		String fooResourceUrl = "http://www.omdbapi.com/?t="+name +"&apikey=c83996a9&fbclid=IwAR2ociOGRH-djAzQbinOzdpv4kuYalT-RpMnLJRYS1aK2TqOyI_Mi_hHC1M";
		ResponseEntity<MovieBean> response = restTemplate.getForEntity(fooResourceUrl + "/1", MovieBean.class);
		MovieBean movieBean = response.getBody();
		String title = movieBean.Title;
		String[] actors = movieBean.Actors.split(", ");
		HashSet<String> actorsSet = new HashSet<>();
		for (String actor : actors) {
			actorsSet.add(actor);
		}
		
		String[] genre = movieBean.Genre.split(", ");
		HashSet<String> genreSet = new HashSet<>();
		for (String genr : genre) {
			genreSet.add(genr);
		}
		String rated = movieBean.Rated;
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
		Date released = sdf.parse(movieBean.Released);
		String[] runtimeArray = movieBean.Runtime.split(" ");
		int runtime = Integer.parseInt(runtimeArray[0]);
		
		
		String[] writersArray = movieBean.Writer.split(", ");
		HashSet<String> writersSet = new HashSet<>();
		for (String write : writersArray) {
			writersSet.add(write);
		}
		
		Movie movie = new Movie(null, title, movieBean.Year, rated, released, runtime, genreSet, movieBean.Genre, writersSet, actorsSet, movieBean.Plot, movieBean.Language, movieBean.Language, movieBean.Poster, movieBean.imdbRating, movieBean.Production);
		return movieService.save(movie);
	}
	
	@CrossOrigin
	@RequestMapping(path="/api/call",method=RequestMethod.GET)
	public void call() throws ParseException {
		getFromAPI("Two towers");
	}

}
