package com.bevis.fox.fsm.core;

import com.bevis.fox.fsm.enums.InvokeTypeEnum;
import com.bevis.fox.fsm.exception.StateException;

/**
 * The interface State publisher.
 *
 * @param <T>         the type parameter
 * @param <BizType>   the type parameter
 * @param <StateType> the type parameter
 * @author bevis
 * @version StateManager.java, v 0.1 2018/4/4 下午7:47
 */
public interface StateManager<T, BizType, StateType> {
    /**
     * Publish.
     *
     * @param stateContext the state context
     * @param invokeType   the invoke type
     * @throws StateException the state exception
     */
    void publish(StateContext<T, BizType, StateType> stateContext, InvokeTypeEnum invokeType) throws StateException;
}