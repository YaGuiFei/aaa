package com.example.demo16;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        //一、创建客户端
                        OkHttpClient okHttpClient = new OkHttpClient();
                        //二、创建请求
                        Request request = new Request.Builder()
                                .url("http://172.18.88.111:8080/a/a.jsp")
                                .build();
                        //三、客户端调用请求，得到响应
                        try {
                            Response response = okHttpClient.newCall(request).execute();
                            String str = response.body().string();
                            System.out.println(str);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }.start();
            }
        });
    }
    public void postComent(View view){
        new Thread(){
            @Override
            public void run() {
                //一、创建客户端
                OkHttpClient okHttpClient = new OkHttpClient();

                FormBody formBody = new FormBody.Builder()
                        .add("name","sjxy")
                        .add("pass","123456")
                        .build();

                //二、创建请求
                Request request = new Request.Builder()
                        .url("http://172.18.88.111:8080/a/login.jsp")
                        .post(formBody)
                        .build();

                //三、客户端调用请求，得到响应
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    String str = response.body().string();
                    System.out.println(str);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();

    }


}
