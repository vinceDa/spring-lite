package com.lite.context;

import com.lite.beans.BeansException;
import com.lite.beans.factory.Aware;

/**
 * 实现该接口，能感知所属 ApplicationContext
 * @author vince 2024/2/23 14:53
 */
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;

}
