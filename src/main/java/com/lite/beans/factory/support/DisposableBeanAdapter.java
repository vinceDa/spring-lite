package com.lite.beans.factory.support;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import com.lite.beans.BeansException;
import com.lite.beans.factory.DisposableBean;

import java.lang.reflect.Method;

/**
 * @author vince 2024/2/8 17:59
 */
public class DisposableBeanAdapter implements DisposableBean {

    private final Object bean;

    private final String beanName;

    private final String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, String destroyMethodName) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = destroyMethodName;
    }

    @Override
    public void destroy() throws Exception {
        if (bean instanceof DisposableBean) {
            ((DisposableBean) bean).destroy();
        }

        // 避免同时继承自 DisposableBean 且自定义方法与 DisposableBean 方法同名导致销毁方法执行两次的情况
        if (StrUtil.isNotEmpty(beanName) && !(bean instanceof DisposableBean && "destroy".equals(destroyMethodName))) {
            // 执行自定义方法
            Method destroyMethod = ClassUtil.getPublicMethod(bean.getClass(), destroyMethodName);
            if (destroyMethod == null) {
                throw new BeansException("Couldn't find a destroy method named '" + destroyMethodName + "' on bean with name '" + beanName + "'");
            }
            destroyMethod.invoke(bean);
        }
    }
}
