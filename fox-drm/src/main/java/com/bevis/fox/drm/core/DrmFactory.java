package com.bevis.fox.drm.core;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * The type Drm factory.
 *
 * @author bevis
 * @version DrmFactory.java, v 0.1 2018/4/6 上午8:46
 */
public class DrmFactory {

    /**
     * The Curator.
     */
    private CuratorFramework curator;

    /**
     * Instantiates a new Drm factory.
     */
    private DrmFactory() {
    }

    /**
     * Builder builder.
     *
     * @param zkConnectString the zk connect string
     * @return the builder
     */
    public static void builder(String zkConnectString) {
        new Builder(zkConnectString).build();
    }

    /**
     * Curator curator framework.
     *
     * @return the curator framework
     */
    public static CuratorFramework curator() {
        return Builder.factory.curator;
    }

    /**
     * Publisher drm publisher.
     *
     * @return the drm publisher
     */
    public static DrmPublisher publisher() {
        return new DefaultDrmPublisher();
    }

    /**
     * Register drm register.
     *
     * @return the drm register
     */
    public static DrmRegister register() {
        return DefaultDrmRegister.instance();
    }

    /**
     * The type Builder.
     */
    static class Builder {
        /**
         * The Factory.
         */
        private static DrmFactory factory = new DrmFactory();
        /**
         * The Zk connect string.
         */
        private String zkConnectString;

        /**
         * Zk connect string builder.
         *
         * @param zkConnectString the zk connect string
         * @return the builder
         */
        public Builder(String zkConnectString) {
            this.zkConnectString = zkConnectString;
        }

        /**
         * Build drm factory.
         *
         * @return the drm factory
         */
        public DrmFactory build() {
            this.init();
            return factory;
        }

        /**
         * Init.
         */
        private void init() {
            RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
            factory.curator = CuratorFrameworkFactory.builder().connectString(zkConnectString).retryPolicy(retryPolicy).build();
            this.factory.curator.start();
        }
    }
}