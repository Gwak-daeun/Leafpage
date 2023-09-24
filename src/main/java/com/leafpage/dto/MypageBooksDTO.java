package com.leafpage.dto;

import lombok.*;

@Getter
@Setter
public class MypageBooksDTO {
    private String bookName;
    private String bookAuthorName;
    private String scheduledReturnDate;
    private String rentalDate;
    private String rentalNo;
    private String bookImg;
    private int scrollY;
    private int modalWidth;
    private String bookContent;
}
