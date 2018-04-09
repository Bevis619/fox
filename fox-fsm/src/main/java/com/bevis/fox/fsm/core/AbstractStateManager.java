package com.bevis.fox.fsm.core;

import com.bevis.fox.fsm.annotation.StateConfig;
import com.bevis.fox.fsm.constants.StateConstants;
import com.bevis.fox.fsm.enums.InvokeTypeEnum;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

/**
 * The type Abstract state manager.
 *
 * @param <T>         the type parameter
 * @param <BizType>   the type parameter
 * @param <StateType> the type parameter
 */
public abstract class AbstractStateManager<T, BizType, StateType> implements StateQueue<T, BizType, StateType> {

    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractStateManager.class);

    /**
     * The Lock.
     */
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    /**
     * The Invoke keeper map.
     */
    private final Map<String, List<StateKeeper<T, BizType, StateType>>> invokeKeeperMap = new ConcurrentHashMap<>();
    /**
     * The State type parser.
     */
    protected StateTypeParser stateTypeParser;

    /**
     * The State filter.
     */
    protected StateFilter stateFilter;

    /**
     * Sets state filter.
     *
     * @param stateFilter the state filter
     */
    public void setStateFilter(StateFilter stateFilter) {
        this.stateFilter = stateFilter;
    }

    /**
     * Sets state type parser.
     *
     * @param stateTypeParser the state type parser
     */
    public void setStateTypeParser(StateTypeParser stateTypeParser) {
        this.stateTypeParser = stateTypeParser;
    }

    /**
     * Gets all invokers.
     *
     * @return the all invokers
     */
    @Override
    public List<StateKeeper<T, BizType, StateType>> getAllInvokers() {
        List<StateKeeper<T, BizType, StateType>> keepers = new LinkedList<>();
        this.invokeKeeperMap.forEach((k, v) -> {
            v.forEach(p -> keepers.add(p));
        });
        return keepers;
    }

    /**
     * Gets invokers.
     *
     * @param stateContext the state context
     * @param invokeType   the invoke type
     * @return the invokers
     */
    @Override
    public List<StateKeeper<T, BizType, StateType>> getInvokers(StateContext<T, BizType, StateType> stateContext, InvokeTypeEnum invokeType) {
        String stateKey = this.getStateKey(stateContext);
        List<StateKeeper<T, BizType, StateType>> keepers = this.invokeKeeperMap.get(stateKey);
        if (null == keepers || 0 == keepers.size()) {
            return null;
        }

        List<StateKeeper<T, BizType, StateType>> filterKeepers = keepers.stream().filter(p -> p.getConfig().invokeType().equals(invokeType)).collect(Collectors.toList());
        Collections.sort(filterKeepers);
        return filterKeepers;
    }

    /**
     * Register invoker.
     *
     * @param invoker the invoker
     */
    @Override
    public void registerInvoker(StateInvoker<T, BizType, StateType> invoker) {
        if (null == invoker) {
            return;
        }

        StateConfig config = invoker.getClass().getDeclaredAnnotation(StateConfig.class);
        if (null == config) {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("invoker[{}] not't StateConfig,can't register,please check", invoker.getClass().getName());
            }

            return;
        }

        if (!this.stateFilter.filter(config)) {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("state filter failed,please make sure StateConfig value is correct:{}->{}", config.from(), config.to());
            }

            return;
        }

        String stateKey = this.getStateKey(config);
        List<StateKeeper<T, BizType, StateType>> keepers = this.invokeKeeperMap.get(stateKey);
        if (null == keepers) {
            try {
                this.lock.writeLock().lock();
                keepers = new LinkedList<>();
                keepers.add(new StateKeeper(config, invoker));
            } finally {
                this.lock.writeLock().unlock();
            }

            this.invokeKeeperMap.put(stateKey, keepers);
        } else {
            keepers.add(new StateKeeper(config, invoker));
        }
    }

    /**
     * Un register invoker.
     *
     * @param invoker the invoker
     */
    @Override
    public void unRegisterInvoker(StateInvoker<T, BizType, StateType> invoker) {
        if (null == invoker) {
            return;
        }

        StateConfig config = invoker.getClass().getDeclaredAnnotation(StateConfig.class);
        if (null == config) {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("invoker[{}] not't StateConfig,can't unRegister,please check", invoker.getClass().getName());
            }

            return;
        }

        String stateKey = this.getStateKey(config);
        List<StateKeeper<T, BizType, StateType>> keepers = this.invokeKeeperMap.get(stateKey);
        if (null != keepers) {
            try {
                StateKeeper<T, BizType, StateType> keeper = keepers.stream().filter(p -> p.getInvoker().equals(invoker)).findFirst().orElse(null);
                this.lock.writeLock().lock();
                keepers.remove(keeper);
            } finally {
                this.lock.writeLock().unlock();
            }
        }
    }

    /**
     * Un register all.
     */
    @Override
    public void unRegisterAll() {
        List<StateKeeper<T, BizType, StateType>> keepers = this.getAllInvokers();
        keepers.forEach(p -> this.unRegisterInvoker(p.getInvoker()));
    }

    /**
     * Gets state key.
     *
     * @param config the config
     * @return the state key
     */
    private String getStateKey(StateConfig config) {
        return config.biz() + StateConstants.SPLITER + StringUtils.defaultString(config.from(), StateConstants.DEFAULT_STATE) + StateConstants.LINKER + StringUtils.defaultString(config.to(), StateConstants.DEFAULT_STATE);
    }

    /**
     * Gets state key.
     *
     * @param stateContext the state context
     * @return the state key
     */
    private String getStateKey(StateContext<T, BizType, StateType> stateContext) {
        String bizType = StringUtils.defaultString(this.getTypeParser().parseBizType(stateContext.getBizType()), StateConstants.DEFAULT_BIZ);
        String fromState = StringUtils.defaultString(this.getTypeParser().parseStateType(stateContext.getFromState()), StateConstants.DEFAULT_STATE);
        String toState = StringUtils.defaultString(this.getTypeParser().parseStateType(stateContext.getToState()), StateConstants.DEFAULT_STATE);
        return bizType + StateConstants.SPLITER + fromState + StateConstants.LINKER + toState;
    }

    /**
     * Gets type parser.
     *
     * @return the type parser
     */
    private StateTypeParser getTypeParser() {
        if (null == this.stateTypeParser) {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("stateTypeParser is null,use the DefaultStateTypeParser");
            }

            this.stateTypeParser = DefaultStateTypeParser.getInstance();
        }

        return this.stateTypeParser;
    }
}