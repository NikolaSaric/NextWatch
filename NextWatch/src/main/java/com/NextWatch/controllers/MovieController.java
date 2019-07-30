package com.NextWatch.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
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
import com.NextWatch.models.SimilarMovie;
import com.NextWatch.models.User;
import com.NextWatch.service.MovieService;
import com.NextWatch.service.SimilarMoviesService;

import static java.util.stream.Collectors.*;
@RestController
public class MovieController {

	@Autowired
	MovieService movieService;
	
	@Autowired
	SimilarMoviesService similarService;

	// If no movie was found in database, check API.
	// Function receives name of the movie and calls API to get movie info
	// Creates bean of type MovieBean and with that bean creates Movie object
	// Saves movie object to database and returns saved object
	public ArrayList<Movie> getFromAPI(String name) throws ParseException {
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
		Date released = null;
		try {
			released = sdf.parse(movieBean.Released);
		} catch (Exception e) {
		}
		String[] runtimeArray = movieBean.Runtime.split(" ");
		int runtime = Integer.parseInt(runtimeArray[0]);

		String[] writersArray = movieBean.Writer.split(", ");
		HashSet<String> writersSet = new HashSet<>();
		for (String write : writersArray) {
			writersSet.add(write);
		}

		// API returns different title from the one given, which implies another
		// database check.
		ArrayList<Movie> foundMovie = (ArrayList<Movie>) movieService.findByTitle(title);
		if (foundMovie == null || foundMovie.isEmpty()) {
			Movie movie = new Movie(null, title, movieBean.Year, rated, released, runtime, genreSet, movieBean.Director,
					writersSet, actorsSet, movieBean.Plot, movieBean.Language, movieBean.Country, movieBean.Poster,
					movieBean.imdbRating, movieBean.Production);
			ArrayList<Movie> newMovie = new ArrayList<Movie>();
			newMovie.add(movieService.save(movie));
			return newMovie;
		}
		return foundMovie;

	}

	@CrossOrigin
	@RequestMapping(path = "/api/searchMovies/{title}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<MovieBean> searchMovies(@PathVariable("title") String title) throws ParseException {
		if (title.equals("".trim())) {
			return null;
		}

		ArrayList<Movie> foundMovies = (ArrayList<Movie>) movieService.findByTitle(title);
		ArrayList<MovieBean> foundMovieBeans = new ArrayList<>();
		if (foundMovies == null || foundMovies.isEmpty()) {
			// Search API.
			ArrayList<Movie> apiMovie = this.getFromAPI(title);

			if (apiMovie == null) {
				return null;
			}
			for (Movie movie : apiMovie) {
				foundMovieBeans.add(new MovieBean(movie));

			}
		}

		return foundMovieBeans;
	}

	@CrossOrigin
	@RequestMapping(path = "/api/call", method = RequestMethod.GET)
	public void call() throws ParseException {
		getFromAPI("Lord of the rings");
	}

	@CrossOrigin
	@RequestMapping(path = "/api/movie/{id}", method = RequestMethod.GET)
	// Returns Movie bean with given ID
	public @ResponseBody MovieBean getMovie(@PathVariable("id") Long id) {
		Movie movie = movieService.findOne(id);
		MovieBean mb = new MovieBean(movie);
		return mb;
	}

	@CrossOrigin
	@RequestMapping(path = "/api/getNewest", method = RequestMethod.GET)
	public @ResponseBody ArrayList<MovieBean> getNewest() {
		ArrayList<Movie> movies = (ArrayList<Movie>) movieService.findTop20ByOrderByReleasedDesc();
		ArrayList<MovieBean> mbeans = new ArrayList<>();
		for (Movie movie : movies) {
			mbeans.add(new MovieBean(movie));
		}
		return mbeans;
	}

	@CrossOrigin
	@RequestMapping(path = "/api/getPopular", method = RequestMethod.GET)
	public @ResponseBody ArrayList<MovieBean> getPopular() {
		ArrayList<Movie> movies = (ArrayList<Movie>) movieService.findTop20ByOrderBynumberLikesDesc();
		ArrayList<MovieBean> mbeans = new ArrayList<>();
		for (Movie movie : movies) {
			mbeans.add(new MovieBean(movie));
		}
		return mbeans;
	}

	@CrossOrigin
	@RequestMapping(path = "/api/getSimilar/{id}")
	public @ResponseBody ArrayList<MovieBean> getSimilar(@PathVariable("id") Long id) {
		Movie movie = movieService.findOne(id);
		if (movie == null) {
			return null;
		}
		
		//If last update of similar movies was less then 3 days ago - don't calculate again
		if(movie.getLastUpdatedSimilar()!=null) {
			Date dateNow = new Date();
			if(movie.getLastUpdatedSimilar().getTime()+1000*3600*24*3>dateNow.getTime()) {
				ArrayList<MovieBean> mbArray = new ArrayList<>();
				//Sorts movies from database
				ArrayList<SimilarMovie> similarMovies = new ArrayList<>();
				similarMovies.addAll(movie.getSimilarMovies());
				for(int i =0;i<similarMovies.size();i++) {
					for(int j=i+1;j<similarMovies.size();j++) {
						if(similarMovies.get(i).getScore()<similarMovies.get(j).getScore()) {
							SimilarMovie similar = similarMovies.get(i);
							similarMovies.set(i, similarMovies.get(j));
							similarMovies.set(j, similar);
						}
					}
				}
				
				int i = 0;

				
				for (SimilarMovie m : similarMovies) {
					System.out.println(m.getScore() + m.getMovie().getTitle());
				
					mbArray.add(new MovieBean(m.getMovie()));
					//Vraca samo 20 najslicnijih filmova
					if(i++>=20) {
						break;
					}
				}
				
				
				return mbArray;
			}
		}

		HashMap<Movie, Double> movieMap = new HashMap<>();

		ArrayList<Movie> allMovies = (ArrayList<Movie>) movieService.findAll();
		// Iterate through all movies, add them to dictionary.
		for (Movie otherMovie : allMovies) {
			movieMap.put(otherMovie, 0.0);
			// Skip same movie.
			if (otherMovie.getId().equals(movie.getId())) {
				continue;
			}
			// Check for movies with same genre, add 1 point to dictionary for every
			// matching genre.
			for (String genre : movie.getGenre()) {
				for (String otherGenre : otherMovie.getGenre()) {
					if (genre.equals(otherGenre)) {
						movieMap.put(otherMovie, movieMap.get(otherMovie) + 1);
					}
				}
			}
			// Check for movies with same actors, add 1 point to dictionary for every
			// matching actor.
			for (String actor : movie.getActors()) {
				for (String otherActor : otherMovie.getActors()) {
					if (actor.equals(otherActor)) {
						movieMap.put(otherMovie, movieMap.get(otherMovie) + 1);
					}
				}
			}
			// Check for movies with same director, add 1 point to dictionary for every
			// matching director.
			String[] directors = movie.getDirector().split(", ");
			for (String director : directors) {
				if (otherMovie.getDirector().contains(director)) {
					movieMap.put(otherMovie, movieMap.get(otherMovie) + 1);
				}
			}
			// Check for movie with same rated category, add 1 point to dictionary for
			// matching category.
			if (movie.getRated().equals(otherMovie.getRated())) {
				movieMap.put(otherMovie, movieMap.get(otherMovie) + 1);
			}
			// Check for movie with same production, add 1 point to dictionary for matching
			// production.
			if (movie.getProduction().equals(otherMovie.getProduction())) {
				movieMap.put(otherMovie, movieMap.get(otherMovie) + 1);
			}
			for (User userWhoLikedMovie : movie.getUsersWhoLiked()) {
				for (Movie userOtherMovies : userWhoLikedMovie.getLikedMovies()) {
					if (movie.getId().equals(userOtherMovies)) {
						continue;
					}
					movieMap.put(otherMovie, movieMap.get(otherMovie) + 1);
				}
			}
			movieMap.put(otherMovie, movieMap.get(otherMovie) + otherMovie.getImdbRating());
		}

		
		ArrayList<Movie> movies = new ArrayList<>(movieMap.keySet());
		ArrayList<Double> values = new ArrayList<>(movieMap.values());
		
		for(int i = 0; i<movies.size();i++) {
			for(int j=i+1;j<movies.size();j++) {
				if(values.get(j)>values.get(i)) {
					Movie m = movies.get(i);
					movies.set(i, movies.get(j));
					movies.set(j, m);
					
					Double v = values.get(i);
					values.set(i, values.get(j));
					values.set(j,v);
				}
			}
		}

		/*for (Map.Entry<Movie, Double> entry : movieMap.entrySet()) {
			System.out.println(entry.getKey().getTitle() + "  " + entry.getValue());
		}*/
		ArrayList<MovieBean> mbArray = new ArrayList<>();
		HashSet<SimilarMovie> similarMovies= new HashSet<>(); // Save only 20 movies;
		int i = 0;
		for (Movie m : movies) {
			mbArray.add(new MovieBean(m));
			similarMovies.add(new SimilarMovie(m, values.get(i)));
			if(i++>=20) {
				break;
			}
		}
		similarService.saveAll(similarMovies);
		for (Double m : values) {
			System.out.println(m);
		}
		
		
		
		//Saves calculated movies
		movie.setSimilarMovies(similarMovies);
		
		
		
		movie.setLastUpdatedSimilar(new Date());
		
		
		movieService.save(movie);


		return  mbArray;
	}

}
