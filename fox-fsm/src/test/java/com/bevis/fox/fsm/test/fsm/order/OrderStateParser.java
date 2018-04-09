package com.bevis.fox.fsm.test.fsm.order;

import com.bevis.fox.fsm.core.StateTypeParser;
import com.bevis.fox.fsm.test.enums.BizTypeEnum;
import com.bevis.fox.fsm.test.enums.OrderStateEnum;

/**
 * The type Order state parser.
 *
 * @author bevis
 * @version OrderStateParser.java, v 0.1 2018/4/4 下午10:49
 */
public class OrderStateParser implements StateTypeParser<BizTypeEnum, OrderStateEnum> {
    /**
     * Parse biz type string.
     *
     * @param bizTypeEnum the biz type
     * @return the string
     */
    @Override
    public String parseBizType(BizTypeEnum bizTypeEnum) {
        return bizTypeEnum.name();
    }

    /**
     * Parse state type string.
     *
     * @param orderStateEnum the state type
     * @return the string
     */
    @Override
    public String parseStateType(OrderStateEnum orderStateEnum) {
        return orderStateEnum.name();
    }
}
