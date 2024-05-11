package com.microservice.questionservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.questionservice.entity.Question;
import com.microservice.questionservice.entity.QuestionWrapper;
import com.microservice.questionservice.entity.Response;
import com.microservice.questionservice.service.QuestionService;

@RestController
@RequestMapping("question")
public class QuestionController {

	@Autowired
	QuestionService questionService;

	@GetMapping("allQuestions")
	public ResponseEntity<List<Question>> getAllQuestions() {
		return questionService.getAllQuestions();
	}

	@PostMapping("createQuestion")
	public ResponseEntity<String> createQuestion(@RequestBody Question question) {
		return questionService.saveQuestion(question);
	}

	@GetMapping("catergory/{category}")
	public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {
		return questionService.getQuestionsByCategory(category);
	}

	@DeleteMapping("delete/{id}")
	public String deleteQuestionById(@PathVariable int id) {
		return questionService.deleteQuestionById(id);
	}

	// quizService request question service to generate quiz
	@GetMapping("generate")
	public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String questionCategory,
			@RequestParam int numQues) {
		return questionService.getQuestionsForQuiz(questionCategory, numQues);
	}

	// getQuestions(question id)
	@PostMapping("getQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds){
		return questionService.getQuestionsFromId(questionIds);
	}
	
	// getScore
	@PostMapping("getScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
		return questionService.getScore(responses);
	}
}
