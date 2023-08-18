package com.example.backendpre.question.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class QuestionDto {
    @Setter
    @Getter
    @NoArgsConstructor
    public static class QuestionPostDto {
        @NotBlank(message = "여기에 제목을 입력하세요.")
        private String title;
        @NotBlank(message = "여기에 내용을 입력하세요.")
        private  String content;
        private  long memberId;
    }
    @NoArgsConstructor
    @Setter
    @Getter
    public static class QuestionPatchDto {
        @NotBlank(message = "여기에 제목을 입력하세요.")
        private String title;
        @NotBlank(message = "여기에 내용을 입력하세요.")
        private  String content;
        private  long memberId;
    }
//    @Setter
//    @Getter
//    @Builder
//    @AllArgsConstructor
//    public static class SearchResponse {
//        @Positive
//        private long questionId;
//        private String username;
//        private String title;
//        private String content;
//    }
}
