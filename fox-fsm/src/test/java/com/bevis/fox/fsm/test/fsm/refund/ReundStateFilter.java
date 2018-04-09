package com.bevis.fox.fsm.test.fsm.refund;

import com.bevis.fox.fsm.annotation.StateConfig;
import com.bevis.fox.fsm.core.StateFilter;
import com.bevis.fox.fsm.test.enums.BizTypeEnum;
import com.bevis.fox.fsm.test.enums.RefundStateEnum;

/**
 * The type Order state filter.
 *
 * @author bevis
 * @version OrderStateFilter.java, v 0.1 2018/4/5 上午12:42
 */
public class ReundStateFilter implements StateFilter {
    /**
     * Filter boolean.
     *
     * @param stateConfig the state config
     * @return the boolean
     */
    @Override
    public boolean filter(StateConfig stateConfig) {
        return this.filter(RefundStateEnum.class, stateConfig) && BizTypeEnum.REFUND.name().equals(stateConfig.biz());
    }
}