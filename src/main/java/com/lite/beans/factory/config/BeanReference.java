package com.lite.beans.factory.config;

/**
 * @author vince 2024/2/4 17:24
 */
public class BeanReference {

    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }

}
