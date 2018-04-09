package com.bevis.fox.fsm.core;

/**
 * The type Default state context.
 *
 * @param <T>         the type parameter
 * @param <BizType>   the type parameter
 * @param <StateType> the type parameter
 * @author bevis
 * @version StateContext.java, v 0.1 2018/4/4 下午7:15
 */
public class StateContext<T, BizType, StateType> {
    /**
     * The Data.
     */
    private T data;
    /**
     * The Biz type.
     */
    private BizType bizType;
    /**
     * The From state.
     */
    private StateType fromState;
    /**
     * The To state.
     */
    private StateType toState;

    /**
     * Instantiates a new State context.
     *
     * @param builder the builder
     */
    private StateContext(Builder<T, BizType, StateType> builder) {
        this.data = builder.data;
        this.bizType = builder.bizType;
        this.fromState = builder.fromState;
        this.toState = builder.toState;
    }

    /**
     * Gets data.
     *
     * @return the data
     */
    public static Builder builder() {
        return new Builder<>();
    }

    /**
     * Gets data.
     *
     * @return the data
     */
    public T getData() {
        return this.data;
    }

    /**
     * Gets biz type.
     *
     * @return the biz type
     */
    public BizType getBizType() {
        return this.bizType;
    }

    /**
     * Gets from state.
     *
     * @return the from state
     */
    public StateType getFromState() {
        return this.fromState;
    }

    /**
     * Gets to state.
     *
     * @return the to state
     */
    public StateType getToState() {
        return this.toState;
    }

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "StateContext{" +
                "bizType=" + bizType +
                ", fromState=" + fromState +
                ", toState=" + toState +
                '}';
    }

    /**
     * The type Builder.
     *
     * @param <T>         the type parameter
     * @param <BizType>   the type parameter
     * @param <StateType> the type parameter
     */
    public static class Builder<T, BizType, StateType> {
        /**
         * The Data.
         */
        private T data;
        /**
         * The Biz type.
         */
        private BizType bizType;
        /**
         * The From state.
         */
        private StateType fromState;
        /**
         * The To state.
         */
        private StateType toState;

        /**
         * Biz type builder.
         *
         * @param bizType the biz type
         * @return the builder
         */
        public Builder bizType(BizType bizType) {
            this.bizType = bizType;
            return this;
        }

        /**
         * To state builder.
         *
         * @param toState the to state
         * @return the builder
         */
        public Builder toState(StateType toState) {
            this.toState = toState;
            return this;
        }

        /**
         * Builder from state builder.
         *
         * @param fromState the from state
         * @return the builder
         */
        public Builder fromState(StateType fromState) {
            this.fromState = fromState;
            return this;
        }

        /**
         * Build data builder.
         *
         * @param data the data
         * @return the builder
         */
        public Builder data(T data) {
            this.data = data;
            return this;
        }

        /**
         * Build state context.
         *
         * @return the state context
         */
        public StateContext<T, BizType, StateType> build() {
            return new StateContext<>(this);
        }
    }
}