package com.bevis.fox.core.test.event;

import com.bevis.fox.core.event.AbstractFoxEvent;

/**
 * The type Sms event.
 */
public class SmsEvent extends AbstractFoxEvent {
    /**
     * The Sms.
     */
    private String sms;

    /**
     * Gets sms.
     *
     * @return the sms
     */
    public String getSms() {
        return sms;
    }

    /**
     * Sets sms.
     *
     * @param sms the sms
     */
    public void setSms(String sms) {
        this.sms = sms;
    }

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @param sms    the sms
     * @throws IllegalArgumentException if source is null.
     */
    public SmsEvent(Object source, String sms) {
        super(source, "smsEvent");
        this.sms = sms;
    }
}
