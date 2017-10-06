package com.jex.aad.demo;

import com.jex.aad.AAdApplication;
import com.jex.aad.AAdApplicationConfig;

/**
 * 全局入口
 * Created by Beanu on 16/8/2.
 */
public class DemoApplication extends AAdApplication {
    @Override
    protected AAdApplicationConfig appConfig() {
        return new AAdApplicationConfig();
    }
}
