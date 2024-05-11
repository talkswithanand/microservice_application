package com.microservice.quizservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microservice.quizservice.dao.QuizDao;
import com.microservice.quizservice.entity.QuestionWrapper;
import com.microservice.quizservice.entity.Quiz;
import com.microservice.quizservice.entity.Response;
import com.microservice.quizservice.feign.QuizInterface;

@Service
public class QuizService {

	@Autowired
	QuizDao quizDao;

	@Autowired
	QuizInterface quizInterface;

	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		
		List<Integer> questions = quizInterface.getQuestionsForQuiz(category, numQ).getBody();
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestionIds(questions);
		quizDao.save(quiz);
		
		return new ResponseEntity<String>("Created", HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
//		Optional<Quiz> quiz = quizDao.findById(id);
//		List<Question> questionsFromDB = quiz.get().getQuestions();
		List<QuestionWrapper> questionsForUser = new ArrayList<>();
//		for (Question q : questionsFromDB) {
//			QuestionWrapper qW = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(),
//					q.getOption3(), q.getOption4());
//			questionsForUser.add(qW);
//		}
		return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
	}

	public ResponseEntity<Integer> calculateResult(int id, List<Response> responses) {
		Quiz quiz = quizDao.findById(id).get();
//		List<Question> questions = quiz.getQuestions();
		int result = 0;
//		int i = 0;
//		for (Response response : responses) {
//			if (response.getResponse().equals(questions.get(i++).getRightAnswer())) {
//				result++;
//			}
//		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
}
