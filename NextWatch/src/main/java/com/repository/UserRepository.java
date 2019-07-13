package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
