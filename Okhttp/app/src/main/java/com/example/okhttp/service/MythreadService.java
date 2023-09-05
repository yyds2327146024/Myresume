package com.example.okhttp.service;

import static android.content.ContentValues.TAG;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemClock;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;

public class MythreadService extends Service {
    public MythreadService() {
//这个是多线程开发，生命周期相关，和网络请求无关


    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: 恭喜你已经初次创建了后台服务");
        //这个是服务创建的时候会执行的方法


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.i(TAG, "onStartCommand: 恭喜你已经创建服务成功啦");
        Vibrator vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);// 获取系统的Vibrator服务
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(5000, 200));// 控制手机振动500ms，振动幅度为200
        }

        int i=0;
        while (i<10){

            i=i+1;
            SystemClock.sleep(100);//这里结合起来就是一共耗时3s进行这个开启界面的执行，差不多是类似于启动软件时访问后端网站确定是否激活成功。



            Log.i(TAG, "输出结果为:"+i);
            System.out.println("输出结果加一");

        }
        //注意循环不能一直达不到return，不然会出现系统报错的情况



        return super.onStartCommand(intent, flags, startId);
        //这个是服务被启动的时候会创建的方法，上面代码应该在return处之前去执行



    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //这个方法是被摧毁的时候可以创建的
    }

    @Override
    public IBinder onBind(Intent intent) {

        //这个方法好像是非要不可得，这是绑定与服务有关联得组件
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}