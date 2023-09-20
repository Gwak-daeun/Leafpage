package com.leafpage.gwakdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDetailDTO {
    private String ISBN;
    private String book_name;
    private String book_author_name;
    private String book_img;
    private String book_info;
    private String book_publisher_name;
    private String book_pub_date;
    private String book_chapter;
    private String category_name;
}
