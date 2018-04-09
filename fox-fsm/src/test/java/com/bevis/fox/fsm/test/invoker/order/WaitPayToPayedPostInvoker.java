package com.bevis.fox.fsm.test.invoker.order;

import com.bevis.fox.fsm.annotation.StateConfig;
import com.bevis.fox.fsm.core.StateContext;
import com.bevis.fox.fsm.core.StateInvoker;
import com.bevis.fox.fsm.enums.InvokeTypeEnum;
import com.bevis.fox.fsm.test.enums.BizTypeEnum;
import com.bevis.fox.fsm.test.enums.OrderStateEnum;
import com.bevis.fox.fsm.test.vo.OrderVO;
import org.springframework.stereotype.Service;

/**
 * The type Wait pay to payed order invoker.
 *
 * @author bevis
 * @version WaitPayToPayedBeforeInvoker.java, v 0.1 2018/4/4 下午11:06
 */
@Service
@StateConfig(biz = "ORDER", from = "HNADING", to = "CANCELED", invokeType = InvokeTypeEnum.POST, isAsync = true)
public class WaitPayToPayedPostInvoker implements StateInvoker<OrderVO, BizTypeEnum, OrderStateEnum> {
    /**
     * Invoke boolean.
     *
     * @param context the context
     * @return the boolean
     */
    @Override
    public boolean invoke(StateContext<OrderVO, BizTypeEnum, OrderStateEnum> context) {
        System.out.println("Post-" + context.getData().getName() + ":" + context.toString());
        return true;
    }
}
