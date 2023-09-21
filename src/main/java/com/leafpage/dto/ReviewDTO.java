package com.leafpage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class ReviewDTO {
    private String reviewNo;
    private String ISBN;
    private String reviewDate;
    private String reviewContent;
    private int reviewRating;
}
