package com.lite.beans.factory;

import com.lite.beans.BeansException;

/**
 * @author vince 2024/2/8 17:22
 */
public interface InitializingBean {

    void afterPropertiesSet() throws BeansException;

}
