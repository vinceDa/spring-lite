package com.lite.aop;

/**
 * @author vince 2024/2/27 09:47
 */
public interface PointcutAdvisor extends Advisor {

    Pointcut getPointcut();

}
