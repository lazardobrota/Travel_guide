package com.example.backend.filters;

import org.apache.commons.codec.digest.DigestUtils;

public class Pass {

    public static String hashPassword(String password) {
        return DigestUtils.sha256Hex(password);
    }
}
