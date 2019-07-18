package com.NextWatch.controllers;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.NextWatch.models.Movie;
import com.NextWatch.service.MovieService;

@RestController
@RequestMapping(value = "/test")
public class TestController {

	@Autowired
	MovieService movieService;

	@CrossOrigin()
	@RequestMapping(value = "/getTest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getTest() {
		return "Get Test was a success!";
	}

	@CrossOrigin()
	@RequestMapping(value = "/addTestMovies", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean addTestMovies(@RequestBody Movie movie) {
		if (movie == null) {
			return false;
		}

		movieService.save(movie);
		return true;
	}

	@CrossOrigin()
	@RequestMapping(value = "/addConcreteMovies", method = RequestMethod.GET)
	public void addConcreteMovies() {
		Movie m1 = new Movie();
		m1.setCountry("Serbia");
		Set<String> a1 = new HashSet<String>();
		a1.add("Nikola");
		a1.add("Mile");
		m1.setActors(a1);
		m1.setDirectors("Jelena");
		Set<String> g1 = new HashSet<String>();
		g1.add("Horror");
		g1.add("Comedy");
		m1.setGenre(g1);
		m1.setImdbRating(9.9);
		m1.setLanguage("German");
		m1.setPlot("Two students try to implement web app.");
		m1.setPoster("No Poster available.");
		m1.setProduction("Domaca radinost.");
		m1.setRated("R");
		m1.setReleased(new Date());
		m1.setRuntime(91);
		m1.setTitle("NextWatch");
		Set<String> w1 = new HashSet<String>();
		w1.add("Jeca");
		m1.setWriters(w1);

		Movie m2 = new Movie();
		m2.setCountry("Italy");
		Set<String> a2 = new HashSet<String>();
		a2.add("Jovica");
		a2.add("Stevica");
		m2.setActors(a2);
		m2.setDirectors("Pasolini");
		Set<String> g2 = new HashSet<String>();
		g2.add("Neorealism");
		m2.setGenre(g2);
		m2.setImdbRating(8.5);
		m2.setLanguage("Italian");
		m2.setPlot("Something about Christ's suffering.");
		m2.setPoster("No Poster available.");
		m2.setProduction("Cinema Italiana.");
		m2.setRated("PG 13");
		m2.setReleased(new Date());
		m2.setRuntime(120);
		m2.setTitle("Third Coming");
		Set<String> w2 = new HashSet<String>();
		w2.add("Pasolini");
		m2.setWriters(w2);

		Movie m3 = new Movie();
		m3.setCountry("USA");
		Set<String> a3 = new HashSet<String>();
		a3.add("Nicolas");
		m3.setActors(a3);
		m3.setDirectors("Nicolas");
		Set<String> g3 = new HashSet<String>();
		g3.add("Horror");
		g3.add("Monodrama");
		m3.setGenre(g3);
		m3.setImdbRating(-3);
		m3.setLanguage("English");
		m3.setPlot("Young man talks to his hand while high on Vinjak.");
		m3.setPoster("Imagine something sad.");
		m3.setProduction("Domaca radinost.");
		m3.setRated("R");
		m3.setReleased(new Date());
		m3.setRuntime(300);
		m3.setTitle("Vinjaception");
		Set<String> w3 = new HashSet<String>();
		w3.add("Nicolas");
		m3.setWriters(w3);

		movieService.save(m1);
		movieService.save(m2);
		movieService.save(m3);
	}
}
