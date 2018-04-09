package com.bevis.fox.fsm.test.invoker.refund;

import com.bevis.fox.fsm.annotation.StateConfig;
import com.bevis.fox.fsm.core.StateContext;
import com.bevis.fox.fsm.core.StateInvoker;
import com.bevis.fox.fsm.enums.InvokeTypeEnum;
import com.bevis.fox.fsm.test.enums.RefundStateEnum;
import com.bevis.fox.fsm.test.vo.RefundVO;
import org.springframework.stereotype.Service;

/**
 * The type Wait refund to refunding before invoker.
 *
 * @author bevis
 * @version WaitRefundToRefundingBeforeInvoker.java, v 0.1 2018/4/5 上午1:04
 */
@Service
@StateConfig(biz = "REFUND", from = "HNADING", to = "CANCELED", invokeType = InvokeTypeEnum.BEFORE, order = 3)
public class WaitRefundToRefundingBeforeInvoker implements StateInvoker<RefundVO, String, RefundStateEnum> {
    /**
     * Invoke boolean.
     *
     * @param context the context
     * @return the boolean
     */
    @Override
    public boolean invoke(StateContext<RefundVO, String, RefundStateEnum> context) {
        System.out.println("Before first-" + context.getData().getName() + ":" + context.toString());
        return true;
    }
}