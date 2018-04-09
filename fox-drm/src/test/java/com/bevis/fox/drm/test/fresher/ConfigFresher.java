package com.bevis.fox.drm.test.fresher;

import com.bevis.fox.drm.annotation.DrmConfig;
import com.bevis.fox.drm.core.DrmContext;
import com.bevis.fox.drm.core.DrmFresher;
import org.springframework.stereotype.Service;

/**
 * The type Config fresher.
 *
 * @author bevis
 * @version ConfigFresher.java, v 0.1 2018/4/6 上午10:46
 */
@Service
@DrmConfig(root = "bevis", domain = "drm", name = "config")
public class ConfigFresher implements DrmFresher {
    /**
     * Refresh.
     *
     * @param context the context
     */
    @Override
    public void refresh(DrmContext context) {
        System.out.println("【config】path=" + context.getPath() + " changed");
        System.out.println(context);
    }
}