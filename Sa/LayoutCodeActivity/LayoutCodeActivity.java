package com.example.android_app_programing;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LayoutCodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout mainLayout = new LinearLayout(this);// new 연산자로 리니어 레이아웃을 만들고 방향설정
        mainLayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams params =                      //new 연산자로 레이아웃 안에 추가될 뷰들에 설정할 파라미터 생성
                new LinearLayout.LayoutParams(                  
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT 
                );
      
        Button button1 = new Button(this);  // 버튼에 파라미터 설정하고 레이아웃 추가
        button1.setText("Button1");
        button1.setLayoutParams(params);
        mainLayout.addView(button1);

        setContentView(mainLayout); //새로 만든 레이아웃을 화면에 
    }
}
