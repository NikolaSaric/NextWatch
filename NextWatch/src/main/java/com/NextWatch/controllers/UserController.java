package com.NextWatch.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.NextWatch.beans.UserMoviesBean;
import com.NextWatch.models.User;
import com.NextWatch.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@CrossOrigin()
	@RequestMapping(value = "/api/getUserMovies", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserMoviesBean getUserMovies() {

		// Checks if user is logged, if it is returns his liked,disliked,watched and
		// watch later movies.
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String username = authentication.getName();
			User loggedUser = userService.findByUsername(username);

			UserMoviesBean umb = new UserMoviesBean();
			umb.setDislikedMovies(loggedUser.getDislikedMovies());
			umb.setLikedMovies(loggedUser.getLikedMovies());
			umb.setWatchedMovies(loggedUser.getWatchedMovies());
			umb.setWatchLaterMovies(loggedUser.getWatchLaterMovies());

			return umb;
		}
		return null;
	}
}
