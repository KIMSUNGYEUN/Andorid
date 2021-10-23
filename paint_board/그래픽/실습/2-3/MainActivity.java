package com.example.paint_board;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SingleTouchView penView;
    private Path mPath;
    private Paint mPaint;
    private int penColor;
    private int penWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        penView = new MainActivity.SingleTouchView(this);                       // Custom View 생성
        setContentView(R.layout.activity_main);

        FrameLayout frm_layout = (FrameLayout) findViewById(R.id.main_frame);
        frm_layout.addView(penView);
    }

    public class SingleTouchView extends View {

        public SingleTouchView(Context context) {
            super(context);

            penColor = Color.BLACK;
            penWidth = 4;
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setColor(penColor);
            mPaint.setStrokeWidth(penWidth);
            mPath = new Path();     // Path 설정
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawPath(mPath, mPaint);   // Path 그리기
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX();
            float y = event.getY();
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    touch_start(x, y);
                    break;
                case MotionEvent.ACTION_MOVE:
                    touch_move(x, y);
                    break;
                case MotionEvent.ACTION_UP:
                    touch_up(x, y);
                    break;
            }
            invalidate();
            return true;
        }  // End of onTouchEvent()

        private float mX, mY;
        private static final float TOUCH_TOLERANCE=10;

        private void touch_start(float x, float y) {
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setColor(penColor);
            mPaint.setStrokeWidth(penWidth);

            mPath.reset();
            mPath.moveTo(x, y);      // 시작 점 좌표
            mX = x;
            mY = y;
        }
        private void touch_move(float x, float y) {
            // 부드러운 곡선 그리기
            float dx = Math.abs(x - mX);
            float dy = Math.abs(y - mY);
            if (dx >= TOUCH_TOLERANCE ||
                    dy >= TOUCH_TOLERANCE) {
                mPath.quadTo(mX, mY, (x + mX)/2,
                        (y + mY)/2);
                mX = x;
                mY = y;
            }
        }

        private void touch_up(float x, float y) {
            mPath.lineTo(mX, mY);
        }
    }     //End of  SIngleTouchView Class
}  //End of  ActivityMain
