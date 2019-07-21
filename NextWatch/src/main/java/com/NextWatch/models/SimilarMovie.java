package com.NextWatch.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class SimilarMovie {
	
	@GeneratedValue
	@Id
	private Long id;
	@ManyToOne
	private Movie movie;
	@Column(name="score")
	private Double score;
	
	public SimilarMovie() {
		super();
	}
	
	public SimilarMovie(Movie movie, Double score) {
		super();
		this.movie = movie;
		this.score = score;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	
	

}
