package com.tss.ProjektJakubStasiurka.helper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptHashGenerator {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "tomcat";
        System.out.println("BCrypt hash: " + encoder.encode(rawPassword));
    }
}
