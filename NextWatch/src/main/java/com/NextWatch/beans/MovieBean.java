package com.NextWatch.beans;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;

import com.NextWatch.models.Movie;

public class MovieBean {
	public Long id;
	public String Title;
	public int Year;
	public String Rated; // R rated, PG 13 etc.
	public String Released;
	public String Runtime; // in minutes
	public String Genre;
	public String Director; // set in case there are more directors
	public String Writer;
	public String Actors;
	public String Plot;
	public String Language;
	public String Country;
	public String Poster;
	public double imdbRating;
	public String Production;
	public MovieBean() {
		super();
	}
	public MovieBean(Movie movie) {
		this.id = movie.getId();
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
		this.Country = movie.getCountry();
		for (String actor : movie.getActors()) {
			if(this.Actors==null) {
				this.Actors = "" + actor;
			}
			else {
				this.Actors= this.Actors + ", " +actor;
			}
		}
		this.Director = movie.getDirectors();
		for (String genre : movie.getGenre()) {
			if(this.Genre==null) {
				this.Genre = ""+genre;
			}else {
				this.Genre = this.Genre+", "+genre;
			}
		}
		this.imdbRating = movie.getImdbRating();
		this.Language = movie.getLanguage();
		this.Plot = movie.getPlot();
		this.Poster = movie.getPoster();
		this.Production = movie.getProduction();
		this.Rated = movie.getRated();
		try {
			this.Released = sdf.format(movie.getReleased());
		} catch (Exception e) {
			this.Released = "Unknown";
		}
		this.Runtime = movie.getRuntime() + " min";
		this.Title = movie.getTitle();
		for (String writer : movie.getWriters()) {
			if(this.Writer==null) {
				this.Writer=""+writer;
			}else {
				this.Writer=this.Writer+", " + writer;
			}
		}
		this.Year = movie.getYear();
	}
	
	
	

}
