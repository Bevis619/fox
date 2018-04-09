package com.bevis.fox.core.event;

import java.util.Collection;

/**
 * 事件发布器
 *
 * @author bevis
 * @version v1.0 : EventPublisher.class, 2018/3/30 15:31
 */
public interface EventPublisher {
    /**
     * 注册监听者
     *
     * @param listener the listener
     */
    void register(FoxEventListener<AbstractFoxEvent> listener);

    /**
     * 注销监听者
     *
     * @param listener the listener
     */
    void unRegister(FoxEventListener<AbstractFoxEvent> listener);

    /**
     * 注销所有监听者
     */
    void unRegisterAll();

    /**
     * 获取所有监听者
     *
     * @return the all
     */
    Collection<FoxEventListener<AbstractFoxEvent>> getAll();

    /**
     * 获取关注事件的监听者
     *
     * @param event the event
     * @return the collection
     */
    Collection<FoxEventListener<AbstractFoxEvent>> get(AbstractFoxEvent event);

    /**
     * 发布事件通知监听者
     *
     * @param event   the event
     * @param isAsync the is async
     */
    void publish(AbstractFoxEvent event, boolean isAsync);
}