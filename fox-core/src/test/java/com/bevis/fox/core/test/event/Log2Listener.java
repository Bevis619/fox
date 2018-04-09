package com.bevis.fox.core.test.event;

import com.bevis.fox.core.event.FoxEventListener;
import org.springframework.stereotype.Service;

/**
 * The type Log 2 listener.
 */
@Service
public class Log2Listener implements FoxEventListener<LogEvent> {
    /**
     * On event.
     *
     * @param event the event
     */
    @Override
    public void onEvent(LogEvent event) {
        System.out.println("log2:"+event.getLog());
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
