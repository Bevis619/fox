package com.bevis.fox.drm;

/**
 * The interface Drm starter.
 *
 * @author bevis
 * @version DrmStarter.java, v 0.1 2018/4/6 上午12:12
 */
public interface DrmStarter {
    /**
     * Start.
     *
     * @param zkConnectString the zk connect string
     */
    void start(String zkConnectString);
}