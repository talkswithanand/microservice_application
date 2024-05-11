package com.microservice.quizservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class QuizDto {
	String category;
	int numQues;
	String title;
}
