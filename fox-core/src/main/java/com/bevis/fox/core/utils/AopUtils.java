package com.bevis.fox.core.utils;

import org.springframework.aop.framework.AdvisedSupport;

import java.lang.reflect.Field;

/**
 * The type Aop utils.
 *
 * @author bevis
 * @version AopUtils.java, v 0.1 2018/4/1 下午3:29
 */
public final class AopUtils {
    /**
     * Instantiates a new Aop utils.
     */
    private AopUtils() {
    }

    /**
     * 获取目标对象
     *
     * @param proxy 代理
     * @return 代理对象 target
     */
    public static Object getTarget(Object proxy) {
        if (!org.springframework.aop.support.AopUtils.isAopProxy(proxy)) {
            return proxy;
        }

        if (org.springframework.aop.support.AopUtils.isJdkDynamicProxy(proxy)) {
            return getJdkDynamicProxyTargetObject(proxy);
        } else { //cglib
            return getCglibProxyTargetObject(proxy);
        }
    }

    /**
     * 获取cglib代理对象
     *
     * @param proxy 代理
     * @return 代理对象 cglib proxy target object
     */
    private static Object getCglibProxyTargetObject(Object proxy) {
        return getProxyTargetObject(proxy, "CGLIB$CALLBACK_0", false);
    }

    /**
     * 获取JDK动态代理对象
     *
     * @param proxy 代理
     * @return 代理对象 jdk dynamic proxy target object
     */
    private static Object getJdkDynamicProxyTargetObject(Object proxy) {
        return getProxyTargetObject(proxy, "h", true);
    }

    /**
     * 获取代理对象
     *
     * @param proxy      代理
     * @param fieldName  declared field name
     * @param superClass the super class
     * @return 代理对象 proxy target object
     */
    private static Object getProxyTargetObject(Object proxy, String fieldName, boolean superClass) {
        try {
            Field h;
            if (superClass) {
                h = proxy.getClass().getSuperclass().getDeclaredField(fieldName);
            } else {
                h = proxy.getClass().getDeclaredField(fieldName);
            }

            h.setAccessible(true);
            Object o = h.get(proxy);

            Field advised = o.getClass().getDeclaredField("advised");
            advised.setAccessible(true);

            return ((AdvisedSupport) advised.get(o)).getTargetSource().getTarget();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}