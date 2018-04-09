package com.bevis.fox.core.test.event;

import com.bevis.fox.core.event.FoxEventListener;
import org.springframework.stereotype.Service;

/**
 * The type Sms listener.
 */
@Service
public class SmsListener implements FoxEventListener<SmsEvent> {
    /**
     * On event.
     *
     * @param event the event
     */
    @Override
    public void onEvent(SmsEvent event) {
        String sms = event.getSms();
    }

    /**
     * Gets event type.
     *
     * @return the event type
     */
    @Override
    public Class<SmsEvent> getEventType() {
        return SmsEvent.class;
    }
}
