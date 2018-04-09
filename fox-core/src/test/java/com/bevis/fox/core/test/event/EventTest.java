package com.bevis.fox.core.test.event;

import com.bevis.fox.core.context.FoxContext;
import com.bevis.fox.core.test.BaseTest;
import com.bevis.fox.core.utils.DateUtils;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;

/**
 * The type Event test.
 */
public class EventTest extends BaseTest {
    /**
     * The Fox context 1.
     */
    @Resource
    private FoxContext foxContext1;

    /**
     * Event test.
     */
    @Test
    public void eventTest() {
        //Date dt = DateUtils.parse("2012-09-11", "yyyy-MM-dd");
        FoxContext foxContext = super.context.getBean("foxContext", FoxContext.class);
        //foxContext.publishEvent(new LogEvent(foxContext, "b"));
        foxContext.publishAsyncEvent(new LogEvent(foxContext, "a"));
        System.out.println("c");
    }
}
