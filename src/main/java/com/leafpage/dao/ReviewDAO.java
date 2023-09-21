package com.leafpage.dao;

import com.leafpage.dto.ReviewDTO;
import com.leafpage.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO {

    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;
    String SQL = "";

    public int makeReview(int userNum, String ISBN, String reviewDate, String reviewContent, int reviewRating) {

        SQL = "INSERT INTO reviews ( user_no, ISBN, review_date, review_content, review_rating ) \n" +
                "VALUES ( ?, ?, ?, ?, ?);";

        try {

            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, userNum);
            pstmt.setString(2, ISBN);
            pstmt.setString(3, reviewDate);
            pstmt.setString(4, reviewContent);
            pstmt.setInt(5, reviewRating);

            return pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(rs, pstmt, conn);
        }

        return -1;
    }

    public List<ReviewDTO> findReviews(String isbn) {

        List<ReviewDTO> bookReviews = new ArrayList<>();

        SQL = "select review_no, ISBN, review_date, review_content, review_rating\n" +
                "from reviews\n" +
                "where ISBN = ?\n" +
                "order by review_date desc;";

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, isbn);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ReviewDTO review = new ReviewDTO();
                review.setReviewNo(rs.getString("review_no"));
                review.setISBN(rs.getString("ISBN"));
                review.setReviewDate(rs.getString("review_date"));
                review.setReviewContent(rs.getString("review_content"));
                review.setReviewRating(rs.getInt("review_rating"));
                bookReviews.add(review);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(rs, pstmt, conn);
        }

        return bookReviews;
    }

    public int removeReview(int reviewNo) {

        SQL = "delete from reviews\n" +
                "where review_no = ?;";

        try {

            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, reviewNo);

            return pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();

        }finally {
            DBUtil.close(rs, pstmt, conn);
        }

        return -1;
    }

}
