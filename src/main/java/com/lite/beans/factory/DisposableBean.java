package com.lite.beans.factory;

import com.lite.beans.BeansException;

/**
 * @author vince 2024/2/8 17:49
 */
public interface DisposableBean {

    void destroy() throws Exception;

}
