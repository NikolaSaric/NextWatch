package com.NextWatch.beans;

public class RegisterNewUserBean {
	private String username;
	private String password;
	private String repeatPassword;
	private String email;

	public RegisterNewUserBean() {
	}

	public RegisterNewUserBean(String username, String password, String repeatPassword, String email) {
		this.username = username;
		this.password = password;
		this.repeatPassword = repeatPassword;
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
