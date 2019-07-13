package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.models.Movie;
public interface MovieRepository extends JpaRepository<Movie, Long> {

}
