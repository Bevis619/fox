package com.bevis.fox.fsm.test;

import com.bevis.fox.fsm.core.StateContext;
import com.bevis.fox.fsm.core.StateDelegate;
import com.bevis.fox.fsm.exception.StateException;
import com.bevis.fox.fsm.test.enums.BizTypeEnum;
import com.bevis.fox.fsm.test.enums.OrderStateEnum;
import com.bevis.fox.fsm.test.vo.OrderVO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * The type Order test.
 *
 * @author bevis
 * @version OrderTest.java, v 0.1 2018/4/4 下午11:02
 */
public class OrderTest extends BaseTest {
    /**
     * The Order state delegate.
     */
    @Autowired
    private StateDelegate<OrderVO, BizTypeEnum, OrderStateEnum> orderStateDelegate;

    /**
     * Test 1.
     */
    @Test
    public void test1(){

        OrderVO orderVO = new OrderVO();
        orderVO.setName("order");
        StateContext<OrderVO, BizTypeEnum, OrderStateEnum> context = StateContext.builder().bizType(BizTypeEnum.ORDER).toState(OrderStateEnum.CANCELED)
                .data(orderVO).fromState(OrderStateEnum.HNADING).build();
        try {
            this.orderStateDelegate.setState(context);
        } catch (StateException e) {
            e.printStackTrace();
        }
    }
}