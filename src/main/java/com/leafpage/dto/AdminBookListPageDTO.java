package com.leafpage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
public class AdminBookListPageDTO {
    private int startPage; // 게시글 화면에 보여질 첫번째 번호
    private int endPage; // 게시글 화면에 보여질 마지막 번호
    private boolean prev, next; // 이전버튼, 다음버튼 활성화여부
    private int pageNum; // 현재 조회하는 페이지번호
    private int amount; // 화면에 그려질 데이터
    private int total; // 전체게시글 수
}
