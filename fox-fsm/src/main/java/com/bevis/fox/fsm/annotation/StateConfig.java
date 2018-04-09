package com.bevis.fox.fsm.annotation;

import com.bevis.fox.fsm.enums.InvokeTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * The interface State config.
 *
 * @author bevis
 * @version StateConfig.java, v 0.1 2018/4/1 下午3:20
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface StateConfig {
    /**
     * From state string.
     *
     * @return the state string
     */
    String from();

    /**
     * To state string.
     *
     * @return the state string
     */
    String to();

    /**
     * Biz string.
     *
     * @return the string
     */
    String biz();

    /**
     * Invoke type invoke type enum.
     *
     * @return the invoke type enum
     */
    InvokeTypeEnum invokeType() default InvokeTypeEnum.POST;

    /**
     * Order int.
     *
     * @return the int
     */
    int order() default 0;

    /**
     * Is async boolean.
     *
     * @return the boolean
     */
    boolean isAsync() default false;
}