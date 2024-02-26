package com.lite.test.common.event;

import com.lite.context.ApplicationListener;
import com.lite.context.event.ContextCloseEvent;

/**
 * @author vince 2024/2/23 18:07
 */
public class ContextClosedEventListener implements ApplicationListener<ContextCloseEvent> {

    @Override
    public void onApplicationEvent(ContextCloseEvent event) {
        System.out.println(this.getClass().getName());
    }
}
