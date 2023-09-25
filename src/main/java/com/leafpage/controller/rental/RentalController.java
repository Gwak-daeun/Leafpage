package com.leafpage.controller.rental;

import com.leafpage.controller.Controller;
import com.leafpage.dao.BookDAO;
import com.leafpage.dao.RentalDAO;
import com.leafpage.dto.RentalDTO;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


@Slf4j
public class RentalController implements Controller {
    private static final int MAX_RENTAL_COUNT = 5;

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        RentalDAO rentalDAO = RentalDAO.getInstance();

        String isbn = request.getParameter("ISBN");
        Long userNo = (Long) session.getAttribute("userNo");
        log.debug("userNo = {}", userNo);
        log.debug("ISBN = {}", isbn);

        RentalDTO dto = new RentalDTO();
        dto.setIsbn(isbn);
        dto.setUserNo(userNo);

        PrintWriter writer = response.getWriter();
        if (rentalDAO.findRentalCount(dto) >= MAX_RENTAL_COUNT) {
            writer.print("overRentCount");
            log.debug("회원의 도서 대여 수가 5권입니다. 더 이상 대여하실 수 없습니다.");
        } else if (rentalDAO.findRenting(dto)) {
            writer.print("renting");
            log.debug("현재 도서가 대여 중 입니다.");
        } else {
            log.debug("도서 대여 시작");
            rentalDAO.rentBook(dto);
        }
        writer.close();

        return "none";
    }
}
