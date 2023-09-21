package com.leafpage.controller.user;

import com.leafpage.controller.Controller;
import com.leafpage.dao.LeeLikeyDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LikeEmptyHeartController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 이 페이지에 들어가기 전 controller를 만들어 select를 먼저 수행
        // int 현재like여부 = LikeyDAO.select(userNo, isbn);
        //session에 "현재like여부"가 0인지 1인지 담아주고 페이지를 띄운다.
        //jsp파일에는 jstl <c:if></c:if>를 사용하여 fullheart/emptyheart를 띄움
        //heart를 클릭하면 LikeEmptyHeartController(여기)로 들어옴
        //if(session내의 현재like여부==1) {int delete변수 = LikeyDAO.delete(user_no, isbn)}
        //if(session내의 현재like여부==0) {int insert변수 = LikeyDAO.insert(user_no, isbn)}
        //delete와 insert 메서드는 수행이 성공하면 1을 반환한다. 실패하면 0을 반환한다.
        //위에서 반환되는 1또는 0이라는 값을 변수에 담아서
        //if(insert변수 == 1) {out.print(1);}
        //if(delete변수 == 1) {out.print(0);}
        //ajax에서 data를 1로 받는다면, fullheart
        //ajax에서 data를 0으로 받는다면, emptyheart

        System.out.println("좋아요 기능입니다");

        //1. 사용자 정보 입력 추출
        long userNo = Long.parseLong(request.getParameter("userNo").trim());
        String isbn = request.getParameter("isbn").trim();

        //2. DB 연동 처리
        LeeLikeyDAO likeyDAO = new LeeLikeyDAO();
        PrintWriter out = response.getWriter();

        int checkLike = likeyDAO.checkLike(userNo, isbn);
        if(checkLike == 1) {
            int deleteLike = likeyDAO.deleteLike(userNo,isbn);
            if(deleteLike == 1) {
                System.out.println("좋아요 삭제");
                out.print(0);
                out.close();
            }
        } else {
            int insertLike = likeyDAO.insertLike(userNo,isbn);
            if(insertLike == 1) {
                System.out.println("좋아요 추가");
                out.print(1);
                out.close();
            }
        }

        int heartCount = likeyDAO.likeCount(isbn);


        //3. 화면 이동

        HttpSession session = request.getSession();

        //request정보 담기
        session.setAttribute("heartClick", checkLike);
        session.setAttribute("heartCount", heartCount);

        return "detailPageView.do";
    }
}