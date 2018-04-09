package com.bevis.fox.fsm.core;

import com.bevis.fox.fsm.enums.InvokeTypeEnum;

import java.util.List;

/**
 * The interface State queue.
 *
 * @param <T>         the type parameter
 * @param <BizType>   the type parameter
 * @param <StateType> the type parameter
 * @author bevis
 * @version StateQueue.java, v 0.1 2018/4/4 下午7:47
 */
public interface StateQueue<T, BizType, StateType> extends StateManager<T, BizType, StateType>, StateRegister<T, BizType, StateType> {

    /**
     * Gets all invokers.
     *
     * @return the all invokers
     */
    List<StateKeeper<T, BizType, StateType>> getAllInvokers();

    /**
     * Gets invokers.
     *
     * @param stateContext the state context
     * @param invokeType   the invoke type
     * @return the invokers
     */
    List<StateKeeper<T, BizType, StateType>> getInvokers(StateContext<T, BizType, StateType> stateContext, InvokeTypeEnum invokeType);
}