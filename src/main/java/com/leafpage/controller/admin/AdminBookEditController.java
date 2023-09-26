package com.leafpage.controller.admin;

import com.leafpage.controller.Controller;
import com.leafpage.dao.BookDAO;
import com.leafpage.dto.BookDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class AdminBookEditController implements Controller {

    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("나여기");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        BookDAO dao = BookDAO.getInstance();

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


        String savePath = "/usr/local/tomcat/webapps/ROOT/image";
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

        List<String> categorieslist = new ArrayList<>();

        categories = categories.replace("[", "").replace("]", "");
        String[] categorie = categories.split(",");
        for (int i = 0; i < categorie.length; i++) {
            categorie[i] = categorie[i].trim();
            if (categorie[i].equals("만화") || categorie[i].equals("생활") || categorie[i].equals("소설") || categorie[i].equals("에세이") || categorie[i].equals("학술논문")) {
                categorieslist.add(categorie[i]);
            } else {

                out.println("<script>");
                out.println("alert('카테고리는 만화,생활,소설,에세이,학술논문만 있습니다')");
                out.println("history.back()");
                out.println("</script>");
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

        int count = dao.updateBook(dto);
        System.out.println(count);

        if (count == 1) {

            out.println("<script>");
            out.println("alert('수정성공')");
            out.println("</script>");
            return "booklistView.do";
        } else {

            out.println("<script>");
            out.println("alert('수정실패')");
            out.println("history.back()");
            out.println("</script>");
        }

        return null;
    }

}