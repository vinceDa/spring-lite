package com.lite.test.beans.factory;

import com.lite.context.support.ClassPathXmlApplicationContext;
import com.lite.test.beans.factory.event.CustomEvent;
import org.junit.jupiter.api.Test;

/**
 * @author vince 2024/2/23 18:09
 */
public class EventAndEventListenerTest {

    @Test
    public void testEventListener() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:event-and-event-listener.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext));

        applicationContext.registerShutdownHook();
    }

}

