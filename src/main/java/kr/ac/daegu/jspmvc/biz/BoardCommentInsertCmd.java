package kr.ac.daegu.jspmvc.biz;

import kr.ac.daegu.jspmvc.model.BoardDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class BoardCommentInsertCmd implements BoardCmd {
    @Override
    public boolean execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 요청받은 key,value들
        int boardId = Integer.parseInt(request.getParameter("boardId"));
        String commentAuthor = request.getParameter("commentAuthor");
        String commentContent = request.getParameter("commentContent");
        // 새로운 댓글을 insert하기 위한 comment 테이블의 id값
        int newId;

        // log
        System.out.println("boardId=" + boardId);
        System.out.println("commentAuthor=" + commentAuthor);
        System.out.println("commentContent=" + commentContent);

        BoardDAO dao = new BoardDAO();
        try {
            //newId = dao.getCommentNewId();
            dao.insertComment( boardId, commentAuthor, commentContent);
            dao.updateCommentCount(boardId);

        } catch (ClassNotFoundException | SQLException e) {

            e.printStackTrace();
        }

        return true; // 의미없음
    }
}
