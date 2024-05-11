package com.microservice.questionservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.microservice.questionservice.entity.Question;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer > {
	
	List<Question> findQuestionsByCategory(String category);

	@Query("SELECT q.id FROM Question q WHERE q.category = ?1 ORDER BY RANDOM() LIMIT ?2")
	List<Integer> findRandomQuestionsByCategory(String category, int numQ);
	
}
