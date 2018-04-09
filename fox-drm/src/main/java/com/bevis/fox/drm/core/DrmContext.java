package com.bevis.fox.drm.core;

import com.bevis.fox.drm.constants.DrmConstants;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;

/**
 * The type Drm context.
 *
 * @author bevis
 * @version DrmContext.java, v 0.1 2018/4/6 上午12:41
 */
public class DrmContext {
    /**
     * 资源所属根
     */
    private String root;
    /**
     * 资源所属域
     */
    private String domain;
    /**
     * 资源名称
     */
    private String name;
    /**
     * 数据
     */
    private String data;

    /**
     * Instantiates a new Drm context.
     */
    private DrmContext() {

    }

    /**
     * Instantiates a new Drm context.
     *
     * @param builder the builder
     */
    private DrmContext(Builder builder) {
        this.root = builder.getRoot();
        this.domain = builder.getDomain();
        this.name = builder.getName();
        this.data = builder.getData() == null ? StringUtils.EMPTY : builder.getData().toString();
    }

    /**
     * Builder builder.
     *
     * @return the builder
     */
    public static DrmContext.Builder builder() {
        return new DrmContext.Builder();
    }

    /**
     * Gets root.
     *
     * @return the root
     */
    public String getRoot() {
        return root;
    }

    /**
     * Gets domain.
     *
     * @return the domain
     */
    public String getDomain() {
        return domain;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets data.
     *
     * @return the data
     */
    public String getData() {
        return data;
    }

    /**
     * Get path string.
     *
     * @return the string
     */
    public String getPath() {
        String root = this.root.startsWith(DrmConstants.PREFIX) ? this.root : DrmConstants.PREFIX + this.root;
        String domain = this.domain.startsWith(DrmConstants.PREFIX) ? this.domain : DrmConstants.PREFIX + this.domain;
        String name = this.name.startsWith(DrmConstants.PREFIX) ? this.name : DrmConstants.PREFIX + this.name;
        return MessageFormat.format("{0}{1}{2}", root, domain, name);
    }

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "DrmContext{" +
                "root='" + root + '\'' +
                ", domain='" + domain + '\'' +
                ", name='" + name + '\'' +
                ", data=" + data +
                '}';
    }

    /**
     * The type Builder.
     */
    public static class Builder {
        /**
         * 资源所属根
         */
        private String root;
        /**
         * 资源所属域
         */
        private String domain;
        /**
         * 资源名称
         */
        private String name;
        /**
         * 数据
         */
        private Object data;

        /**
         * Instantiates a new Builder.
         */
        private Builder() {
            this.root = DrmConstants.DEFAULT_ROOT;
            this.domain = DrmConstants.DEFAULT_DOMAIN;
            this.data = StringUtils.EMPTY;
        }

        /**
         * Gets root.
         *
         * @return the root
         */
        public String getRoot() {
            return root;
        }

        /**
         * Gets domain.
         *
         * @return the domain
         */
        public String getDomain() {
            return domain;
        }

        /**
         * Gets name.
         *
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * Gets data.
         *
         * @return the data
         */
        public Object getData() {
            return data;
        }

        /**
         * Root builder.
         *
         * @param root the root
         * @return the builder
         */
        public Builder root(String root) {
            this.root = root;
            return this;
        }

        /**
         * Domain builder.
         *
         * @param domain the domain
         * @return the builder
         */
        public Builder domain(String domain) {
            this.domain = domain;
            return this;
        }

        /**
         * Name builder.
         *
         * @param name the name
         * @return the builder
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Data builder.
         *
         * @param data the data
         * @return the builder
         */
        public Builder data(Object data) {
            this.data = data;
            return this;
        }

        /**
         * For path builder.
         *
         * @param path the path
         * @return the builder
         */
        public Builder forPath(String path) {
            if (StringUtils.isEmpty(path)) {
                return this;
            }

            String newPath = path.startsWith(DrmConstants.PREFIX) ? path.substring(1) : path;
            String nodes[] = newPath.split(DrmConstants.PREFIX);
            if (StringUtils.isNoneEmpty(nodes[0])) {
                this.root = nodes[0];
            }

            if (nodes.length > 1 && StringUtils.isNotEmpty(nodes[1])) {
                this.domain = nodes[1];
            }

            if (nodes.length > 2 && StringUtils.isNotEmpty(nodes[2])) {
                this.name = nodes[2];
            }

            return this;
        }

        /**
         * Build drm context.
         *
         * @return the drm context
         */
        public DrmContext build() {
            return new DrmContext(this);
        }
    }
}