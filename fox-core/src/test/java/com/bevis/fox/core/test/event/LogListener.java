package com.bevis.fox.core.test.event;

import com.bevis.fox.core.event.FoxEventListener;
import org.springframework.stereotype.Service;

/**
 * The type Log listener.
 */
@Service
public class LogListener implements FoxEventListener<LogEvent> {
    /**
     * On event.
     *
     * @param event the event
     */
    @Override
    public void onEvent(LogEvent event) {
        String log = event.getLog();
        String eventName = event.getEventName();
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(log);
    }

    /**
     * Gets event type.
     *
     * @return the event type
     */
    @Override
    public Class<LogEvent> getEventType() {
        return LogEvent.class;
    }
}
