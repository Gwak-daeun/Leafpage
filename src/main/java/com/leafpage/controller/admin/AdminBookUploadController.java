package com.leafpage.controller.admin;

import com.leafpage.controller.Controller;
import com.leafpage.dao.BookDAO;
import com.leafpage.dto.BookDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@MultipartConfig(
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 50
)
public class AdminBookUploadController implements Controller {

    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("들어오니?");


        BookDAO dao = new BookDAO();

        request.setCharacterEncoding("UTF-8");
        String ISBN = null;
        String bookname = null;
        String auther = null;
        String publisher = null;
        String pubdate = null;
        String categories = null;
        String bookinfo = null;
        String bookchapter = null;
        String bookcontent = null;
        String bookimg = null;


        String savePath = "C:\\Users\\user\\Desktop\\beanstalk\\Leafpage\\src\\main\\webapp\\image";
        String dbPath = "\\image";

        int sizeLimit = 1024 * 1024 * 15;

        MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());

        if (multi.getFilesystemName("bookimg") == (null)) {
            bookimg = "iconmonstr-book-26-240.png";
        } else {
            bookimg = multi.getFilesystemName("bookimg");
        }


        String bookimgFullPath = dbPath + "\\" + bookimg;


        if (multi.getParameter("ISBN") != null) {
            ISBN = multi.getParameter("ISBN");
        }
        if (multi.getParameter("bookname") != null) {
            bookname = multi.getParameter("bookname");
        }
        if (multi.getParameter("auther") != null) {
            auther = multi.getParameter("auther");
        }
        if (multi.getParameter("publisher") != null) {
            publisher = multi.getParameter("publisher");
        }
        if (multi.getParameter("pubdate") != null) {
            pubdate = multi.getParameter("pubdate");
        }
        if (multi.getParameter("categories") != null) {
            categories = multi.getParameter("categories");
        }
        if (multi.getParameter("bookinfo") != null) {
            bookinfo = multi.getParameter("bookinfo");
        }
        if (multi.getParameter("bookchapter") != null) {
            bookchapter = multi.getParameter("bookchapter");
        }
        if (multi.getParameter("bookcontent") != null) {
            bookcontent = multi.getParameter("bookcontent");
        }

       /* System.out.println(ISBN);
        System.out.println(bookname);
        System.out.println(auther);
        System.out.println(publisher);
        System.out.println(pubdate);
        System.out.println(categories);
        System.out.println(bookinfo);
        System.out.println(bookchapter);
        System.out.println(bookcontent);
        System.out.println(bookimgFullPath);*/
        List<String> categorieslist = new ArrayList<>();

        PrintWriter out = response.getWriter();
        response.setContentType("text/html; charset=UTF-8");

        if(ISBN.length() != 13){
            out.println("<script>");
            out.println("alert('ISBN은 13자리입니다')");
            out.println("history.back()");
            out.println("</script>");
            return null;
        } else if (dao.duplicationISBN(ISBN)) {
            out.println("<script>");
            out.println("alert('존재하는 ISBN입니다')");
            out.println("history.back()");
            out.println("</script>");

        }

        String[] categorie = categories.split(",");
        for (int i = 0; i < categorie.length; i++) {
            categorie[i] = categorie[i].trim();
            if (categorie[i].equals("만화") || categorie[i].equals("생활") || categorie[i].equals("소설") || categorie[i].equals("에세이") || categorie[i].equals("학술논문")) {
                categorieslist.add(categorie[i]);
            }else {
                out.println("<script>");
                out.println("alert('카테고리는 만화,생활,소설,에세이,학술논문만 있습니다')");
                out.println("history.back()");
                out.println("</script>");
                return null;
            }
        }


        System.out.println(bookimgFullPath);

        BookDTO dto = new BookDTO();

        dto.setISBN(ISBN);
        dto.setBookName(bookname);
        dto.setBookAuthorName(auther);
        dto.setBookPublisherName(publisher);
        dto.setBookPubDate(pubdate);
        dto.setCategories(categorieslist);
        dto.setBookInfo(bookinfo);
        dto.setBookChapter(bookchapter);
        dto.setBookContent(bookcontent);
        dto.setBookImg(bookimg);
        dto.setBookImgFullPath(bookimgFullPath);

        int count = dao.uploadBook(dto);
        System.out.println(count);

        if(count == 1){
            out.println("<script>");
            out.println("alert('등록성공')");
            out.println("</script>");
            return "booklistView.do";
        }else {
            out.println("<script>");
            out.println("alert('등록실패')");
            out.println("history.back()");
            out.println("</script>");
            return null;
        }
    }

}
