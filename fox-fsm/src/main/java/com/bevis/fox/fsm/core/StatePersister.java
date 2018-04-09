package com.bevis.fox.fsm.core;

import com.bevis.fox.fsm.exception.StatePersisterException;

/**
 * 状态持久化
 *
 * @param <T> 业务数据泛型
 * @param <StateType> 状态泛型
 * @author bevis
 * @version StatePersister.java, v 0.1 2018/4/1 下午3:52
 */
public interface StatePersister<T, StateType> {
    /**
     * 更新状态
     *
     * @param data      业务数据
     * @param fromState 原始状态
     * @param toState   目标状态
     * @return 更新结果 boolean
     * @throws StatePersisterException the state persister exception
     */
    boolean updateState(T data, StateType fromState, StateType toState) throws StatePersisterException;
}