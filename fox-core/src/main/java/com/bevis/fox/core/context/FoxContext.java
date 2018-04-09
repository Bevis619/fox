package com.bevis.fox.core.context;

import com.bevis.fox.core.event.AbstractFoxEvent;
import org.springframework.context.ApplicationContext;

/**
 * The interface Fox context.
 *
 * @author bevis
 * @version v1.0 : FoxContext.class, 2018/3/30  15:19
 */
public interface FoxContext {
    /**
     * Start.
     *
     * @return the boolean
     */
    boolean start();

    /**
     * Stop.
     */
    void stop();

    /**
     * Is running boolean.
     *
     * @return the boolean
     */
    boolean isRunning();

    /**
     * Gets start time.
     *
     * @return the start time
     */
    long getStartTime();

    /**
     * Publish event.
     *
     * @param event the event
     */
    void publishEvent(AbstractFoxEvent event);

    /**
     * Publish async event.
     *
     * @param event the event
     */
    void publishAsyncEvent(AbstractFoxEvent event);

    /**
     * Gets spring context.
     *
     * @return the spring context
     */
    ApplicationContext getSpringContext();
}
