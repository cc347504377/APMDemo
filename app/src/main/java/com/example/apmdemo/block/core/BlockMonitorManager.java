package com.example.apmdemo.block.core;

import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;

import com.example.apmdemo.block.info.BlockInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @desc: 卡顿检测管理类
 */
public class BlockMonitorManager {
    private static final String TAG = "BlockMonitorManager";
    private static final int MAX_SIZE = 50;


    private static class Holder {
        private static BlockMonitorManager INSTANCE = new BlockMonitorManager();
    }

    private boolean mIsRunning;
    private MonitorCore mMonitorCore;
    private Context mContext;
    private List<BlockInfo> mBlockInfoList = Collections.synchronizedList(new ArrayList<BlockInfo>());
    private OnBlockInfoUpdateListener mOnBlockInfoUpdateListener;


    public static BlockMonitorManager getInstance() {
        return BlockMonitorManager.Holder.INSTANCE;
    }

    public void start(Context context) {
        if (mIsRunning) {
            Log.i(TAG, "start when manager is running");
            return;
        }
        if (context == null) {
            Log.e(TAG, "start fail, context is null");
            return;
        }
        mContext = context.getApplicationContext();
        if (mMonitorCore == null) {
            mMonitorCore = new MonitorCore();
        }
        mIsRunning = true;
        Looper.getMainLooper().setMessageLogging(mMonitorCore);
    }

    public boolean isRunning() {
        return mIsRunning;
    }

    public void stop() {
        if (!mIsRunning) {
            Log.i(TAG, "stop when manager is not running");
            return;
        }
        Looper.getMainLooper().setMessageLogging(null);
        if (mMonitorCore != null) {
            mMonitorCore.shutDown();
            mMonitorCore = null;
        }
//        NotificationUtils.cancelNotification(mContext, NotificationUtils.ID_SHOW_BLOCK_NOTIFICATION);
        mIsRunning = false;
        mContext = null;
    }

    public void setOnBlockInfoUpdateListener(OnBlockInfoUpdateListener onBlockInfoUpdateListener) {
        mOnBlockInfoUpdateListener = onBlockInfoUpdateListener;
    }

    public void notifyBlockEvent(BlockInfo blockInfo) {
        blockInfo.concernStackString = BlockCanaryUtils.concernStackString(mContext, blockInfo);
        blockInfo.time = System.currentTimeMillis();
        if (!TextUtils.isEmpty(blockInfo.concernStackString)) {
            showNotification(blockInfo);
            if (mBlockInfoList.size() > MAX_SIZE) {
                mBlockInfoList.remove(0);
            }
            mBlockInfoList.add(blockInfo);
            if (mOnBlockInfoUpdateListener != null) {
                mOnBlockInfoUpdateListener.onBlockInfoUpdate(blockInfo);
            }
        }

    }

    private void showNotification(BlockInfo info) {
//        String contentTitle = mContext.getString(R.string.dk_block_class_has_blocked, info.timeStart);
//        String contentText = mContext.getString(R.string.dk_block_notification_message);
//        Intent intent = new Intent(mContext, UniversalActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        intent.putExtra(BundleKey.FRAGMENT_INDEX, FragmentIndex.FRAGMENT_BLOCK_MONITOR);
//        intent.putExtra(BlockMonitorFragment.KEY_JUMP_TO_LIST, true);
//        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 1, intent, FLAG_UPDATE_CURRENT);
//        NotificationUtils.setInfoNotification(mContext, NotificationUtils.ID_SHOW_BLOCK_NOTIFICATION,
//                contentTitle, contentText, contentText, pendingIntent);
    }

    public List<BlockInfo> getBlockInfoList() {
        return mBlockInfoList;
    }
}