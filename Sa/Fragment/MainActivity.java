package com.example.home_cctv_;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    A_Fragment a_fragment;
    B_Fragment b_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        a_fragment = (A_Fragment) getSupportFragmentManager().findFragmentById(R.id.a_fragment);
        b_fragment = new B_Fragment();
    }

    public void onFragmentChanged(int index) {
        if(index == 0) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, a_fragment).commit();
        }else if (index == 1){
            getSupportFragmentManager().beginTransaction().replace(R.id.container, b_fragment).commit();
        }
    }
}
