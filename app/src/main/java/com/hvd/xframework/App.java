package com.hvd.xframework;


import android.app.Application;

import com.hvd.xutils.utils.XUtils;
import com.hvd.xview.utils.XCustomViews;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        XCustomViews.addTypeFace(XUtils.getTypeface(this, "fonts/Montserrat-Medium.ttf"));
        XCustomViews.addTypeFace(XUtils.getTypeface(this, "fonts/Montserrat-Black.ttf"));
        XCustomViews.addTypeFace(XUtils.getTypeface(this, "fonts/Montserrat-Italic.ttf"));
        XCustomViews.addTypeFace(XUtils.getTypeface(this, "fonts/Montserrat-Light.ttf"));
        XCustomViews.addTypeFace(XUtils.getTypeface(this, "fonts/Montserrat-Regular.ttf"));
    }
}
