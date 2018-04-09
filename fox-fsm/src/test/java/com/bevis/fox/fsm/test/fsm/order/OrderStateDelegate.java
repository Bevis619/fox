package com.bevis.fox.fsm.test.fsm.order;

import com.bevis.fox.fsm.core.DefaultStateDelegate;
import com.bevis.fox.fsm.test.enums.BizTypeEnum;
import com.bevis.fox.fsm.test.enums.OrderStateEnum;
import com.bevis.fox.fsm.test.vo.OrderVO;

/**
 * The type Order state delegate.
 *
 * @author bevis
 * @version OrderStateDelegate.java, v 0.1 2018/4/4 下午10:51
 */
public class OrderStateDelegate extends DefaultStateDelegate<OrderVO, BizTypeEnum, OrderStateEnum> {
}