package com.example.demo17;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    EditText editTextname;
    EditText editTextpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextname = findViewById(R.id.editText);
        editTextpass = findViewById(R.id.editText2);
    }

    public void testgetRequest(View view) throws ExecutionException, InterruptedException {
        System.out.println(OkHttpUtil.getRequest("https://www.baidu.com"));
    }

    public void login(View view) throws ExecutionException, InterruptedException {
        String name = editTextname.getText().toString();
        String pass = editTextpass.getText().toString();
        Map<String,String> map = new HashMap<>();
        map.put("username",name);
        map.put("userpass",pass);
        String url = "http://172.18.85.254:8080/auction/api/users/login";
        String str = OkHttpUtil.postRequest(url,map);
        if (str.equals("1")){
            //Intent intent = new Intent()
            Toast.makeText(this,"登录成功",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this,"登录失败",Toast.LENGTH_LONG).show();
        }

    }
}
