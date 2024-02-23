package com.lite.context;

/**
 * 事件发布者接口
 * @author vince 2024/2/23 17:56
 */
public interface ApplicationEventPublisher {

    /**
     * 发布事件
     */
    void publishEvent(ApplicationEvent event);

}
