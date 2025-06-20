package com.example.tour_backend.dto.comment;

import lombok.Data;

import java.time.LocalDateTime;

@Data
/* Lombok 어노테이션-> 자동으로 다음 메서드를 만들어 줍니다: getter,setter toString()equals(), hashCode() */

public class CommentDto {
    private Long commentId; //Long은 정수 타입 중에서도 매우 큰 숫자를 담을 수 있는 자료형
    private Long threadId;
    private String comment;
    private String author;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;


}