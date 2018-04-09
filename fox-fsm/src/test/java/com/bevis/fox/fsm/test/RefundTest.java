package com.bevis.fox.fsm.test;

import com.bevis.fox.fsm.core.StateDelegate;
import com.bevis.fox.fsm.exception.StateException;
import com.bevis.fox.fsm.test.enums.BizTypeEnum;
import com.bevis.fox.fsm.test.enums.RefundStateEnum;
import com.bevis.fox.fsm.test.vo.RefundVO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * The type Refund test.
 *
 * @author bevis
 * @version RefundTest.java, v 0.1 2018/4/5 上午1:21
 */
public class RefundTest extends BaseTest {
    /**
     * The Refund state delegate.
     */
    @Autowired
    private StateDelegate<RefundVO, String, RefundStateEnum> refundStateDelegate;

    /**
     * Test 1.
     */
    @Test
    public void test1() {
        RefundVO refundVO = new RefundVO();
        refundVO.setName("refund");
        try {
            this.refundStateDelegate.setState(refundVO, BizTypeEnum.REFUND.name(), RefundStateEnum.HNADING, RefundStateEnum.CANCELED);
        } catch (StateException e) {
            e.printStackTrace();
        }
    }
}
