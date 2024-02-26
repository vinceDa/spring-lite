package com.lite.aop.framework;

import com.lite.aop.AdvisedSupport;

/**
 * 代理工厂
 * @author vince 2024/2/26 17:54
 */
public class ProxyFactory {

    private AdvisedSupport advisedSupport;

    public ProxyFactory(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    public Object getProxy() {
        return createProxy().getProxy();
    }

    private AopProxy createProxy() {
        if (advisedSupport.isProxyTargetClass()) {
            return new CglibAopProxy(advisedSupport);
        }

        return new JdkDynamicAopProxy(advisedSupport);
    }
}
