package com.leafpage.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MypageBooksDTO {

    int all_rentals;
    String book_name;
    String book_author_name;
    String scheduled_return_date;
    String rental_date;
    String rental_no;
    int scroll_y;
    int modal_width;

}
