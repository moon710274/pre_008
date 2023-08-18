package com.example.backendpre.question.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.lang.reflect.Member;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
public class Question {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long questionId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false, name = "LAST_MODIFIED_AT")
    private  LocalDateTime modifiedAt = LocalDateTime.now();

//    @OneToMany(mappedBy = "question", cascade = {CascadeType.ALL})
//    private List<Answer> answers = new ArrayList<>();   질문이 삭제시 답변도 삭제
//
//    @ManyToOne
//    @Column(name = "MEMBER_ID")
//    private Member member;
}
