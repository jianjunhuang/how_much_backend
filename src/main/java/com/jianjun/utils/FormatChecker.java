package com.jianjun.utils;

import java.util.regex.Pattern;

public class FormatChecker {

    public static boolean email(String email) {
        String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        return regex.matcher(email).matches();
    }
}
