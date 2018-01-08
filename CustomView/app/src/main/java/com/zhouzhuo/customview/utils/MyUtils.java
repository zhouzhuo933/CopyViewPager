package com.zhouzhuo.customview.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

/**
 * Created by zhouzhuo on 2018/1/5.
 */

public class MyUtils {
    public static String getProcessName(Context cxt,int pid){
        ActivityManager am = (ActivityManager) cxt.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if(runningApps == null){
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo processInfo :runningApps){
            if(processInfo.pid == pid){
                return processInfo.processName;
            }
        }
        return null;
    }

    public static void close(Closeable closeable){
       if(closeable!=null){
           try {
               closeable.close();
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
    }

    public static DisplayMetrics getScreenMetrics(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm;
    }

    public static void executeInthread(Runnable runnable){
        new Thread(runnable).start();
    }

}
