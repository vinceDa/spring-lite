package com.lite.beans.factory;

/**
 * @author vince 2024/1/20 20:25
 */
public class BeansException extends RuntimeException{

    public BeansException(String msg) {
        super(msg);
    }

    public BeansException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
