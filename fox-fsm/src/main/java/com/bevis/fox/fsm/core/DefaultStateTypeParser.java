package com.bevis.fox.fsm.core;

/**
 * The type Default state type parser.
 *
 * @author bevis
 * @version DefaultStateTypeParser.java, v 0.1 2018/4/4 下午10:09
 */
public class DefaultStateTypeParser implements StateTypeParser<Object, Object> {
    /**
     * Instantiates a new Default state type parser.
     */
    private DefaultStateTypeParser() {

    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static StateTypeParser<Object, Object> getInstance() {
        return StateTypeParserHolder.INSTANCE;
    }

    /**
     * Parse biz type string.
     *
     * @param s the s
     * @return the string
     */
    @Override
    public String parseBizType(Object s) {
        return s.toString();
    }

    /**
     * Parse state type string.
     *
     * @param s the s
     * @return the string
     */
    @Override
    public String parseStateType(Object s) {
        return s.toString();
    }

    /**
     * The type State type parser holder.
     */
    private static class StateTypeParserHolder {
        /**
         * The constant INSTANCE.
         */
        private static final DefaultStateTypeParser INSTANCE = new DefaultStateTypeParser();
    }
}