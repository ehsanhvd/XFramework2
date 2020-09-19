package com.hvd.xutils.utils;

import android.util.Log;

public class XLog {

    private String tag;
    private final boolean LOG_ENABLED = true;

    public XLog(String tag) {
        this.tag = tag;
    }

    public void log(String log) {
        if (LOG_ENABLED) {
            Log.d(tag, log);
        }
    }

}
