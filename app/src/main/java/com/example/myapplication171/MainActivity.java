package com.example.myapplication171;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textview);
        new AsyncTask(){
            @Override
            protected void onProgressUpdate(Object[] values) {
                super.onProgressUpdate(values);
                textView.setText(values[0].toString());
            }

            @Override
            protected Object doInBackground(Object[] objects) {
                try {
                    Socket socket = new Socket("192.168.3.56",30000);

                    InputStream inputStream = socket.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                    String line = br.readLine();
                    publishProgress(line);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }
    public void test(View view){

    }
}
