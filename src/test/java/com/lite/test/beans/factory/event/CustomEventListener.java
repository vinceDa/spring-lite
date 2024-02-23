package com.lite.test.beans.factory.event;

import com.lite.context.ApplicationEvent;
import com.lite.context.ApplicationListener;

/**
 * @author vince 2024/2/23 18:06
 */
public class CustomEventListener implements ApplicationListener<ApplicationEvent> {


    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println(this.getClass().getName());
    }
}
