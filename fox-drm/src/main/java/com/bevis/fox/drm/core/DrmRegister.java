package com.bevis.fox.drm.core;

import com.bevis.fox.drm.exception.DrmException;

/**
 * The interface Drm register.
 *
 * @author bevis
 * @version DrmRegister.java, v 0.1 2018/4/6 上午9:57
 */
public interface DrmRegister {
    /**
     * Register.
     *
     * @param fresher the fresher
     * @throws DrmException the drm exception
     */
    void register(DrmFresher fresher) throws DrmException;

    /**
     * Un register.
     *
     * @param fresher the fresher
     * @throws DrmException the drm exception
     */
    void unRegister(DrmFresher fresher) throws DrmException;

    /**
     * Un register all.
     */
    void unRegisterAll();
}