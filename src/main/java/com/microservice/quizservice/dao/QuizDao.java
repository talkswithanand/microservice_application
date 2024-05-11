package com.microservice.quizservice.dao;

import java.util.List;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.quizservice.entity.Quiz;

@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer>{
	
	List<Question> findAllById(int id);
	
}
