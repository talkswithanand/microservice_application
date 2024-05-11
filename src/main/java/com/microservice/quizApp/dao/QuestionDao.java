package com.microservice.quizApp.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.microservice.quizApp.entity.Question;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer > {
	
	List<Question> findQuestionsByCategory(String category);

	@Query("SELECT q FROM Question q WHERE q.category = ?1 ORDER BY RANDOM() LIMIT ?2")
	List<Question> findRandomQuestionsByCategory(String category, int numQ);
	
}
