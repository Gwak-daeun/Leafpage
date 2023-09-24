package com.leafpage.dto;

import lombok.*;

@Getter
@Setter
public class MypageReturnedBooksDTO {
    private String bookName;
    private String bookAuthorName;
    private String actualReturnDate;
    private String bookImg;
}
