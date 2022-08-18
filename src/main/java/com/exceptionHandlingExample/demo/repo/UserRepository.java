package com.exceptionHandlingExample.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exceptionHandlingExample.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
