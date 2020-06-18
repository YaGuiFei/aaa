package com.example.demo13;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText editText;
    ClientThread clientThread;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                //处理子线程发送过来的消息
                if (msg.what == 0x345) {
                    textView.append("\n" + msg.obj.toString());
                    textView.setMovementMethod(ScrollingMovementMethod.getInstance());
                    int textHeight = textView.getLineCount()*textView.getLineHeight();
                    if(textHeight>textView.getHeight()){
                        textView.scrollTo(0,textHeight - textView.getHeight());
                    }
                }
            }
        };

        button = findViewById(R.id.button);
        editText = findViewById(R.id.editText);
        //在客户端启动新线程,来进行网络通信
        clientThread = new ClientThread(handler);
        new Thread(clientThread).start();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message = new Message();
                message.what = 0x123;
                message.obj = editText.getText().toString();
                //发送消息
                clientThread.handler.sendMessage(message);
                editText.setText("");
            }
        });


    }
}
