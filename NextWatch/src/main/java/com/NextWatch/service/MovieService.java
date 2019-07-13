package com.NextWatch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

}
