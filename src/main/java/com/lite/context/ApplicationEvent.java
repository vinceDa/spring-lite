package com.lite.context;

import java.util.EventObject;

/**
 * @author vince 2024/2/23 17:28
 */
public class ApplicationEvent extends EventObject {

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
