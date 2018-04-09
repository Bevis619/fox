package com.bevis.fox.drm.core;

import com.bevis.fox.drm.annotation.DrmConfig;
import com.bevis.fox.drm.exception.DrmException;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * The type Default drm register.
 *
 * @author bevis
 * @version DefaultDrmRegister.java, v 0.1 2018/4/6 上午9:58
 */
class DefaultDrmRegister implements DrmRegister {
    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultDrmRegister.class);
    /**
     * The constant FRESHER_MAP.
     */
    private final Map<String, DrmFresher> FRESHER_MAP = Maps.newConcurrentMap();
    /**
     * The constant NODE_MAP.
     */
    private final Map<String, NodeCache> NODE_MAP = Maps.newConcurrentMap();
    /**
     * The Executor.
     */
    private ThreadPoolExecutor executor;

    /**
     * Instantiates a new Default drm register.
     */
    private DefaultDrmRegister() {
    }

    /**
     * Instance drm register.
     *
     * @return the drm register
     */
    public static DrmRegister instance() {
        return DrmRegisterHolder.INSTANCE;
    }

    /**
     * Register.
     *
     * @param fresher the fresher
     * @throws DrmException the drm exception
     */
    @Override
    public void register(DrmFresher fresher) throws DrmException {
        Assert.notNull(fresher, "fresher can't be null");
        DrmConfig config = fresher.getClass().getDeclaredAnnotation(DrmConfig.class);
        Assert.notNull(config, "the fresher no DrmConfig annotation,please check:" + fresher.getClass().getName());
        Assert.hasText(config.name(), "drm name can't be null");

        DrmContext context = this.getContext(config);
        String path = context.getPath();
        if (!FRESHER_MAP.containsKey(path)) {
            FRESHER_MAP.put(path, fresher);
        }

        if (!NODE_MAP.containsKey(path)) {
            NodeCache nodeCache = new NodeCache(DrmFactory.curator(), path);
            try {
                nodeCache.start(true);
                nodeCache.getListenable().addListener(() -> {
                    DrmContext refreshContext = DrmContext.builder().forPath(nodeCache.getPath()).data(new String(nodeCache.getCurrentData().getData())).build();
                    fresher.refresh(refreshContext);
                }, this.getTaskExecutor());
            } catch (Exception e) {
                throw new DrmException(e.getMessage(), e);
            }

            NODE_MAP.put(path, nodeCache);
        }

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(" fresher registered:" + fresher.getClass().getName());
        }
    }

    /**
     * Un register.
     *
     * @param fresher the fresher
     * @throws DrmException the drm exception
     */
    @Override
    public void unRegister(DrmFresher fresher) throws DrmException {
        Assert.notNull(fresher, "fresher can't be null");
        DrmConfig config = fresher.getClass().getDeclaredAnnotation(DrmConfig.class);

        DrmContext context = this.getContext(config);
        String path = context.getPath();

        this.FRESHER_MAP.remove(path);
        NodeCache nodeCache = this.NODE_MAP.get(path);
        if (null != nodeCache) {
            try {
                nodeCache.close();
            } catch (IOException e) {
                throw new DrmException(e.getMessage(), e);
            }

            this.NODE_MAP.remove(path);
        }
    }

    /**
     * Un register all.
     */
    @Override
    public void unRegisterAll() {
        this.FRESHER_MAP.forEach((k, v) -> {
            try {
                this.unRegister(v);
            } catch (DrmException e) {
                if (LOGGER.isErrorEnabled()) {
                    LOGGER.error("unregister refresher error,path={},refresher=,error=", k, v.getClass().getName(), e.getMessage());
                }
            }
        });

        this.FRESHER_MAP.clear();
        this.NODE_MAP.clear();
    }

    /**
     * Gets context.
     *
     * @param config the config
     * @return the context
     */
    private DrmContext getContext(DrmConfig config) {
        DrmContext context = DrmContext.builder().root(config.root()).domain(config.domain()).name(config.name()).build();
        return context;
    }

    /**
     * Gets task executor.
     *
     * @return the task executor
     */
    private Executor getTaskExecutor() {
        if (null == this.executor) {
            synchronized (this) {
                if (null == this.executor) {
                    this.executor = new ThreadPoolExecutor(1, 1 << 10, 60, TimeUnit.SECONDS, new LinkedBlockingDeque<>(),
                            new BasicThreadFactory.Builder().namingPattern("drm-thread-pool-%d").daemon(true).build());
                    this.executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
                }
            }
        }

        return this.executor;
    }

    /**
     * The type Drm register holder.
     */
    private static class DrmRegisterHolder {
        /**
         * The constant INSTANCE.
         */
        private static final DrmRegister INSTANCE = new DefaultDrmRegister();
    }
}