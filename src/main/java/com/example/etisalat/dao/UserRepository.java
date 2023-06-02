package com.example.etisalat.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.etisalat.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
