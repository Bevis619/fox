package com.bevis.fox.fsm.core;

import com.bevis.fox.fsm.annotation.StateConfig;
import org.apache.commons.lang3.EnumUtils;

/**
 * The interface State filter.
 *
 * @author bevis
 * @version StateFilter.java, v 0.1 2018/4/5 上午12:22
 */
public interface StateFilter {
    /**
     * Filter boolean.
     *
     * @param stateConfig the state config
     * @return the boolean
     */
    boolean filter(StateConfig stateConfig);

    /**
     * Filter boolean.
     *
     * @param <E>         the type parameter
     * @param tclass      the tclass
     * @param stateConfig the state config
     * @return the boolean
     */
    default <E extends Enum<E>> boolean filter(Class<E> tclass, StateConfig stateConfig) {
        return EnumUtils.isValidEnum(tclass, stateConfig.from()) && EnumUtils.isValidEnum(tclass, stateConfig.to());
    }
}