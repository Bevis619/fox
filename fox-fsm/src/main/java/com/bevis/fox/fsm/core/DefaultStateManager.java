package com.bevis.fox.fsm.core;

import com.bevis.fox.fsm.enums.InvokeTypeEnum;
import com.bevis.fox.fsm.exception.StateException;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * The type Default state manager.
 *
 * @param <T>         the type parameter
 * @param <BizType>   the type parameter
 * @param <StateType> the type parameter
 * @author bevis
 * @version DefaultStateManager.java, v 0.1 2018/4/4 下午8:45
 */
public class DefaultStateManager<T, BizType, StateType> extends AbstractStateManager<T, BizType, StateType> {
    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultStateManager.class);

    /**
     * The Executor.
     */
    private ThreadPoolExecutor executor;

    /**
     * Publish.
     *
     * @param stateContext the state context
     * @param invokeType   the invoke type
     * @throws StateException the state exception
     */
    @Override
    public void publish(StateContext<T, BizType, StateType> stateContext, InvokeTypeEnum invokeType) throws StateException {
        boolean result = true;
        List<StateKeeper<T, BizType, StateType>> keepers = super.getInvokers(stateContext, invokeType);
        if (null == keepers) {
            throw new StateException(MessageFormat.format("not find keepers,stateContext={0},invoKeType={1}", stateContext, invokeType));
        }

        for (StateKeeper<T, BizType, StateType> keeper : keepers) {
            if (keeper.getConfig().isAsync()) {
                this.getTaskExecutor().execute(() -> {
                    keeper.getInvoker().invoke(stateContext);
                });
            } else {
                if (result) {
                    result = keeper.getInvoker().invoke(stateContext);
                }
            }
        }
    }

    /**
     * Gets task executor.
     *
     * @return the task executor
     */
    private Executor getTaskExecutor() {
        if (null == this.executor) {
            synchronized (this) {
                if (null == this.executor) {
                    this.executor = new ThreadPoolExecutor(1, 1 << 10, 60, TimeUnit.SECONDS, new LinkedBlockingDeque<>(),
                            new BasicThreadFactory.Builder().namingPattern("fsm-thread-pool-%d").daemon(true).build());
                    this.executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
                }
            }
        }

        return this.executor;
    }
}