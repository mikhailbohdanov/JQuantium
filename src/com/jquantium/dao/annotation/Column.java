package com.jquantium.dao.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Mykhailo_Bohdanov on 01/07/2015.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Column {

    String name() default "";

    boolean primary() default false;
    String unique() default "";

    int length() default 256;
    boolean unsigned() default false;
    boolean autoIncrement() default false;

    boolean notNull() default false;
    String defaultValue() default "";

}
