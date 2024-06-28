package com.joy.dao;

import org.springframework.data.repository.CrudRepository;

import com.joy.entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
