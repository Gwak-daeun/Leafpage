package com.leafpage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MypageBooksDTO {

    int all_rentals;
    String book_name;
    String author_name;
    String scheduled_return_date;
    String rental_date;
    String rental_no;
    int mobileY;
    int pcY;
    int modalWidth;
}
