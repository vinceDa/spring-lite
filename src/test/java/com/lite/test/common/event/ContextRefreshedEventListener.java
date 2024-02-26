package com.lite.test.common.event;

import com.lite.context.ApplicationListener;
import com.lite.context.event.ContextRefreshEvent;

/**
 * @author vince 2024/2/23 18:07
 */
public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshEvent event) {
        System.out.println(this.getClass().getName());
    }
}
