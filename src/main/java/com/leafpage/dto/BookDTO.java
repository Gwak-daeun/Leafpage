package com.leafpage.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class BookDTO {
    private String ISBN;
    private String bookName;
    private String bookAuthorName;
    private String bookPublisherName;
    private String bookPubDate;
    private List<String> categories;
    private String bookInfo;
    private String bookChapter;
    private String bookContent;
    private String bookImg;
    private String bookImgFullPath;
    private String bookstate;
}
