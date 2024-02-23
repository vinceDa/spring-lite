package com.lite.context.event;

/**
 * @author vince 2024/2/23 18:00
 */
public class ContextCloseEvent extends ApplicationContextEvent {

    public ContextCloseEvent(Object source) {
        super(source);
    }
}
