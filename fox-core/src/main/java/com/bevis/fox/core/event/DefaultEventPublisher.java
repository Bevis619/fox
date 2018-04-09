package com.bevis.fox.core.event;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * The type Default event publisher.
 *
 * @author bevis
 * @version v1.0 : DefaultEventPublisher.class, 2018/3/30 17:28
 */
public class DefaultEventPublisher extends AbstractEventPublisher implements EventPublisher {
    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultEventPublisher.class);

    /**
     * The Executor.
     */
    private Executor            executor;

    /**
     * 发布事件通知监听者
     *
     * @param event   the event
     * @param isAsync the is async
     */
    @Override
    public void publish(AbstractFoxEvent event, boolean isAsync) {
        Assert.notNull(event, "event can't be null");
        Collection<FoxEventListener<AbstractFoxEvent>> listeners = super.get(event);
        for (FoxEventListener<AbstractFoxEvent> listener : listeners) {
            if (!isAsync) {
                try {
                    listener.onEvent(event);
                } catch (Exception e) {
                    if (LOGGER.isErrorEnabled()) {
                        LOGGER.error(listener.getClass().getName() + " error", e);
                    }

                    throw new RuntimeException(e);
                }
            } else {
                this.getTaskExecutor().execute(() -> listener.onEvent(event));
            }
        }
    }

    /**
     * Gets task executor.
     *
     * @return the task executor
     */
    private Executor getTaskExecutor() {
        if (null == this.executor) {
            synchronized (this) {
                if(null == this.executor) {
                    this.executor = new ThreadPoolExecutor(1, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new LinkedBlockingDeque<>(),
                            new BasicThreadFactory.Builder().namingPattern("event-thread-pool-%d").daemon(true).build());
                }
            }
        }

        return this.executor;
    }
}