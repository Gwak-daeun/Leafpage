package com.leafpage.dto;

import lombok.*;

@Getter
@Setter
public class ReviewDTO {
    private String reviewNo;
    private String ISBN;
    private String reviewDate;
    private String reviewContent;
    private int reviewRating;
}
