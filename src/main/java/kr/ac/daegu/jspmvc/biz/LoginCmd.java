package kr.ac.daegu.jspmvc.biz;

import kr.ac.daegu.jspmvc.common.PasswordEncoder;
import kr.ac.daegu.jspmvc.model.MemberDAO;
import kr.ac.daegu.jspmvc.model.MemberDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class LoginCmd implements BoardCmd {
    @Override
    public boolean execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String password = request.getParameter("password");

        System.out.println("id = " + id);
        System.out.println("password = " + password);

        // DAO
        MemberDAO memDAO = new MemberDAO();
        // id 기준으로 로그인 정보를 가져옴.
        MemberDTO member = null;
        // 0915 오류 아이디가 있는지 없는지부터 확인해야함
        // 비밀번호가 오류나면 로그인 실패 페이지로 이동되지만 아이디를 틀리면 500error

        try {
            member = memDAO.getLoginData(id);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 비밀번호 매칭

        if(member.getPassword()==null){
            System.out.println("입력된 아이디와 일치하는 정보가 없습니다");
            return false;
        }
        return isPasswordMatch(password, member);
    }

    private boolean isPasswordMatch(String inputPassword, MemberDTO member) {
        String encodedPassword = PasswordEncoder.getEncodedPassword(inputPassword, member.getSalt());
        return member.getPassword().equals(encodedPassword);
    }
}