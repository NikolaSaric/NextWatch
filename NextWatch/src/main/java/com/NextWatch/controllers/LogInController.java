package com.NextWatch.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.NextWatch.beans.LoggingBean;
import com.NextWatch.beans.RegisterNewUserBean;
import com.NextWatch.models.User;
import com.NextWatch.service.UserService;

@RestController
@RequestMapping(value = "/auth")
public class LogInController {

	@Autowired
	UserService userService;

	@CrossOrigin()
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String login(@RequestBody LoggingBean lb) {
		// Checks if logIn data is correct
		if (lb.getUsername() == null || lb.getUsername().equals("".trim())) {
			return "Please enter username.";
		}

		if (lb.getPassword() == null || lb.getPassword().equals("".trim())) {
			return "Please enter password.";
		}

		User foundUser = userService.findByUsername(lb.getUsername());
		if (foundUser == null) {
			return "Incorrect username.";
		}

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		if (encoder.matches(lb.getPassword(), foundUser.getPassword())) {
			// Implement JWT token for logged user.
			return "Successfully logged in.";
		}

		return "Incorrect password.";
	}

	@CrossOrigin()
	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String register(@RequestBody RegisterNewUserBean rb) {
		// Checks if user data is correct.
		if (rb.getUsername() == null || rb.getUsername().equals("".trim())) {
			return "Please enter username.";
		}

		if (rb.getPassword() == null || rb.getRepeatPassword() == null || rb.getPassword().equals("".trim())
				|| !rb.getPassword().equals(rb.getRepeatPassword())) {
			return "Please enter password.";
		}

		if (rb.getEmail() == null || rb.getEmail().equals("".trim())) {
			return "Please enter email.";
		}

		if (userService.findByUsername(rb.getUsername()) != null) {
			return "Username is taken.";
		}

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		User newUser = new User(rb.getUsername(), encoder.encode(rb.getPassword()), rb.getEmail());
		userService.save(newUser);

		return "User " + rb.getUsername() + " is successfully registered.";
	}
}
