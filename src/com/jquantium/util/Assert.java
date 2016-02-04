package com.jquantium.util;

/**
 * Created by mikha on 01.02.2016.
 */
public abstract class Assert /*extends org.springframework.util.Assert*/ {

    public static boolean Null(Object object) {
        return object == null;
    }




    public static boolean isNull(String string) {
        return isEmpty(string) || "null".equals(string);
    }

    public static boolean isEmpty(String string) {
        return isNull(string) || string.isEmpty();
    }

}
