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
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

@MultipartConfig(
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 50
)
public class AdminBookUploadController implements Controller  {

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

        int sizeLimit = 1024*1024*15;

        MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());

        if (multi.getFilesystemName("bookimg")== (null)){
            bookimg = "\\" + "iconmonstr-book-26-240";
        }else {
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


        String[] categorie = categories.split(",");
        for(int i = 0;  i < categorie.length; i++){
            categorie[i] = categorie[i].trim();
            if(categorie[i].equals("만화")|| categorie[i].equals("생활")||categorie[i].equals("소설")|| categorie[i].equals("에세이")||categorie[i].equals("학술논문")){
                categorieslist.add(categorie[i]);
            }else {
                response.setContentType("text/html; charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<script>");
                out.println("alert('카테고리는 만화,생활,소설,에세이,학술논문만 있습니다')");
                out.println("history.back()");
                out.println("</script>");
            }
        }



        System.out.println(bookimgFullPath);

        BookDTO dto = new BookDTO();

        dto.setISBN(ISBN);
        dto.setBookname(bookname);
        dto.setAuther(auther);
        dto.setPublisher(publisher);
        dto.setPubdate(pubdate);
        dto.setCategories(categorieslist);
        dto.setBookinfo(bookinfo);
        dto.setBookchapter(bookchapter);
        dto.setBookcontent(bookcontent);
        dto.setBookimg(bookimg);
        dto.setBookimgFullPath(bookimgFullPath);

        int count = dao.uploadBook(dto);
        System.out.println(count);

        if(count == 1){
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('등록성공')");
            out.println("</script>");
            return "booklistView.do";
        }else {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('등록실패')");
            out.println("history.back()");
            out.println("</script>");
        }


       return null;
    }

    private String getFilename(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] split = contentDisp.split(";");
        for (int i = 0; i < split.length; i++) {
            String temp = split[i];
            if (temp.trim().startsWith("filename")) {
                return temp.substring(temp.indexOf("=") + 2, temp.length() - 1);
            }
        }
        return "";
    }

}
