package com.lite.context;

import com.lite.beans.BeansException;

/**
 * @author vince 2024/2/6 16:48
 */
public interface ConfigurableApplicationContext extends ApplicationContext {

    /**
     * 刷新容器
     * @throws BeansException
     */
    void refresh() throws BeansException;

}
