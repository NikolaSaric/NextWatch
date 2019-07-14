package com.NextWatch.beans;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;

public class MovieBean {
	public String Title;
	public int Year;
	public String Rated; // R rated, PG 13 etc.
	public String Released;
	public String Runtime; // in minutes
	public String Genre;
	public String Director; // set in case there are more directors
	public String Writer;
	public String Actors;
	public String Plot;
	public String Language;
	public String Country;
	public String Poster;
	public double imdbRating;
	public String Production;
	public MovieBean() {
		super();
	}
	
	
	

}
