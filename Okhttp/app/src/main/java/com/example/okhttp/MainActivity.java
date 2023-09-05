package com.example.okhttp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.okhttp.net.Callb;
import com.example.okhttp.net.OkHttp;
import com.example.okhttp.service.MythreadService;

import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView TextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
        //上面是调用两个类的实例方法进行组件对象的实例化，便于进行后续的代码维护，然后后面是打算在子线程里面进行ui线程的更新和修改
        //预计调用子线程里的方法进行，后台的计时或者是计数然后视情况对ui线程进行更新。
        //下面是实现服务启动
        Intent intent=new Intent(MainActivity.this, MythreadService.class);
        startService(intent);//启动服务和启动类的类似的，
        //首次启动才会创建这个服务






    }

    private void initEvent() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //到这步创建一个工具类使用网络接口，因为可能后面很多都需要用这个网络工具
                //下面new 一个接口的操作是当传入成功和失败分别要做的事,Callb是用来包装你要做的哪些事
                //这部分学的是网络接口后面准备学习多线程相关开始主要还是利用handler进行数据传输实现子线程更行ui线程的操作。

                OkHttp.getInstanc().doGet("http://www.weather.com.cn/data/sk/101010100.html", new Callb() {
                    @Override
                    public void onSuccess(String result) {
                        TextView.setText(result);

                    }

                    @Override
                    public void onError(Exception e) {
                        TextView.setText(e.toString());


                    }
                });

            }
        });
    }

    private void initView() {
        button=findViewById(R.id.button);
        TextView=findViewById(R.id.TextView);
    }
}