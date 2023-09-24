package com.leafpage.domain.book;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class ISBN {
    private static final int ISBN_LENGTH = 13;
    private final String ISBN;

    public ISBN(String isbn) {
        validate(isbn);
        this.ISBN = isbn;
    }

    private void validate(String isbn) {
        if (isbn == null) {
            log.error("ISBN이 없습니다.");
            throw new IllegalArgumentException("ISBN이 없습니다.");
        }

        if (isInValidISBNLength(isbn)) {
            log.error("ISBN은 13자리 숫자여야 합니다.");
            throw new IllegalArgumentException("ISBN은 13자리 숫자여야 합니다.");
        }

        if (isNotNumberic(isbn)) {
            log.error("ISBN은 오직 숫자만 입력하실 수 있습니다.");
            throw new IllegalArgumentException("ISBN은 오직 숫자만 입력하실 수 있습니다.");
        }
    }

    private static boolean isNotNumberic(String isbn) {
        return !isbn.chars().allMatch(Character::isDigit);
    }

    private boolean isInValidISBNLength(String isbn) {
        return isbn.length() != ISBN_LENGTH;
    }
}
