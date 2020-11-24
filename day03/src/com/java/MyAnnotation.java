package com.java;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;

/**
 * 自定义Annotation
 */
@Retention(RetentionPolicy.CLASS)
@Target({FIELD,TYPE,METHOD})
public @interface MyAnnotation {
    String value() default "hello world";
}

