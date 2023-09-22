package com.leafpage.dto;

import com.leafpage.domain.book.ISBN;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class RentalDTO {
    Long rentalNo;
    Long userNo;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    ISBN isbn;
    LocalDate rentalDate;
    LocalDate scheduledReturnDate;
    LocalDate actualReturnDate;

    public String getIsbn() {
        return isbn.getISBN();
    }

    public void setIsbn(String isbn) {
        this.isbn = new ISBN(isbn);
    }
}
