package com.lite.context.event;

import com.lite.context.ApplicationEvent;
import com.lite.context.ApplicationListener;

/**
 * @author vince 2024/2/23 17:26
 */
public interface ApplicationEventMulticaster {

    void addApplicationListener(ApplicationListener<?> applicationListener);

    void removeApplicationListener(ApplicationListener<?> applicationListener);

    void multicastEvent(ApplicationEvent event);

}
