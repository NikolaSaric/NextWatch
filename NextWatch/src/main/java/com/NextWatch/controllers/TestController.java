package com.NextWatch.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class TestController {

	@CrossOrigin()
	@RequestMapping(value = "/getTest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getTest() {
		return "Get Test was a success!";
	}
	
	
}
