package com.lite.beans.factory;

/**
 * @author vince 2024/2/23 16:52
 */
public interface FactoryBean<T> {

    T getObject() throws Exception;

    boolean isSingleton();

}
