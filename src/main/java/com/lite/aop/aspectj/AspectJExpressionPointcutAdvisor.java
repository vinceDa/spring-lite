package com.lite.aop.aspectj;

import com.lite.aop.Pointcut;
import com.lite.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;

/**
 * @author vince 2024/2/27 09:49
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    private AspectjExpressionPointcut pointcut;

    private Advice advice;

    private String expression;

    public void setExpression(String expression) {
        this.expression = expression;
        pointcut = new AspectjExpressionPointcut(expression);
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }
}
