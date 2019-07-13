package com.NextWatch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.NextWatch.models.User;
import com.NextWatch.repository.UserRepository;


@Service
public class UserService {
	@Autowired
	UserRepository repository;
	
	public User findOne(Long id) {
		return repository.getOne(id);
	}

	public List<User> findAll() {
		return repository.findAll();
	}

	public User save(User user) {
		return repository.save(user);
	}

	public void remove(Long id) {
		repository.deleteById(id);
	}



}
