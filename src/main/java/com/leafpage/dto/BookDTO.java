package com.leafpage.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class BookDTO {

    private String ISBN;
    private String bookname;
    private String auther;
    private String publisher;
    private String pubdate;
    private List<String> categories;
    private String bookinfo;
    private String bookchapter;
    private String bookcontent;

}
