package com.example.studydb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        SharedPreferences sd=getSharedPreferences("test", Context.MODE_PRIVATE);//这里只需要重新设置一个与之前文件名相同的Shaarepreference即可
        TextView t2=findViewById(R.id.textView2);
        t2.setText(sd.getString("name","默认值"));//


    }
}