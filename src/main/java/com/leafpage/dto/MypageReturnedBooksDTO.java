package com.leafpage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class MypageReturnedBooksDTO {
    String bookName;
    String bookAuthorName;
    String actualReturnDate;
}
