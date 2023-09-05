package com.example.okhttp.net;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.logging.ConsoleHandler;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttp {
    //单例模式：全局只有一个这个类不能被乱继承更改
    //异步还要定义一个接口实现操作Callb
    private OkHttp(){};

    private OkHttpClient client = new OkHttpClient();
    private Handler handler=new Handler();//这个handler要用小范围的，将os的改为小范围内handler


    private static OkHttp instanc=new OkHttp();//这两句都是静态方法,要用其他类创建对象，获得实例并使得你要调用的方法为公开
    public static OkHttp getInstanc() {
        return instanc;
    }
    public void doGet(String url,Callb callb){
            Request request = new Request.Builder().url(url).build();
            Call call=client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //在里面填写访问失败的代码
                        callb.onError(e);//这个就是用过接口去回调之前你传入的参数

                    }
                });



            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //在里面填写访问成功的代码
                       String result=null;
                        try {
                            result= response.body().string();//这个response只在这个方法参数内有，且有io操作所以必须用try
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        //最后返回结果在result里面
                        callb.onSuccess(result);

                    }
                });

            }
        });








    }
}
