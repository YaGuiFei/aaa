package com.example.demo15;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    Bitmap bitmap;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg.what == 0x123){
                    imageView.setImageBitmap(bitmap);
                }
            }
        };

        new Thread(){
            @Override
            public void run() {
                try {
                    URL url = new URL("http://www.gxstnu.edu.cn/__local/C/24/C3/" +
                            "E1DC4699E3764354F097E87FE83_2833970C_22128.jpg");
                    //打开输入流
                    InputStream is = url.openStream();
                    //解析图片
                    bitmap = BitmapFactory.decodeStream(is);
                    //通知UI线程显示图片
                    handler.sendEmptyMessage(0x123);
                    is.close();
                    is = url.openStream();
                    OutputStream os = openFileOutput("gks.png",MODE_PRIVATE);
                    byte[] buff = new byte[1024];
                    int lens;
                    //将URL对应的资源下载到本地
                    while ((lens = is.read(buff))>0){
                        System.out.println(lens);
                        os.write(buff,0,lens);
                    }
                    is.close();
                    os.close();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
