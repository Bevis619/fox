package com.bevis.fox.core.context;

import com.bevis.fox.core.event.AbstractFoxEvent;
import com.bevis.fox.core.event.DefaultEventPublisher;
import com.bevis.fox.core.event.EventPublisher;
import com.bevis.fox.core.event.FoxEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * The type Default fox context.
 *
 * @author bevis
 * @version v1.0 : DefaultFoxContext.class, 2018/3/30 15:20
 */
public class DefaultFoxContext implements FoxContext, ApplicationContextAware {

    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultFoxContext.class);
    /**
     * 启动时间
     */
    private volatile long startupTime;
    /**
     * Spring上下文
     */
    private ApplicationContext springContext;
    /**
     * 容器运行状态
     */
    private volatile boolean isRunning;
    /**
     * The Event publisher.
     */
    private EventPublisher eventPublisher;

    /**
     * Instantiates a new Default fox context.
     */
    public DefaultFoxContext() {
        this.startupTime = System.currentTimeMillis();
    }

    /**
     * Sets event publisher.
     *
     * @param eventPublisher the event publisher
     */
    public void setEventPublisher(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    /**
     * Sets application context.
     *
     * @param applicationContext the application context
     * @throws BeansException the beans exception
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.springContext = applicationContext;
        this.eventPublisher = this.springContext.getBean(EventPublisher.class);
        if (null == this.eventPublisher) {
            this.eventPublisher = new DefaultEventPublisher();
        }

        this.start();
    }

    /**
     * Start.
     *
     * @return the boolean
     */
    @Override
    public boolean start() {
        if (this.isRunning) {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("has stated");
            }

            return this.isRunning;
        }

        try {
            this.isRunning = true;
            // 初始化事件
            this.initEvents();
            // 启动回调
            this.initStarted();
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error(e.getMessage(), e);
            }

            this.isRunning = false;
        }

        return this.isRunning;
    }

    /**
     * Stop.
     */
    @Override
    public void stop() {

    }

    /**
     * Is isRunning boolean.
     *
     * @return the boolean
     */
    @Override
    public boolean isRunning() {
        return this.isRunning;
    }

    /**
     * Gets start time.
     *
     * @return the start time
     */
    @Override
    public long getStartTime() {
        return this.startupTime;
    }

    /**
     * Publish event.
     *
     * @param event the event
     */
    @Override
    public void publishEvent(AbstractFoxEvent event) {
        this.eventPublisher.publish(event, false);
    }

    /**
     * Publish async event.
     *
     * @param event the event
     */
    @Override
    public void publishAsyncEvent(AbstractFoxEvent event) {
        this.eventPublisher.publish(event, true);
    }

    /**
     * Gets spring context.
     *
     * @return the spring context
     */
    @Override
    public ApplicationContext getSpringContext() {
        return springContext;
    }

    /**
     * Init events.
     */
    private void initEvents() {
        Map<String, FoxEventListener> listenerBeanMap = this.springContext.getBeansOfType(FoxEventListener.class, true, true);
        if (null != listenerBeanMap) {
            listenerBeanMap.forEach((k, v) -> this.eventPublisher.register(v));
        }
    }

    /**
     * Init started.
     */
    private void initStarted() {
        Map<String, FoxContextStarted> startedMap = this.springContext.getBeansOfType(FoxContextStarted.class);
        if (null == startedMap || 0 == startedMap.size()) {
            return;
        }

        List<FoxContextStarted> list = new ArrayList(startedMap.size());
        list.addAll(startedMap.values());
        Collections.sort(list, new StartedComparator());
        list.forEach(item -> item.onStarted(this));
    }
}