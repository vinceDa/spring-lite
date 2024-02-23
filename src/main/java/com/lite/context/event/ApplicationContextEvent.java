package com.lite.context.event;

import com.lite.context.ApplicationContext;
import com.lite.context.ApplicationEvent;

/**
 * @author vince 2024/2/23 17:59
 */
public abstract class ApplicationContextEvent extends ApplicationEvent {

    public ApplicationContextEvent(Object source) {
        super(source);
    }

    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }
}
