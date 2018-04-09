package com.bevis.fox.fsm.test.fsm.order;

import com.bevis.fox.fsm.core.StatePersister;
import com.bevis.fox.fsm.exception.StatePersisterException;
import com.bevis.fox.fsm.test.enums.OrderStateEnum;
import com.bevis.fox.fsm.test.vo.OrderVO;

/**
 * @author bevis
 * @version OrderStatePersister.java, v 0.1 2018/4/4 下午10:59
 */
public class OrderStatePersister implements StatePersister<OrderVO, OrderStateEnum> {
    /**
     * 更新状态
     *
     * @param data      业务数据
     * @param fromState 原始状态
     * @param toState   目标状态
     * @return 更新结果 boolean
     * @throws StatePersisterException the state persister exception
     */
    @Override
    public boolean updateState(OrderVO data, OrderStateEnum fromState, OrderStateEnum toState) throws StatePersisterException {
        System.out.println(data.getName() + ": update db success");
        return true;
    }
}
