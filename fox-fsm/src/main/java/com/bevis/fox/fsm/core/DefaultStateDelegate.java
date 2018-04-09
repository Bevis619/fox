package com.bevis.fox.fsm.core;

import com.bevis.fox.fsm.enums.InvokeTypeEnum;
import com.bevis.fox.fsm.exception.StateException;
import org.springframework.util.Assert;

/**
 * The type Default state delegate.
 *
 * @param <T>         the type parameter
 * @param <BizType>   the type parameter
 * @param <StateType> the type parameter
 * @author bevis
 * @version DefaultStateDelegate.java, v 0.1 2018/4/4 下午9:58
 */
public class DefaultStateDelegate<T, BizType, StateType> implements StateDelegate<T, BizType, StateType> {
    /**
     * The State manager.
     */
    private StateManager<T, BizType, StateType> stateManager;
    /**
     * The State persister.
     */
    private StatePersister<T, StateType> statePersister;

    /**
     * Sets state manager.
     *
     * @param stateManager the state manager
     */
    public void setStateManager(StateManager<T, BizType, StateType> stateManager) {
        this.stateManager = stateManager;
    }

    /**
     * Sets state persister.
     *
     * @param statePersister the state persister
     */
    public void setStatePersister(StatePersister<T, StateType> statePersister) {
        this.statePersister = statePersister;
    }

    /**
     * Sets state.
     *
     * @param stateContext the state context
     * @throws StateException the state exception
     */
    @Override
    public void setState(StateContext<T, BizType, StateType> stateContext) throws StateException {
        Assert.notNull(stateContext, "stateContext can't be null");
        Assert.notNull(stateContext.getData(), "data can't be null");
        try {
            this.stateManager.publish(stateContext, InvokeTypeEnum.BEFORE);

            this.statePersister.updateState(stateContext.getData(), stateContext.getFromState(), stateContext.getToState());

            this.stateManager.publish(stateContext, InvokeTypeEnum.POST);
        } catch (StateException e) {
            throw e;
        } catch (Exception e) {
            throw new StateException(e.getMessage(), e);
        }
    }

    /**
     * Sets state.
     *
     * @param data      the data
     * @param bizType   the biz type
     * @param fromState the from state
     * @param toState   the to state
     * @throws StateException the state exception
     */
    @Override
    public void setState(T data, BizType bizType, StateType fromState, StateType toState) throws StateException {
        StateContext<T, BizType, StateType> context = StateContext.builder().bizType(bizType).toState(toState)
                .data(data).fromState(fromState).build();
        this.setState(context);
    }
}