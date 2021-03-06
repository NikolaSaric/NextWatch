package com.NextWatch.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.stereotype.Service;

import com.NextWatch.models.Movie;
import com.NextWatch.repository.MovieRepository;

@Service
public class MovieService {
	@Autowired
	MovieRepository repository;

	public Movie findOne(Long id) {
		return repository.getOne(id);
	}

	public List<Movie> findAll() {
		return repository.findAll();
	}

	public Movie save(Movie movie) {
		return repository.save(movie);
	}

	public void remove(Long id) {
		repository.deleteById(id);
	}

	public List<Movie> findByTitleAndReleased(String title, Date released) {
		return repository.findByTitleAndReleased(title, released);
	}

	public List<Movie> findByTitle(String title) {
		return repository.findByTitle(title);
	}
	public List<Movie> findTop20ByOrderByReleasedDesc(){
		return repository.findTop20ByOrderByReleasedDesc();
	}
	public List<Movie> findTop20ByOrderBynumberLikesDesc(){
		return repository.findTop20ByOrderByNumberLikesDesc();
	}

}
