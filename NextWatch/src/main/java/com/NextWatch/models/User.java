package com.NextWatch.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name = "User")
public class User {
	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "email")
	private String email;

	@OneToMany
	private Set<Movie> likedMovies;
	@OneToMany
	private Set<Movie> dislikedMovies;
	@OneToMany
	private Set<Movie> watchedMovies;
	@OneToMany
	private Set<Movie> watchLaterMovies;

	public User() {
		super();
	}

	public User(String username, String password, String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.likedMovies = new HashSet<Movie>();
		this.dislikedMovies = new HashSet<Movie>();
		this.watchedMovies = new HashSet<Movie>();
		this.watchLaterMovies = new HashSet<Movie>();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
