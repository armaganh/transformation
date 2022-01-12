package com.huso.transformation.util;

import com.huso.transformation.exception.LetterValidationException;

public class Util {
    private final static String letterPattern = "[a-zA-Z]+";

    public static void validateLettern(String transform) {
        if(!transform.matches(letterPattern)) {
            throw new LetterValidationException();
        }
    }
}
