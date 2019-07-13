package com.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Movie {
	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "title")
	private String title;
	@Column(name = "year")
	private int year;
	@Column(name = "rated")
	private String rated; // R rated, PG 13 etc.
	@Column(name = "released")
	private Date released;
	@Column(name = "runtime")
	private int runtime; // in minutes
	@Column(name = "genre")
	private Set<String> genre;
	@Column(name = "directors")
	private Set<String> directors; // set in case there are more directors
	@Column(name = "writers")
	private Set<String> writers;
	@Column(name = "actors")
	private Set<String> actors;
	@Column(name = "plot")
	private String plot;
	@Column(name = "language")
	private String language;
	@Column(name = "country")
	private String country;
	@Column(name = "poster")
	private String poster;
	@Column(name = "imdbRating")
	private double imdbRating;
	@Column(name = "production")
	private String production;

	public Movie() {
		super();
	}

	public Movie(String title, int year, String rated, Date released, int runtime, Set<String> genre,
			Set<String> directors, Set<String> writers, Set<String> actors, String plot, String language,
			String country, String poster, double imdbRating, String production) {
		super();
		this.title = title;
		this.year = year;
		this.rated = rated;
		this.released = released;
		this.runtime = runtime;
		this.genre = genre;
		this.directors = directors;
		this.writers = writers;
		this.actors = actors;
		this.plot = plot;
		this.language = language;
		this.country = country;
		this.poster = poster;
		this.imdbRating = imdbRating;
		this.production = production;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getRated() {
		return rated;
	}

	public void setRated(String rated) {
		this.rated = rated;
	}

	public Date getReleased() {
		return released;
	}

	public void setReleased(Date released) {
		this.released = released;
	}

	public int getRuntime() {
		return runtime;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	public Set<String> getGenre() {
		return genre;
	}

	public void setGenre(Set<String> genre) {
		this.genre = genre;
	}

	public Set<String> getDirectors() {
		return directors;
	}

	public void setDirectors(Set<String> directors) {
		this.directors = directors;
	}

	public Set<String> getWriters() {
		return writers;
	}

	public void setWriters(Set<String> writers) {
		this.writers = writers;
	}

	public Set<String> getActors() {
		return actors;
	}

	public void setActors(Set<String> actors) {
		this.actors = actors;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public double getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(double imdbRating) {
		this.imdbRating = imdbRating;
	}

	public String getProduction() {
		return production;
	}

	public void setProduction(String production) {
		this.production = production;
	}

}
