package com.example.shareourlife.MVVM.utils;

public class Preconditions {
    public static <T> void checkNotNull(T value, String name) {
        if (value == null) {
            throw new NullPointerException(name + " should not be null");
        }
    }
}
