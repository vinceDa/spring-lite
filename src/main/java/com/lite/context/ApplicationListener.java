package com.lite.context;

import java.util.EventListener;

/**
 * @author vince 2024/2/23 17:30
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

    void onApplicationEvent(E event);

}
