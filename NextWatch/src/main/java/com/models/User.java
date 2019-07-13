package com.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;

	private Set<Movie> likedMovies;
	private Set<Movie> dislikedMovies;
	private Set<Movie> watchedMovies;
	private Set<Movie> watchLaterMovies;

	public User() {
		super();
	}

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Movie> getLikedMovies() {
		return likedMovies;
	}

	public void setLikedMovies(Set<Movie> likedMovies) {
		this.likedMovies = likedMovies;
	}

	public Set<Movie> getDislikedMovies() {
		return dislikedMovies;
	}

	public void setDislikedMovies(Set<Movie> dislikedMovies) {
		this.dislikedMovies = dislikedMovies;
	}

	public Set<Movie> getWatchedMovies() {
		return watchedMovies;
	}

	public void setWatchedMovies(Set<Movie> watchedMovies) {
		this.watchedMovies = watchedMovies;
	}

	public Set<Movie> getWatchLaterMovies() {
		return watchLaterMovies;
	}

	public void setWatchLaterMovies(Set<Movie> watchLaterMovies) {
		this.watchLaterMovies = watchLaterMovies;
	}

}
