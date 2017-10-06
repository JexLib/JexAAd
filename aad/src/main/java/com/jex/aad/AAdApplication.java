package com.jex.aad;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.jex.aad.support.slideback.ActivityHelper;
import com.jex.aad.utils.DeviceInformant;
import com.jex.aad.utils.Utils;

import org.greenrobot.eventbus.EventBus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public abstract class AAdApplication extends Application {

    public DeviceInformant deviceInfo;
    public AAdApplicationConfig config;

    protected String processName;

    public static ActivityHelper activityHelper;


    @Override
    public void onCreate() {
        super.onCreate();

        processName = getProcessName(android.os.Process.myPid());

        if (getApplicationContext().getPackageName().equals(processName)) {
            //只在主进程中，初始化一次
            Utils.init(getApplicationContext());

            config = appConfig();
            AAd.app = this;
            AAd.db = DB.getInstance(getApplicationContext());
            AAd.preferences = new Preferences(getSharedPreferences(config.preferencesName, Context.MODE_PRIVATE));
            deviceInfo = new DeviceInformant(getApplicationContext());
            AAd.bus = EventBus.getDefault();

            //开启侧滑支持需要此Helper类支持
            activityHelper = new ActivityHelper();
            registerActivityLifecycleCallbacks(activityHelper);
        }


    }

    protected abstract AAdApplicationConfig appConfig();

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }
}
