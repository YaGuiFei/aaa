package com.example.demo17;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.example.demo17.dummy.DummyContent;

public class Viewitem_Activity extends FragmentActivity implements ItemFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewitem_);
        getSupportFragmentManager().beginTransaction()
                       .replace(R.id.fragement,ItemFragment.newInstance(1))
                       .commit();
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
