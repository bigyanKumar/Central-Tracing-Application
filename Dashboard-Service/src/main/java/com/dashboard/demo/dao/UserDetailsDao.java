package com.dashboard.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dashboard.demo.model.UserDetails;

@Repository
public interface UserDetailsDao extends JpaRepository<UserDetails, Integer>{

}
