/*
 * Canadian Tire Corporation, Ltd. Do not reproduce without permission in writing. Copyright (c) 2015 Canadian Tire
 * Corporation, Ltd. All rights reserved.
 */
package com.jquantium.util.auto.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface StatusProcessing {
    boolean returnHeaders() default false;
    boolean wrapExceptions() default true;
}
