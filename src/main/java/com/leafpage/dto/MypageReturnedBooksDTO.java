package com.leafpage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MypageReturnedBooksDTO {
    String book_name;
    String book_author_name;
    String actual_return_date;
}
