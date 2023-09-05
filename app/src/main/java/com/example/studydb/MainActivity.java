package com.example.studydb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sp =getSharedPreferences("test", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();//这个是存储共享的例子实例
        editor.putString("name","jack");
        editor.commit();
        TextView t1=findViewById(R.id.textView);
        Button save=findViewById(R.id.save);
        Button read=findViewById(R.id.read);
        EditText re=findViewById(R.id.editTextTextPersonName);

        Button btn=findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t1.setText(sp.getString("name","默认值").toString());
                Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);


            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text1=re.getText().toString();
                FileOutputStream fos=null;
                try {
                    fos =openFileOutput("wnejian1",MODE_PRIVATE);
                    fos.write(text1.getBytes());

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (fos!=null){
                        try {
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });



    }
}