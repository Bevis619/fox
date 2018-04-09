package com.bevis.fox.drm.annotation;

import com.bevis.fox.drm.constants.DrmConstants;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 分布式资源配置
 *
 * @author bevis
 * @version DrmConfig.java, v 0.1 2018/4/6 上午12:21
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DrmConfig {
    /**
     * 资源所属根
     *
     * @return the string
     */
    String root() default DrmConstants.DEFAULT_ROOT;

    /**
     * 资源所属域
     *
     * @return the string
     */
    String domain() default DrmConstants.DEFAULT_DOMAIN;

    /**
     * 资源名称
     *
     * @return the string
     */
    String name();
}