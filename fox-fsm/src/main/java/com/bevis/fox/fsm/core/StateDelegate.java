package com.bevis.fox.fsm.core;

import com.bevis.fox.fsm.exception.StateException;

/**
 * The interface State delegator.
 *
 * @param <T>         the type parameter
 * @param <BizType>   the type parameter
 * @param <StateType> the type parameter
 * @author bevis
 * @version StateDelegate.java, v 0.1 2018/4/4 下午9:55
 */
public interface StateDelegate<T, BizType, StateType> {
    /**
     * Sets state.
     *
     * @param stateContext the state context
     * @throws StateException the state exception
     */
    void setState(StateContext<T, BizType, StateType> stateContext) throws StateException;

    /**
     * Sets state.
     *
     * @param data      the data
     * @param bizType   the biz type
     * @param fromState the from state
     * @param toState   the to state
     * @throws StateException the state exception
     */
    void setState(T data, BizType bizType, StateType fromState, StateType toState) throws StateException;
}