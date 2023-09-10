package com.leafpage.leafpage.book;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookDTO {
    private int bookID;
    private String sentence;
}
