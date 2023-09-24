package com.leafpage.util;

import com.leafpage.dto.AdminBookListPageDTO;

public class ListPageUtil {

    public void listpage(AdminBookListPageDTO pageDTO){
        // 1. endPage결정
//		 ex) 조회하는 페이지 1 -> 끝번호 10
//		 ex) 조회하는 페이지 9 -> 끝번호 10
//		 ex) 조회하는 페이지 11 -> 끝번호 20
//		 공식 = (int)Math.ceil(페이지번호 / 페이지네이션개수) * 페이지네이션개수
         pageDTO.setEndPage((int) Math.ceil(pageDTO.getPageNum() * 0.1) * 10);

        // 2. startPage결정
        // 공식 = 끝페이지 - 페이지네이션개수 + 1
        pageDTO.setStartPage(pageDTO.getEndPage() - 10 + 1);

        // 3. realEnd(진짜 끝번호) 구해서 endPage의 값을 다시 결정
//		 만약 게시글이 52개라면 -> 진짜 끝번호 6
//		 만약 게시글이 105개라면 -> 진짜 끝번호 11
//		 공식 = (int)Math.ceil(전체게시글수 / 화면에보여질데이터개수)
        int realEnd = (int) Math.ceil(pageDTO.getTotal() / (double) pageDTO.getAmount());

//		 마지막페이지 도달했을 때 보여져야 하는 끝번호가 달라집니다.
//		 ex) 131개 게시물
//		 1번 페이지 클릭시 -> endPage = 10, realEnd = 14 ( endPage로 세팅 )
//		 11번 페이지 클릭시 -> endPage = 20, realEnd = 14 ( realEnd로 세팅 )
        if (pageDTO.getEndPage() > realEnd) {
            pageDTO.setEndPage(realEnd);
        }

        // 4. prev결정 ( startPage의 번호는 1, 11, 21... )
        pageDTO.setPrev(pageDTO.getStartPage() > 1);

        // 5. next결정
//		 ex: 131개 게시물
//		 1~10 클릭시 endPage = 10, realEnd = 14 -> 다음버튼 true
//		 11 클릭시 endPage = 14 , realEnd = 14 -> 다음버튼 false
        pageDTO.setNext(pageDTO.getEndPage() < realEnd);


        // GetListService에서 페이지VO 계산처리 코드작성.

    }
}
