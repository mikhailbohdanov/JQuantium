package com.jquantium.util;

/**
 * Created by mikha on 01.02.2016.
 */
public abstract class Assert /*extends org.springframework.util.Assert*/ {

    public static boolean NULL(Object object) {
        return object == null;
    }

    public static boolean EMPTY(String string) {
        return NULL(string) || string.isEmpty();
    }

    public static boolean EQUALS(String stringA, String stringB) {
        return !NULL(stringA) && !NULL(stringB) && stringA.equals(stringB);
    }

}
