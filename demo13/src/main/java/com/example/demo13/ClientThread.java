package com.example.demo13;


import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class ClientThread implements Runnable {
    Handler handler;
    OutputStream os;
    Handler uihandler;
    BufferedReader br;
    public ClientThread(Handler handler) {
        this.uihandler = handler;
    }

    @Override
    public void run() {
        Socket s = null;
        try {
            //创建网络连接
            s = new Socket("172.18.88.111",30011);
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            new Thread(){
                @Override
                public void run() {
                    String content;
                    try {
                        while ((content = br.readLine())!=null){
                            Message message = new Message();
                            message.what = 0x345;
                            message.obj = content;
                            uihandler.sendMessage(message);
                        }
                    }catch (IOException e){
                        e.printStackTrace();
                    }

                }
            }.start();
            //获取输出流
            os = s.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Looper.prepare();
        //创建Handle对象处理UI线程发送过来的消息
        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg.what == 0x123){
                    try {
                        //调用输出流，发字符串发送到服务端
                        os.write((msg.obj.toString()+"\n").getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Looper.loop();
    }
}
