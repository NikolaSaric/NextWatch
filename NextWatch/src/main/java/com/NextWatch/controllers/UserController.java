package com.NextWatch.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.NextWatch.beans.UserMovieRelation;
import com.NextWatch.beans.UserMoviesBean;
import com.NextWatch.models.Movie;
import com.NextWatch.models.User;
import com.NextWatch.service.MovieService;
import com.NextWatch.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	MovieService movieService;

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

	// Checks if movie is liked, or disliked or just watched or watch later.
	@CrossOrigin()
	@RequestMapping(value = "/api/getUserMovieRelations/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody UserMovieRelation getUserMovieRelations(@PathVariable("id") Long id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			Movie foundMovie = movieService.findOne(id);

			if (foundMovie == null) {
				return null;
			}

			String username = authentication.getName();
			User loggedUser = userService.findByUsername(username);

			UserMovieRelation umr = new UserMovieRelation();

			if (loggedUser.getLikedMovies().contains(foundMovie)) {
				umr.setLiked(true);
				umr.setWatched(true);
				return umr;
			}
			if (loggedUser.getDislikedMovies().contains(foundMovie)) {
				umr.setDisliked(true);
				umr.setWatched(true);
				return umr;
			}
			if (loggedUser.getWatchedMovies().contains(foundMovie)) {
				umr.setWatched(true);
				return umr;
			}
			if (loggedUser.getWatchLaterMovies().contains(foundMovie)) {
				umr.setWatchLater(true);
				return umr;
			}

			return umr;

		}
		return null;
	}

	// Adds movie to liked and watched.
	@CrossOrigin()
	@RequestMapping(value = "/api/movieLiked/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean movieLiked(@PathVariable("id") Long id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			Movie foundMovie = movieService.findOne(id);

			if (foundMovie == null) {
				return false;
			}

			String username = authentication.getName();
			User loggedUser = userService.findByUsername(username);

			loggedUser.getLikedMovies().add(foundMovie);
			loggedUser.getWatchedMovies().add(foundMovie);

			System.out.println("First!");
			System.out.println(foundMovie.getId());
			System.out.println(loggedUser.getId());
			userService.save(loggedUser);

			/*
			 * // Removes movie if it was disliked if
			 * (foundMovie.getUsersWhoDisliked().contains(loggedUser)) {
			 * foundMovie.getUsersWhoDisliked().remove(loggedUser); }
			 */
			System.out.println("Still working!");
			foundMovie.getUsersWhoLiked().add(loggedUser);
			System.out.println(loggedUser.getId());
			System.out.println(foundMovie.getId());
			movieService.save(foundMovie);

			return true;
		}
		return false;
	}

	// Removes movie from liked.
	@CrossOrigin()
	@RequestMapping(value = "/api/movieUnliked", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean movieUnliked(@RequestBody Long id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			Movie foundMovie = movieService.findOne(id);

			if (foundMovie == null) {
				return false;
			}

			String username = authentication.getName();
			User loggedUser = userService.findByUsername(username);

			loggedUser.getLikedMovies().remove(foundMovie);

			userService.save(loggedUser);

			foundMovie.getUsersWhoLiked().remove(loggedUser);
			movieService.save(foundMovie);

			return true;
		}
		return false;
	}

	// Adds movie to disliked movies.
	@CrossOrigin()
	@RequestMapping(value = "/api/movieDisliked", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean movieDisliked(@RequestBody Long id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			Movie foundMovie = movieService.findOne(id);

			if (foundMovie == null) {
				return false;
			}

			String username = authentication.getName();
			User loggedUser = userService.findByUsername(username);

			loggedUser.getDislikedMovies().add(foundMovie);
			loggedUser.getWatchedMovies().add(foundMovie);

			userService.save(loggedUser);

			foundMovie.getUsersWhoDisliked().add(loggedUser);
			movieService.save(foundMovie);

			return true;
		}
		return false;
	}

	// Removes movie from disliked.
	@CrossOrigin()
	@RequestMapping(value = "/api/movieUndisliked", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean movieUndisliked(@RequestBody Long id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			Movie foundMovie = movieService.findOne(id);

			if (foundMovie == null) {
				return false;
			}

			String username = authentication.getName();
			User loggedUser = userService.findByUsername(username);

			loggedUser.getDislikedMovies().remove(foundMovie);

			userService.save(loggedUser);

			foundMovie.getUsersWhoDisliked().remove(loggedUser);
			movieService.save(foundMovie);

			return true;
		}
		return false;
	}

	// Adds movie to watched.
	@CrossOrigin()
	@RequestMapping(value = "/api/movieWatched", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean movieWatched(@RequestBody Long id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			Movie foundMovie = movieService.findOne(id);

			if (foundMovie == null) {
				return false;
			}

			String username = authentication.getName();
			User loggedUser = userService.findByUsername(username);

			loggedUser.getWatchedMovies().add(foundMovie);

			if (loggedUser.getWatchLaterMovies().contains(foundMovie)) {
				loggedUser.getWatchLaterMovies().remove(foundMovie);
			}

			userService.save(loggedUser);

			return true;
		}
		return false;
	}

	// Removes movie from watched.
	@CrossOrigin()
	@RequestMapping(value = "/api/movieUnwatched", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean movieUnwatched(@RequestBody Long id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			Movie foundMovie = movieService.findOne(id);

			if (foundMovie == null) {
				return false;
			}

			String username = authentication.getName();
			User loggedUser = userService.findByUsername(username);

			loggedUser.getWatchedMovies().remove(foundMovie);

			userService.save(loggedUser);

			return true;
		}
		return false;
	}

	// Adds movie to watch later.
	@CrossOrigin()
	@RequestMapping(value = "/api/movieWatchLater", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean movieWatchLater(@RequestBody Long id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			Movie foundMovie = movieService.findOne(id);

			if (foundMovie == null) {
				return false;
			}

			String username = authentication.getName();
			User loggedUser = userService.findByUsername(username);

			loggedUser.getWatchLaterMovies().add(foundMovie);

			userService.save(loggedUser);

			return true;
		}
		return false;
	}

	// Removes movie from watch later.
	@CrossOrigin()
	@RequestMapping(value = "/api/movieUnwatchLater", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean movieUnwatchLater(@RequestBody Long id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			Movie foundMovie = movieService.findOne(id);

			if (foundMovie == null) {
				return false;
			}

			String username = authentication.getName();
			User loggedUser = userService.findByUsername(username);

			loggedUser.getWatchLaterMovies().remove(foundMovie);

			userService.save(loggedUser);

			return true;
		}
		return false;
	}
}
