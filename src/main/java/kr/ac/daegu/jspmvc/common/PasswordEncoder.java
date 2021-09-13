package kr.ac.daegu.jspmvc.common;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncoder {
    public static String getEncodedPassword(String rawPassword, String salt) {
        String passwordSalt = rawPassword + salt;
        String encodedPassword = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(passwordSalt.getBytes(StandardCharsets.UTF_8));
            encodedPassword = String.format("%040x", new BigInteger(1, digest.digest()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encodedPassword;
    }
}