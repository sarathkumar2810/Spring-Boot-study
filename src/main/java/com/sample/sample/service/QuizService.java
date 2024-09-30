package com.sample.sample.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sample.sample.Question;
import com.sample.sample.QuestionWrapper;
import com.sample.sample.Quiz;
import com.sample.sample.Response;
import com.sample.sample.dao.QuestionDao;
import com.sample.sample.dao.QuizDao;

@Service
public class QuizService {
	
	@Autowired
	QuizDao quizDao;
	@Autowired
	QuestionDao questionDao;

	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		try {
			List<Question> question = questionDao.findRandomQuestionByCategory(category, numQ);
			
			Quiz quiz = new Quiz();
			quiz.setTitle(title);
			quiz.setQuestion(question);
			quizDao.save(quiz);
			
			return new ResponseEntity<>("Success", HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("Failure", HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
		try {
			Optional<Quiz> quiz = quizDao.findById(id);
			List<Question> questionFromDB = quiz.get().getQuestion();
			List<QuestionWrapper> questionForUser = new ArrayList<>();
			for (Question q : questionFromDB) {
				QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
				questionForUser.add(qw);
			}
			return new ResponseEntity<>(questionForUser, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
		int right = 0;
		try {
			Optional<Quiz> quiz = quizDao.findById(id);
			List<Question> questions= quiz.get().getQuestion();
			int i = 0;
			for (Response response : responses) {
				if(response.getResponse().equals(questions.get(i).getRightAnswer())) {	
					right++;
				}			
				i++;
			}	
			return new ResponseEntity<>(right, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(right, HttpStatus.BAD_REQUEST);
	}

}
