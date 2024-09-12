package com.boot.DTO;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeDTO {
    private String uuid;  // 중복 확인 후 가입 진행
    private String movieno; // 영화번호
    private String condition;
    private String numLike;
}