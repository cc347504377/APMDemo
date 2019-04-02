package com.example.apmdemo.block;

import android.content.Context;
import android.util.Log;

import com.example.apmdemo.block.core.BlockMonitorManager;
import com.example.apmdemo.block.core.OnBlockInfoUpdateListener;
import com.example.apmdemo.block.info.BlockInfo;

/**
 * Created by whr on 2019/3/5.
 */
public class BlockTraceManager {

    private static final String TAG = "BlockTraceManager";

    public static void startBlockTace(Context context) {
        BlockMonitorManager.getInstance().start(context);
        BlockMonitorManager.getInstance().setOnBlockInfoUpdateListener(new OnBlockInfoUpdateListener() {
            @Override
            public void onBlockInfoUpdate(BlockInfo blockInfo) {
                Log.e(TAG, "onBlockInfoUpdate: " + blockInfo.concernStackString
                +"\r"+blockInfo.toString());
            }
        });
    }
}
