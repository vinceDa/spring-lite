package com.lite.aop;

/**
 * 切点抽象
 * @author vince 2024/2/26 11:36
 */
public interface Pointcut {

    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();

}
