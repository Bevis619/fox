package com.bevis.fox.drm.core;

import com.bevis.fox.drm.exception.DrmException;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * The type Default drm publisher.
 *
 * @author bevis
 * @version DefaultDrmPublisher.java, v 0.1 2018/4/6 上午9:36
 */
class DefaultDrmPublisher implements DrmPublisher {
    /**
     * 发布资源
     *
     * @param context the context
     * @throws DrmException the drm exception
     */
    @Override
    public void publish(DrmContext context) throws DrmException {
        Assert.notNull(context, "drm context can't be null");
        Assert.hasText(context.getName(), "drm name can't be null or empty");
        try {
            if (StringUtils.isEmpty(context.getData())) {
                DrmFactory.curator().setData().forPath(context.getPath());
            } else {
                DrmFactory.curator().setData().forPath(context.getPath(), context.getData().getBytes());
            }
        } catch (Exception e) {
            throw new DrmException(e.getMessage(), e);
        }
    }
}