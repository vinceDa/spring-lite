package com.lite.stereotype;

import java.lang.annotation.*;

/**
 * @author vince 2024/2/28 10:39
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {

    String value() default "";

}
