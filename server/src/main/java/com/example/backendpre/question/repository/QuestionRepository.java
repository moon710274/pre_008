package com.example.backendpre.question.repository;

import com.example.backendpre.question.controller.QuestionController;
import com.example.backendpre.question.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Question findByQuestionId(Long questionId);
}