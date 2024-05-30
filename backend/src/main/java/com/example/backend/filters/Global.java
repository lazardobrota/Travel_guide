package com.example.backend.filters;

import org.apache.commons.codec.digest.DigestUtils;

import java.sql.Date;
import java.time.LocalDate;

public class Global {

    public static String hashPassword(String password) {
        return DigestUtils.sha256Hex(password);
    }

    public static Date localDateToDate(LocalDate localDate) {
        return Date.valueOf(localDate);
    }

    public static LocalDate dateToLocalDate(Date date) {
        return date.toLocalDate();
    }
}
