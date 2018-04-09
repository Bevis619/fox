package com.bevis.fox.core.context;

/**
 * The interface Fox context started.
 *
 * @author bevis
 * @version FoxContextStarted.java, v 0.1 2018/4/1 下午3:57
 */
public interface FoxContextStarted extends Orderable {
    /**
     * On started.
     *
     * @param foxContext the fox context
     */
    void onStarted(FoxContext foxContext);
}
