package com.NextWatch.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name="movie")
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
	
	@ElementCollection
	@Column(name = "genre")
	private Set<String> genre;
	
	@Column(name = "director")
	private String director; // set in case there are more directors
	
	@ElementCollection
	@Column(name = "writers")
	private Set<String> writers;
	
	@ElementCollection
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
	@Column(name = "NumberOfLikes")
	private int numberLikes;
	@Column(name = "NumberOfDislikes")
	private int numberDislikes;
	@ManyToMany(mappedBy="likedMovies")
	private Set<User> usersWhoLiked;
	
	@ManyToMany(mappedBy="dislikedMovies")
	private Set<User> usersWhoDisliked;

	public Movie() {
		super();
	}

	
	

	public Movie(Long id, String title, int year, String rated, Date released, int runtime, Set<String> genre,
			String director, Set<String> writers, Set<String> actors, String plot, String language, String country,
			String poster, double imdbRating, String production) {
		super();
		this.id = id;
		this.title = title;
		this.year = year;
		this.rated = rated;
		this.released = released;
		this.runtime = runtime;
		this.genre = genre;
		this.director = director;
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

	public String getDirectors() {
		return director;
	}

	public void setDirectors(String director) {
		this.director = director;
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




	public String getDirector() {
		return director;
	}




	public void setDirector(String director) {
		this.director = director;
	}




	public int getNumberLikes() {
		return numberLikes;
	}




	public void setNumberLikes(int numberLikes) {
		this.numberLikes = numberLikes;
	}




	public int getNumberDislikes() {
		return numberDislikes;
	}




	public void setNumberDislikes(int numberDislikes) {
		this.numberDislikes = numberDislikes;
	}




	public Set<User> getUsersWhoLiked() {
		return usersWhoLiked;
	}




	public void setUsersWhoLiked(Set<User> usersWhoLiked) {
		this.usersWhoLiked = usersWhoLiked;
	}




	public Set<User> getUsersWhoDisliked() {
		return usersWhoDisliked;
	}




	public void setUsersWhoDisliked(Set<User> usersWhoDisliked) {
		this.usersWhoDisliked = usersWhoDisliked;
	}
	

}
