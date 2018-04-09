package com.bevis.fox.drm.exception;

/**
 * The type Drm exception.
 *
 * @author bevis
 * @version DrmException.java, v 0.1 2018/4/6 上午9:51
 */
public class DrmException extends Exception {
    /**
     * Instantiates a new drm exception.
     */
    public DrmException() {
        super();
    }

    /**
     * Instantiates a new drm exception.
     *
     * @param message the message
     */
    public DrmException(String message) {
        super(message);
    }

    /**
     * Instantiates a new drm exception.
     *
     * @param message   the message
     * @param throwable the throwable
     */
    public DrmException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
