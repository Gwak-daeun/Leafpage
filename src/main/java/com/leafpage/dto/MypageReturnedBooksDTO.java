package com.leafpage.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MypageReturnedBooksDTO {
    String book_name;
    String book_author_name;
    String actual_return_date;
}
