package com.example.backendpre.question.controller;

import com.example.backendpre.question.dto.QuestionDto;
import com.example.backendpre.question.dto.QuestionResponseDto;
import com.example.backendpre.question.entity.Question;
import com.example.backendpre.question.mapper.QuestionMapper;
import com.example.backendpre.question.service.QuestionService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/questions")
@Validated
public class QuestionController {
    private final static String QUESTION_DEFAULT_URL = "/questions";
    private final QuestionService questionService;
    private final QuestionMapper mapper;

    public QuestionController(QuestionService questionService, QuestionMapper questionMapper) {
        this.questionService = questionService;
        this.mapper = questionMapper;
    }
    @PostMapping
    public ResponseEntity postQuestion(@Valid @RequestBody QuestionDto.QuestionPostDto questionPostDto) {
        Question question = questionService.createQuestion(mapper.questionPostDtoToQuestion(questionPostDto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{question-id}")
                .buildAndExpand(question.getQuestionId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

//    @PostMapping
//    public ResponseEntity postQuestion(@Valid @RequestBody QuestionDto.QuestionPostDto questionPostDto) {
//        Question question = questionService.createQuestion(mapper.questionPostDtoToQuestion(questionPostDto));
//        URI location = UriCreator.createUri(QUESTION_DEFAULT_URL, question.getQuestionId());
//
//        return ResponseEntity.created(location).build();
//    }
    @PatchMapping("/{question-id}")
    public ResponseEntity<QuestionResponseDto> patchQuestion(@PathVariable("question-id") @Positive long questionId,
                                                         @Valid @RequestBody QuestionDto.QuestionPatchDto questionPatchDto) {
    QuestionDto.QuestionPatchDto newQuestionPatchDto = new QuestionDto.QuestionPatchDto();
    newQuestionPatchDto.setId(questionId);
    newQuestionPatchDto.setContent(questionPatchDto.getContent());

    Question question = questionService.updateQuestion(mapper.questionPatchDtoToQuestion(newQuestionPatchDto));
    QuestionResponseDto questionResponseDto = mapper.questionToQuestionResponseDto(question);
    return new ResponseEntity<>(questionResponseDto, HttpStatus.OK);
}

    @PatchMapping("/{question-id}")
    public ResponseEntity patchQuestion(@PathVariable("question-id") @Positive long questionId,
                                        @Valid @RequestBody QuestionDto.QuestionPatchDto questionPatchDto) {
        questionPatchDto.setContent(questionId);
        Question question = questionService.updateQuestion(mapper.questionPatchDtoToQuestion(questionPatchDto));

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.questionToQuestionResponseDto(question)),
                HttpStatus.OK);
    }

    @GetMapping("/{question-id}")
    public ResponseEntity getQuestion(@PathVariable("question-id") long questionId) {
        Question question = questionService.findQuestion(questionId);

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.questionToQuestionResponseDto(question)),
                HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<QuestionResponseDto>> getQuestions(@Positive @RequestParam("page") int page,
                                                                  @Positive @RequestParam("size") int size) {
        Page<Question> pageQuestions = questionService.findQuestions(page - 1, size);
        List<Question> questions = pageQuestions.getContent();
        List<QuestionResponseDto> questionResponseDtos = mapper.questionsToQuestionResponseDtos(questions);

        return new ResponseEntity<>(questionResponseDtos, HttpStatus.OK);
    }

//    @GetMapping
//    public ResponseEntity getQuestions(@Positive @RequestParam("page") int page,
//                                       @Positive @RequestParam("size") int size) {
//        Page<Question> pageQuestions = questionService.findQuestions(page - 1, size);
//        List<Question> questions = pageQuestions.getContent();
//
//        return new ResponseEntity<>(
//                new MultiResponseDto<>(mapper.questionsToQuestionResponseDtos(questions),
//                        pageQuestions),
//                HttpStatus.OK);
//    }
    @DeleteMapping("/{question-id}")
    public ResponseEntity deleteQuestion(@PathVariable("question-id") long questionId) {
        questionService.deleteQuestion(questionId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}