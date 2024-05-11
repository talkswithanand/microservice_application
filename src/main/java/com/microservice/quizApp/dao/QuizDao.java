package com.microservice.quizApp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.quizApp.entity.Question;
import com.microservice.quizApp.entity.Quiz;

@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer>{
	
	List<Question> findAllById(int id);
	
}
