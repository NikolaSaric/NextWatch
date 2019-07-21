package com.NextWatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.NextWatch.models.SimilarMovie;

public interface SimilarMoviesRepository extends JpaRepository<SimilarMovie, Long> {

}
