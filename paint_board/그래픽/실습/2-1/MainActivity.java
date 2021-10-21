package com.example.paint_board;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    SingleTouchView penView;
    private Path mPath;
    private Paint mPaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        penView = new SingleTouchView(this);
        setContentView(penView);
    }

    public class SingleTouchView extends View{
        public SingleTouchView(Context context)
        {
            super(context);
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setColor(Color.BLUE);
            mPaint.setStrokeWidth(4);
        }
        @Override
        protected void onDraw(Canvas canvas) {

            mPaint.setColor(Color.YELLOW); // 얼굴 색상을 지정
            mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            canvas.drawCircle(220, 250, 200, mPaint); // 원의중심 x,y, 반지름,paint

            mPaint.setColor(Color.BLUE); // 선 색상을 지정
            mPaint.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(150, 150, 40, mPaint); // 원의 중심 x,y, 반지름,paint

            mPath = new Path();
            mPath.moveTo(260, 200); // 자취 시작점 이동
            mPath.lineTo(260+40, 200-60); // 자취 직선
            mPath.lineTo(260+80, 200); // 자취 직선
            canvas.drawPath(mPath, mPaint);

            canvas.drawOval(210,210,210+20,210+80, mPaint);  // 타원 그리기

            // path 자취 만들기
            mPaint.setColor(Color.RED); // 색상을 지정
            mPath = new Path();
            mPath.moveTo(110, 340+50); // 자취 시작점 이동
            mPath.lineTo(110+50, 340); // 자취 직선
            mPath.lineTo(110+100, 340+50); // 자취 직선
            mPath.lineTo(110+150, 340); // 자취 직선
            mPath.lineTo(110+200, 340+50); // 자취 직선
            canvas.drawPath(mPath, mPaint);
        }
    }
}
