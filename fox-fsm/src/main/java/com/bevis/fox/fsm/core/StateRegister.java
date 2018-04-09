package com.bevis.fox.fsm.core;

/**
 * The interface State register.
 *
 * @param <T>         the type parameter
 * @param <BizType>   the type parameter
 * @param <StateType> the type parameter
 * @author bevis
 * @version StateRegister.java, v 0.1 2018/4/4 下午7:43
 */
public interface StateRegister<T, BizType, StateType> {
    /**
     * Register invoker.
     *
     * @param invoker the invoker
     */
    void registerInvoker(StateInvoker<T, BizType, StateType> invoker);

    /**
     * Un register invoker.
     *
     * @param invoker the invoker
     */
    void unRegisterInvoker(StateInvoker<T, BizType, StateType> invoker);

    /**
     * Un register all.
     */
    void unRegisterAll();
}