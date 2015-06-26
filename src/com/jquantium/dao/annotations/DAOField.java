package com.jquantium.dao.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Mykhailo_Bohdanov on 26/06/2015.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DAOField {

    String name() default "";

    int length() default 10;
    boolean unsigned() default false;
    boolean autoIncrement() default false;

    boolean notNull() default false;
    String defaultValue() default "";

    boolean primary() default false;
    String unique() default "";

}
