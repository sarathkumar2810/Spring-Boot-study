package com.sample.sample.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.sample.Quiz;

@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer> {

}
