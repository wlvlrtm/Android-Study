package com.example.e02views;

public class EmptyStringChecker {
    public static boolean isEmptyOrWhiteSpace(String str) {
        if (str == null) {
            return true;
        }
        return str.trim().length() == 0;
    }
}
