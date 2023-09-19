package com.leafpage.gwakdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private String ISBN;
    private String book_name;
    private String book_author_name;
    private String book_img;
    private String book_info;
    private String book_publisher_name;
    private String book_pub_date;
    private String book_upload_date;
    private String book_content;
    private String book_chapter;
    private int book_views;
    private String book_state;


    public BookDTO(String  string1, int anInt1, String string2, String string3) {
    }
}
