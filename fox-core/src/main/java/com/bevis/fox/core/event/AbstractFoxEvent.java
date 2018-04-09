package com.bevis.fox.core.event;

import java.util.EventObject;

/**
 * 抽象基础事件
 *
 * @author bevis
 * @version v1.0 : BaseEvent.class, 2018/3/30 15:05
 */
public abstract class AbstractFoxEvent extends EventObject {
    /**
     * The constant serialVersionUID.
     */
    private static final long serialVersionUID = -1656719145096988010L;
    /**
     * The Event name.
     */
    private final String      eventName;
    /**
     * The Create timestamp.
     */
    private final long createTimestamp;

    /**
     * Constructs a prototypical Event.
     *
     * @param source    The object on which the Event initially occurred.
     * @param eventName the event name
     * @throws IllegalArgumentException if source is null.
     */
    public AbstractFoxEvent(Object source, String eventName) {
        super(source);
        this.eventName = eventName;
        this.createTimestamp = System.currentTimeMillis();
    }

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        sb.append(super.toString()).deleteCharAt(super.toString().length() - 1);
        sb.append(", \"eventName\": \"").append(eventName).append('\"');
        sb.append(", \"createTimestamp\": \"").append(createTimestamp).append('\"');
        sb.append('}');
        return sb.toString();
    }

    /**
     * Gets event name.
     *
     * @return the event name
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * Gets create timestamp.
     *
     * @return the create timestamp
     */
    public long getCreateTimestamp() {
        return createTimestamp;
    }
}
