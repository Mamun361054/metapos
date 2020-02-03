package com.metamorphosis.metapos.Activity.Application;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;


public class MyApplication extends Application {

    public static final String TAG = "MyApplication";
    private static Context context;

    public static Context getAppContext() {
        return MyApplication.context;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        MyApplication.context = getApplicationContext();
        FlowManager.init(new FlowConfig.Builder(this).build());
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());


    }
}
