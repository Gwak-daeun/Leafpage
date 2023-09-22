package com.leafpage.controller.rental;

import com.leafpage.controller.Controller;
import com.leafpage.dao.RentalDAO;
import com.leafpage.dto.RentalDTO;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.BlockingDeque;

@Slf4j
public class ReturnController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        RentalDAO rentalDAO = new RentalDAO();

        Long userNo = (Long) session.getAttribute("userNo");
        Long rentalNo = Long.valueOf(request.getParameter("rentalNo"));
        log.debug("userNo = {}", userNo);
        log.debug("rentalNo = {}", rentalNo);

        RentalDTO dto = new RentalDTO();
        dto.setUserNo(userNo);
        dto.setRentalNo(rentalNo);

        PrintWriter writer = response.getWriter();

        if (rentalDAO.isBookReturnable(dto)) {
            log.debug("도서 반납 시작");
            rentalDAO.returnBook(dto);
        } else {
            writer.print("ReturnFail");
            log.debug("반납에 실패했습니다.");
        }
        writer.close();
        return "none";
    }
}
