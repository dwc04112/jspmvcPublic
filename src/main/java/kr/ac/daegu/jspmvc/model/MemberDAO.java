package kr.ac.daegu.jspmvc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDAO {
    public MemberDTO getLoginData(String id) throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        pstmt = conn.prepareStatement("select * from member where id = ?");
        pstmt.setString(1, id);
        rs = pstmt.executeQuery();

        MemberDTO memberDTO = new MemberDTO();
        if(rs.next()){
            memberDTO.setId(rs.getString("id"));
            memberDTO.setPassword(rs.getString("password"));
            memberDTO.setSalt(rs.getString("salt"));
        }

        return memberDTO;
    }

    public void postLoginData(int newId,
                              String id,
                              String encodedPassword,
                              String salt) throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = null;

        pstmt = conn.prepareStatement("insert into member values (?, ?, ?, ?)");
        pstmt.setInt(1, newId);
        pstmt.setString(2, id);
        pstmt.setString(3, encodedPassword);
        pstmt.setString(4, salt);
        pstmt.executeUpdate();
    }

    public int existCount(String id) throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        pstmt = conn.prepareStatement("select count(*) from member where id = ?");
        pstmt.setString(1, id);
        rs = pstmt.executeQuery();

        int count = 0;
        if(rs.next()){
            count = rs.getInt(1);
        }
        return count;
    }

    public int getNewId() throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        pstmt = conn.prepareStatement("select max(mId) as maxId from member");
        rs = pstmt.executeQuery();

        if(rs.next()){
            int maxId = rs.getInt("maxId");
            return maxId + 1;
        }
        return 0;
    }
}