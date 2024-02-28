package com.lite.context.annotation;

import java.lang.annotation.*;

/**
 * bean 的作用域
 *
 * @author vince 2024/2/28 10:42
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Scope {

    String value() default "singleton";

}
