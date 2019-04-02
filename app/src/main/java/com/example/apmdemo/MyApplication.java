package com.example.apmdemo;

import android.app.Application;
import com.example.apmdemo.block.BlockTraceManager;

/**
 * Created by whr on 2019/2/22.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        BlockTraceManager.startBlockTace(this);
    }
}
