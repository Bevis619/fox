package com.bevis.fox.fsm.core;

/**
 * The interface State type parser.
 *
 * @param <BizType>   the type parameter
 * @param <StateType> the type parameter
 * @author bevis
 * @version StateTypeParser.java, v 0.1 2018/4/4 下午10:06
 */
public interface StateTypeParser<BizType, StateType> {
    /**
     * Parse biz type string.
     *
     * @param bizType the biz type
     * @return the string
     */
    String parseBizType(BizType bizType);

    /**
     * Parse state type string.
     *
     * @param stateType the state type
     * @return the string
     */
    String parseStateType(StateType stateType);
}