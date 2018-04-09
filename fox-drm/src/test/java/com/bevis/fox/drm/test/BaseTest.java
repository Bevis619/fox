package com.bevis.fox.drm.test;

import com.bevis.fox.drm.annotation.DrmConfig;
import com.bevis.fox.drm.core.DrmContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * The type Base test.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:META-INF/spring/*.xml", "classpath*:META-INF/*.xml"})
public class BaseTest extends AbstractJUnit4SpringContextTests {
    @Test
    public void init() throws IOException {

        System.in.read();
    }
}
