package com.bevis.fox.fsm.core;

/**
 * The interface State invoker.
 *
 * @param <T>         the type parameter
 * @param <BizType>   the type parameter
 * @param <StateType> the type parameter
 * @author bevis
 * @version StateInvoker.java, v 0.1 2018/4/1 下午3:46
 */
public interface StateInvoker<T, BizType, StateType> {
    /**
     * Invoke boolean.
     *
     * @param context the context
     * @return the boolean
     */
    boolean invoke(StateContext<T, BizType, StateType> context);
}
