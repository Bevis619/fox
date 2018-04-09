package com.bevis.fox.core.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 抽象事件发布器
 *
 * @author yhd35305
 * @version v1.0 : AbstractEventPublisher.class, 2018/3/30 15:40
 */
public abstract class AbstractEventPublisher implements EventPublisher {

    /**
     * The Lock.
     */
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractEventPublisher.class);

    /**
     * The Event listener map.
     */
    private final Map<Class<AbstractFoxEvent>, Set<FoxEventListener<AbstractFoxEvent>>> eventListenerMap = new ConcurrentHashMap<>();

    /**
     * 注册监听者
     *
     * @param listener the listener
     */
    @Override
    public void register(FoxEventListener<AbstractFoxEvent> listener) {
        if (null == listener) {
            return;
        }

        Class<AbstractFoxEvent> eventType = listener.getEventType();
        if (null == eventType) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("event type is null,can't register,please check getEventType() method");
            }

            return;
        }

        Set<FoxEventListener<AbstractFoxEvent>> listeners = this.eventListenerMap.get(eventType);
        try {
            this.lock.writeLock().lock();
            if (null != listeners) {
                listeners.add(listener);
            } else {
                listeners = new LinkedHashSet<>();
                listeners.add(listener);
                this.eventListenerMap.put(eventType, listeners);
            }
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    /**
     * 注销监听者
     *
     * @param listener the listener
     */
    @Override
    public void unRegister(FoxEventListener<AbstractFoxEvent> listener) {
        if (null == listener) {
            return;
        }

        Class<AbstractFoxEvent> eventType = listener.getEventType();
        if (null == eventType) {
            return;
        }

        Set<FoxEventListener<AbstractFoxEvent>> listeners = this.eventListenerMap.get(eventType);
        if (null != listeners) {
            try {
                this.lock.writeLock().lock();
                listeners.remove(listener);
            } finally {
                this.lock.writeLock().unlock();
            }
        }
    }

    /**
     * 注销所有监听者
     */
    @Override
    public void unRegisterAll() {
        for (Map.Entry<Class<AbstractFoxEvent>, Set<FoxEventListener<AbstractFoxEvent>>> entry : this.eventListenerMap.entrySet()) {
            entry.getValue().clear();
        }

        this.eventListenerMap.clear();
    }

    /**
     * 获取所有监听者
     *
     * @return the all
     */
    @Override
    public Collection<FoxEventListener<AbstractFoxEvent>> getAll() {
        Collection<FoxEventListener<AbstractFoxEvent>> listeners = new LinkedList<>();
        this.eventListenerMap.forEach((k, v) -> v.forEach(item -> listeners.add(item)));
        return listeners;
    }

    /**
     * 获取关注事件的监听者
     *
     * @param event the event
     * @return the collection
     */
    @Override
    public Collection<FoxEventListener<AbstractFoxEvent>> get(AbstractFoxEvent event) {
        Collection<FoxEventListener<AbstractFoxEvent>> listeners = new LinkedList<>();
        Set<FoxEventListener<AbstractFoxEvent>> findListeners = this.eventListenerMap.get(event.getClass());
        if (null != findListeners) {
            findListeners.forEach(item -> listeners.add(item));
        }

        return listeners;
    }
}