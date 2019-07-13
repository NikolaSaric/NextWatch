package com.NextWatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.NextWatch.models.Movie;
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

}
