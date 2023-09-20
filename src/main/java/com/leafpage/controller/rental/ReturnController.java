package com.leafpage.controller.rental;

import com.leafpage.controller.Controller;
import com.leafpage.dao.RentalDAO;
import com.leafpage.dto.RentalDTO;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class ReturnController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        RentalDAO rentalDAO = new RentalDAO();

        Long userNo = (Long) session.getAttribute("user_no");
        String isbn = request.getParameter("isbn");

        RentalDTO dto = new RentalDTO();
        dto.setIsbn(isbn);
        dto.setUserNo(userNo);

        if (rentalDAO.isBookReturnable(dto)) {
            rentalDAO.returnBook(dto);
        } else {
            request.setAttribute("alreadyReturned", true);
            log.debug("이미 반납된 도서입니다.");
        }
        return null;
    }
}
