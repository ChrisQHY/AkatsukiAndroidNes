package com.akatsuki.nes.framework;


import android.app.Application;

import com.blankj.utilcode.util.Utils;

import com.akatsuki.nes.framework.utils.EmuUtils;
import com.akatsuki.nes.framework.utils.NLog;

abstract public class BaseApplication extends Application {

    private static final String TAG = BaseApplication.class.getName();

    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        boolean debug = EmuUtils.isDebuggable(this);
        NLog.setDebugMode(debug);
    }

    public abstract boolean hasGameMenu();
}
