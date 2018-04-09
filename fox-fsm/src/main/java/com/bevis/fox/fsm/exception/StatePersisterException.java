package com.bevis.fox.fsm.exception;

/**
 * The type State persister exception.
 *
 * @author bevis
 * @version StatePersisterException.java, v 0.1 2018/4/4 下午6:45
 */
public class StatePersisterException extends Exception {

    /**
     * Instantiates a new State persister exception.
     */
    public StatePersisterException() {
        super();
    }

    /**
     * Instantiates a new State persister exception.
     *
     * @param message the message
     */
    public StatePersisterException(String message) {
        super(message);
    }

    /**
     * Instantiates a new State persister exception.
     *
     * @param message   the message
     * @param throwable the throwable
     */
    public StatePersisterException(String message, Throwable throwable) {
        super(message, throwable);
    }
}