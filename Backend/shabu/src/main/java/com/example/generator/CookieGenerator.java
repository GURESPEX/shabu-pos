package com.example.generator;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class CookieGenerator {

    public static CookieGenerator generator = new CookieGenerator();

    public String generate() {
        String chrs = "0123456789abcdefghijklmnopqrstuvwxyz-_ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        SecureRandom secureRandom;
        try {
            secureRandom = SecureRandom.getInstanceStrong();
            String customTag = secureRandom
                    .ints(32, 0, chrs.length()) // 9 is the length of the string you want
                    .mapToObj(i -> chrs.charAt(i))
                    .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                    .toString();
            return customTag;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

}
