package com.NextWatch.repository;

import java.util.Date;
import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.NextWatch.models.Movie;
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
	List<Movie> findByTitleAndReleased(String title, Date released);
	List<Movie> findByTitle(String title);
	
	List<Movie> findTop20ByOrderByReleasedDesc();
	
}
