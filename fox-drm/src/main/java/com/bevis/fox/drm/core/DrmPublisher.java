package com.bevis.fox.drm.core;

import com.bevis.fox.drm.exception.DrmException;

/**
 * The interface Drm client.
 *
 * @author bevis
 * @version DrmPublisher.java, v 0.1 2018/4/6 上午12:39
 */
public interface DrmPublisher {
    /**
     * 发布资源
     *
     * @param context the context
     * @throws DrmException the drm exception
     */
    void publish(DrmContext context) throws DrmException;
}