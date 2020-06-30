package com.example.demo17;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

public class IndexActivity extends FragmentActivity implements Callbacks{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
    }
//
    @Override
    public void onItemSelected(int id, Bundle bundle) {
        Intent intent;
        switch (id){
            case 0:
                intent = new Intent(this,Viewitem_Activity.class);
                intent.putExtra("url","");
                startActivity(intent);
        }
    }
}
