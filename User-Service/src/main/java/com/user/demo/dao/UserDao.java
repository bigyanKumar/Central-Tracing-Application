package com.user.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.demo.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer>{

}
