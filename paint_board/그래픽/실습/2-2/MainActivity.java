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
    int curShape = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        penView = new SingleTouchView(this);
        FrameLayout frm_layout = (FrameLayout) findViewById(R.id.main_frame);
        frm_layout.addView(penView);
    }

    public class SingleTouchView extends View{
        int startX = -1, stopX = -1, startY = -1, stopY = -1;

        public SingleTouchView(Context context)
        {
            super(context);

            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(4);
        }
        @Override
        protected void onDraw(Canvas canvas){
            for (MyShape p : Shapelist) {
                switch (p.Stype){
                    case 1:
                        mPaint.setColor(Color.RED);
                        canvas.drawRect(p.startX,p.startY,p.stopX,p.stopY,mPaint);
                        break;

                    case 2:
                        mPaint.setColor(Color.BLUE);
                        int radius = (int) Math.sqrt(Math.pow(p.stopX-p.startX,2)+Math.pow(p.stopY-p.startY,2));
                        canvas.drawCircle(p.startX,p.startY,radius,mPaint);
                        break;
                }
            }
        }
        public MyShape shapeobj;
        public boolean onTouchEvent(MotionEvent event)
        {
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    startX = (int) event.getX();
                    startY = (int) event.getY();
                    shapeobj = new MyShape();                                                 // 도형 정보 저장 객체 생성
                    shapeobj.startX = startX;   shapeobj.startY = startY;            // 도형 정보 저장
                    shapeobj.Stype = curShape;
                    break;
                case MotionEvent.ACTION_MOVE:
                case MotionEvent.ACTION_UP:
                    stopX = (int)event.getX();
                    stopY = (int)event.getY();
                    shapeobj.stopX = stopX;   shapeobj.stopY = stopY;
                    Shapelist.add(shapeobj);
                    invalidate();
                    break;
            }
            return true;
        }
        public void onErase() {
            Shapelist.clear();     // 리스트 초기화
            invalidate();
        }
    }

    public void mOnClicked(View v)
    {
        switch (v.getId()){
            case R.id.button1:
                curShape = 1;
                break;

            case R.id.button2:
                curShape = 2;
                break;

            case  R.id.button3:
                penView.onErase();
                break;

            default:
                break;
        }
    }

    private ArrayList<MyShape> Shapelist = new ArrayList<MyShape>();

    private class MyShape{
        int Stype;
        int startX;
        int startY;
        int stopX;
        int stopY;
    }
}
