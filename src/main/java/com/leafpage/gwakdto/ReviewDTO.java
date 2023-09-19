package com.leafpage.gwakdto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewDTO {
    private String review_no;
    private String ISBN;
    private String review_date;
    private String review_content;
    private int review_rating;
}
