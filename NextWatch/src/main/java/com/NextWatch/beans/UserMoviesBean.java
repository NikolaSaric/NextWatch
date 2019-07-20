package com.NextWatch.beans;

import java.util.Set;

import com.NextWatch.models.Movie;

public class UserMoviesBean {
	private Set<Movie> likedMovies;
	private Set<Movie> dislikedMovies;
	private Set<Movie> watchedMovies;
	private Set<Movie> watchLaterMovies;

	public UserMoviesBean() {
	}

	public UserMoviesBean(Set<Movie> likedMovies, Set<Movie> dislikedMovies, Set<Movie> watchedMovies,
			Set<Movie> watchLaterMovies) {
		this.likedMovies = likedMovies;
		this.dislikedMovies = dislikedMovies;
		this.watchedMovies = watchedMovies;
		this.watchLaterMovies = watchLaterMovies;
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
