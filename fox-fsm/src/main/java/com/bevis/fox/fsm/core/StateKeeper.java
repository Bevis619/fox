package com.bevis.fox.fsm.core;

/**
 * @author bevis
 * @version StateKeeper.java, v 0.1 2018/4/4 下午9:04
 */

import com.bevis.fox.fsm.annotation.StateConfig;

/**
 * The type Invoker keeper.
 *
 * @param <T>         the type parameter
 * @param <BizType>   the type parameter
 * @param <StateType> the type parameter
 */
public class StateKeeper<T, BizType, StateType> implements Comparable<StateKeeper> {
    /**
     * The State config.
     */
    private final StateConfig config;
    /**
     * The Invoker.
     */
    private final StateInvoker<T, BizType, StateType> invoker;

    /**
     * Instantiates a new Invoke keeper.
     *
     * @param config  the config
     * @param invoker the invoker
     */
    public StateKeeper(StateConfig config, StateInvoker<T, BizType, StateType> invoker) {
        this.config = config;
        this.invoker = invoker;
    }

    /**
     * Gets config.
     *
     * @return the config
     */
    public StateConfig getConfig() {
        return config;
    }

    /**
     * Gets invoker.
     *
     * @return the invoker
     */
    public StateInvoker<T, BizType, StateType> getInvoker() {
        return invoker;
    }

    /**
     * Compare to int.
     *
     * @param o the o
     * @return the int
     */
    @Override
    public int compareTo(StateKeeper o) {
        return this.config.order() - o.config.order();
    }
}