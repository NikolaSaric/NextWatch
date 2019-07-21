package com.NextWatch.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.NextWatch.models.SimilarMovie;
import com.NextWatch.repository.SimilarMoviesRepository;

@Service
public class SimilarMoviesService {

	@Autowired
	SimilarMoviesRepository repository;

	public SimilarMovie findOne(Long id) {
		return repository.getOne(id);
	}

	public List<SimilarMovie> findAll() {
		return repository.findAll();
	}

	public SimilarMovie save(SimilarMovie movie) {
		return repository.save(movie);
	}

	public void remove(Long id) {
		repository.deleteById(id);
	}
	public List<SimilarMovie> saveAll (Set<SimilarMovie> seats){
		return repository.saveAll(seats);
	}
}
