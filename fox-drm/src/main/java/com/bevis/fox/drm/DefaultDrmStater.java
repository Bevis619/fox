package com.bevis.fox.drm;

import com.bevis.fox.core.context.FoxContext;
import com.bevis.fox.core.context.FoxContextStarted;
import com.bevis.fox.drm.constants.DrmConstants;
import com.bevis.fox.drm.core.DrmFactory;
import com.bevis.fox.drm.core.DrmFresher;
import com.bevis.fox.drm.exception.DrmException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * The type Default drm stater.
 *
 * @author bevis
 * @version DefaultDrmStater.java, v 0.1 2018/4/6 上午12:12
 */
public class DefaultDrmStater implements DrmStarter, FoxContextStarted {
    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultDrmStater.class);

    /**
     * The Fox context.
     */
    private FoxContext foxContext;
    /**
     * The Zk connect string.
     */
    private String zkConnectString;

    /**
     * Sets zk connect string.
     *
     * @param zkConnectString the zk connect string
     */
    public void setZkConnectString(String zkConnectString) {
        this.zkConnectString = zkConnectString;
    }

    /**
     * On started.
     *
     * @param foxContext the fox context
     */
    @Override
    public void onStarted(FoxContext foxContext) {
        this.foxContext = foxContext;
        if (StringUtils.isEmpty(this.zkConnectString)) {
            this.zkConnectString = System.getProperty(DrmConstants.ZK_CONNECT_STRING);
        }

        Assert.hasText(this.zkConnectString, "zookeeper connect string can't be null or empty,please check setting or property file");
        this.start(this.zkConnectString);
    }

    /**
     * Order int.
     *
     * @return the int
     */
    @Override
    public int order() {
        return 0;
    }

    /**
     * Start.
     *
     * @param zkConnectString the zk connect string
     */
    @Override
    public void start(String zkConnectString) {
        Map<String, DrmFresher> refresherMap = this.foxContext.getSpringContext().getBeansOfType(DrmFresher.class);
        if (null == refresherMap || 0 == refresherMap.size()) {
            return;
        }

        DrmFactory.builder(zkConnectString);

        refresherMap.forEach((k, v) -> {
            try {
                DrmFactory.register().register(v);
            } catch (DrmException e) {
                if (LOGGER.isErrorEnabled()) {
                    LOGGER.error("register fresher error:" + k, e);
                }
            }
        });
    }
}