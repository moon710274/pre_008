package com.example.backendpre.question.service;

import com.example.backendpre.question.entity.Question;
import com.example.backendpre.question.repository.QuestionRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.data.jpa.repository.JpaRepository;


import java.lang.reflect.Member;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    public Question updateQuestion(Question question) {
        return questionRepository.save(question);
    }

    public Question findQuestion(long questionId) {
        return questionRepository.findById(questionId)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException("Question not found"));
    }

    public Page<Question> findQuestions(int page, int size) {
        return questionRepository.findAll(PageRequest.of(page, size));
    }

    public void deleteQuestion(long questionId) {
        questionRepository.deleteById(questionId);
    }
}

