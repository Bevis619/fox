package com.bevis.fox.core.event;


import java.util.EventListener;

/**
 * 监听者接口
 *
 * @param <E> the type parameter
 * @author bevis
 * @version v1.0 : AbstractListener.class, 2018/3/30 15:13
 */
public interface FoxEventListener<E extends AbstractFoxEvent> extends EventListener {
    /**
     * On event.
     *
     * @param event the event
     */
    void onEvent(E event);

    /**
     * Gets event type.
     *
     * @return the event type
     */
    Class<E> getEventType();
}