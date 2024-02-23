package com.lite.test.beans.factory.event;

import com.lite.context.ApplicationContext;
import com.lite.context.event.ApplicationContextEvent;

/**
 * @author derekyi
 * @date 2020/12/5
 */
public class CustomEvent extends ApplicationContextEvent {

	public CustomEvent(ApplicationContext source) {
		super(source);
	}
}
