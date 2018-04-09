package com.bevis.fox.core.test.event;

import com.bevis.fox.core.event.AbstractFoxEvent;

/**
 * The type Log event.
 */
public class LogEvent extends AbstractFoxEvent {

    /**
     * Gets log.
     *
     * @return the log
     */
    public String getLog() {
        return log;
    }

    /**
     * Sets log.
     *
     * @param log the log
     */
    public void setLog(String log) {
        this.log = log;
    }

    /**
     * The Log.
     */
    private String log;


    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @param log    the log
     * @throws IllegalArgumentException if source is null.
     */
    public LogEvent(Object source, String log) {
        super(source, "log");
        this.log = log;
    }
}
