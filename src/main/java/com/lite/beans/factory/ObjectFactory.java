package com.lite.beans.factory;

import com.lite.beans.BeansException;

public interface ObjectFactory<T> {

    T getObject() throws BeansException;

}
