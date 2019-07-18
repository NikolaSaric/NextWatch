package com.NextWatch.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;

import org.springframework.core.io.WritableResource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.NextWatch.beans.MovieBean;
import com.NextWatch.models.Movie;
import com.NextWatch.service.MovieService;

@RestController
public class MovieController {

	@Autowired
	MovieService movieService;

	// If no movie was found in database, check API.
	// Function receives name of the movie and calls API to get movie info
	// Creates bean of type MovieBean and with that bean creates Movie object
	// Saves movie object to database and returns saved object
	public Movie getFromAPI(String name) throws ParseException {
		RestTemplate restTemplate = new RestTemplate();
		String fooResourceUrl = "http://www.omdbapi.com/?t=" + name
				+ "&apikey=c83996a9&fbclid=IwAR2ociOGRH-djAzQbinOzdpv4kuYalT-RpMnLJRYS1aK2TqOyI_Mi_hHC1M";
		ResponseEntity<MovieBean> response;
		MovieBean movieBean = new MovieBean();
		try {
			response = restTemplate.getForEntity(fooResourceUrl + "/1", MovieBean.class);
			movieBean = response.getBody();
		} catch (Exception e) {
			return null; // If there is some error with api
		}
		String title = movieBean.Title;
		if (title == null) {
			return null; // If movie is not found
		}
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

		Movie movie = new Movie(null, title, movieBean.Year, rated, released, runtime, genreSet, movieBean.Genre,
				writersSet, actorsSet, movieBean.Plot, movieBean.Language, movieBean.Country, movieBean.Poster,
				movieBean.imdbRating, movieBean.Production);

		// API returns different title from the one given, which implies another
		// database check.
		ArrayList<Movie> foundMovie = (ArrayList<Movie>) movieService.findByTitle(movie.getTitle());
		if (foundMovie == null || foundMovie.isEmpty()) {
			return movieService.save(movie);
		}
		return movie;

	}

	@CrossOrigin
	@RequestMapping(path = "/api/searchMovies/{title}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<Movie> searchMovies(@PathVariable("title") String title) throws ParseException {
		if (title.equals("".trim())) {
			return null;
		}

		ArrayList<Movie> foundMovies = (ArrayList<Movie>) movieService.findByTitle(title);

		if (foundMovies == null || foundMovies.isEmpty()) {
			// Search API.
			Movie apiMovie = this.getFromAPI(title);

			if (apiMovie == null) {
				return null;
			}

			foundMovies.add(apiMovie);
		}

		return foundMovies;
	}

	@CrossOrigin
	@RequestMapping(path = "/api/call", method = RequestMethod.GET)
	public void call() throws ParseException {
		getFromAPI("Lord of the rings");
	}
	
	@CrossOrigin
	@RequestMapping(path="/api/movie/{id}", method=RequestMethod.GET)
	//Returns Movie bean with given ID
	public @ResponseBody MovieBean getMovie(@PathVariable("id") Long id){
		Movie movie = movieService.findOne(id);
		MovieBean mb = new MovieBean();
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
		mb.Country = movie.getCountry();
		for (String actor : movie.getActors()) {
			if(mb.Actors==null) {
				mb.Actors = "" + actor;
			}
			else {
				mb.Actors= mb.Actors + ", " +actor;
			}
		}
		mb.Director = movie.getDirectors();
		for (String genre : movie.getGenre()) {
			if(mb.Genre==null) {
				mb.Genre = ""+genre;
			}else {
				mb.Genre = mb.Genre+", "+genre;
			}
		}
		mb.imdbRating = movie.getImdbRating();
		mb.Language = movie.getLanguage();
		mb.Plot = movie.getPlot();
		mb.Poster = movie.getPoster();
		mb.Production = movie.getProduction();
		mb.Rated = movie.getRated();
		mb.Released = sdf.format(movie.getReleased());
		mb.Runtime = movie.getRuntime() + " min";
		mb.Title = movie.getTitle();
		for (String writer : movie.getWriters()) {
			if(mb.Writer==null) {
				mb.Writer=""+writer;
			}else {
				mb.Writer=mb.Writer+", " + writer;
			}
		}
		mb.Year = movie.getYear();
		return mb;
	}

}
