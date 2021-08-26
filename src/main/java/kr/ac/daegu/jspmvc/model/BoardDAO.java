package kr.ac.daegu.jspmvc.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class BoardDAO {
    private static final String DB_URL  = "jdbc:mariadb://localhost:3306/dgd";
    private static final String DB_USER = "root";
    private static final String DB_PW   = "0000";

    public static boolean getConnection() throws SQLException, ClassNotFoundException {
        // Connection, PreparedStatement, ResultSet은 interface 객체이다.
        Class.forName("org.mariadb.jdbc.Driver");
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        return true;
    }

    public ArrayList<BoardDTO> getBoardList() throws ClassNotFoundException, SQLException {
        // Connection, PreparedStatement, ResultSet은 interface 객체이다.
        Class.forName("org.mariadb.jdbc.Driver");
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // 쿼리 준비 & db 쿼리
        pstmt = conn.prepareStatement("select * from Board");
        rs = pstmt.executeQuery();

        // 글 목록을 반환할 ArrayList
        ArrayList<BoardDTO> boardRowList = new ArrayList<BoardDTO>();

        // db에서 데이터를 row단위로 가져와서
        // list에 넣는다.
        while(rs.next()) {
            int id = rs.getInt("id");
            String author = rs.getString("author");
            String subject = rs.getString("subject");
            String content = rs.getString("content");
            Date writeDate = rs.getDate("writeDate");
            Time writeTime = rs.getTime("writeTime");
            int readCount = rs.getInt("readCount");
            int commentCount = rs.getInt("commentCount");

            BoardDTO dto = new BoardDTO();
            dto.setId(id);
            dto.setAuthor(author);
            dto.setSubject(subject);
            dto.setContent(content);
            dto.setWriteDate(writeDate);
            dto.setWriteTime(writeTime);
            dto.setReadCount(readCount);
            dto.setCommentCount(commentCount);

            boardRowList.add(dto);
        }

        // db로부터 데이터 잘 들어왔는지 확인 (log 찍어봄)
        for(BoardDTO dto: boardRowList){
            System.out.println(dto.toString());
        }

        return boardRowList;
    }

    public int getBoardNewId() throws ClassNotFoundException, SQLException {
        // Connection, PreparedStatement, ResultSet은 interface 객체이다.
        Class.forName("org.mariadb.jdbc.Driver");
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // newId를 가져오는 쿼리
        pstmt = conn.prepareStatement("select max(id) + 1 AS newId from Board");
        rs = pstmt.executeQuery();

        int newId = 0;
        if(rs.next()){
            newId = rs.getInt("newId");
            return newId;
        }

        // 예외 발생
        throw new SQLException("글 컨텐츠를 새로 입력하기 위한 아이디값 받아오기를 실패하였습니다.");
    }

    public void insertBoardContent(int newId,
                                   String subject,
                                   String author,
                                   String content,
                                   String password) throws ClassNotFoundException, SQLException {
        // Connection, PreparedStatement, ResultSet은 interface 객체이다.
        Class.forName("org.mariadb.jdbc.Driver");
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
        PreparedStatement pstmt = null;

        // 쿼리 준비 & db 쿼리
        // insert into board values (1, 'testAuthor', 'testSubject', 'testContent', CURDATE(), CURTIME(), 0, 0)
        pstmt = conn.prepareStatement("insert into Board values (?, ?, ?, ?, CURDATE(), CURTIME(), 0, 0, ?)");
        pstmt.setInt(1, newId);
        pstmt.setString(2, subject);
        pstmt.setString(3, author);
        pstmt.setString(4, content);
        pstmt.setString(5, password);
        pstmt.executeUpdate();

    }

    public BoardDTO getBoardData(int id) throws ClassNotFoundException, SQLException {
        // db에 접속해서
        Class.forName("org.mariadb.jdbc.Driver");
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        // 쿼리 실행시키고
        pstmt = conn.prepareStatement("select * from Board where id = ?");
        pstmt.setInt(1, id);
        rs = pstmt.executeQuery();
        // 반환 데이터를 리턴.
        BoardDTO data = new BoardDTO();
        if(rs.next()){
//            int id = rs.getInt("id");
            String author = rs.getString("author");
            String subject = rs.getString("subject");
            String content = rs.getString("content");
            Date writeDate = rs.getDate("writeDate");
            Time writeTime = rs.getTime("writeTime");
            int readCount = rs.getInt("readCount");
            int commentCount = rs.getInt("commentCount");
            String password = rs.getString("password");

            data.setId(id);
            data.setAuthor(author);
            data.setSubject(subject);
            data.setContent(content);
            data.setWriteDate(writeDate);
            data.setWriteTime(writeTime);
            data.setReadCount(readCount);
            data.setCommentCount(commentCount);
            data.setPassword(password);

        }
        return data;
    }

    public void boardRowPlusReadCount(int rowId, int howMuch) throws ClassNotFoundException, SQLException {
        // db에 접속해서
        Class.forName("org.mariadb.jdbc.Driver");
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
        PreparedStatement pstmt = null;

        // 해당 아이디의 row에서 readCount를 +1 해주는 쿼리 실행
        pstmt = conn.prepareStatement("update Board set readCount=readCount + ? where id = ?");
        pstmt.setInt(1, howMuch);
        pstmt.setInt(2, rowId);
        pstmt.executeUpdate();
    }

    public void updateBoardContent(int id,
                                   String subject,
                                   String content) throws ClassNotFoundException, SQLException {
        // db에 접속해서
        Class.forName("org.mariadb.jdbc.Driver");
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
        PreparedStatement pstmt = null;
        // 해당 아이디의 row에서 subject와 content를 업데이트
        pstmt = conn.prepareStatement("update Board set subject=?, content=? where id = ?");
        pstmt.setString(1, subject);
        pstmt.setString(2, content);
        pstmt.setInt(3, id);
        pstmt.executeUpdate();

    }
}
