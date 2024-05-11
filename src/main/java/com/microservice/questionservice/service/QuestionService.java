package com.microservice.questionservice.service;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microservice.questionservice.dao.QuestionDao;
import com.microservice.questionservice.entity.Question;
import com.microservice.questionservice.entity.QuestionWrapper;
import com.microservice.questionservice.entity.Response;

@Service
public class QuestionService {

	@Autowired
	QuestionDao questionDao;

	public ResponseEntity<List<Question>> getAllQuestions() {
		try {
			return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> saveQuestion(Question question) {
		try {
			questionDao.save(question);
			return new ResponseEntity<>("Added", HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("not added", HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
		try {
			return new ResponseEntity<>(questionDao.findQuestionsByCategory(category), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	public String deleteQuestionById(int id) {
		questionDao.deleteById(id);
		return "Deleted";
	}

	public ResponseEntity<List<Integer>> getQuestionsForQuiz(String questionCategory, int numQues) {
		List<Integer> questions = questionDao.findRandomQuestionsByCategory(questionCategory, numQues);
		return new ResponseEntity<List<Integer>>(questions, HttpStatus.OK);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
		List<QuestionWrapper> wrappers = new ArrayList<>();

		List<Question> questions = new ArrayList<>();
		for (Integer id : questionIds) {
			questions.add(questionDao.findById(id).get());
		}

		for (Question question : questions) {
			QuestionWrapper wrapper = new QuestionWrapper();
			wrapper.setId(question.getId());
			wrapper.setQuestionTitle(question.getQuestionTitle());
			wrapper.setOption1(question.getOption1());
			wrapper.setOption2(question.getOption2());
			wrapper.setOption3(question.getOption3());
			wrapper.setOption4(question.getOption4());
			wrappers.add(wrapper);
		}
		return new ResponseEntity<List<QuestionWrapper>>(wrappers, HttpStatus.OK);
	}

	public ResponseEntity<Integer> getScore(List<Response> responses) {
		int result = 0;
		for (Response response : responses) {
			Question question = questionDao.findById(response.getId()).get();
			if (response.getResponse().equals(question.getRightAnswer())) {
				result++;
			}
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
}