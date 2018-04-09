package com.bevis.fox.fsm.exception;

/**
 * The type State exception.
 *
 * @author bevis
 * @version StateException.java, v 0.1 2018/4/4 下午7:31
 */
public class StateException extends Exception {
    /**
     * Instantiates a new State exception.
     */
    public StateException() {
        super();
    }

    /**
     * Instantiates a new State exception.
     *
     * @param message the message
     */
    public StateException(String message) {
        super(message);
    }

    /**
     * Instantiates a new State exception.
     *
     * @param message   the message
     * @param throwable the throwable
     */
    public StateException(String message, Throwable throwable) {
        super(message, throwable);
    }
}