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
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SingleTouchView penView;
    private Path mPath;
    private Paint mPaint;
    private int penColor;
    private int penWidth;
    int pcount;

    Button redBtn;
    Button blackBtn;
    Button blueBtn;
    Button greenBtn;
    RadioGroup rGroup;
    Button undoBtn;
    Button redoBtn;
    Button eraseBtn;
    Button pointerBtn;
    Canvas mCanvas;

    private boolean pointerMode = false;
    private ArrayList<Path> pointers = new ArrayList<Path>();         // sub Path  저장 리스트

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        penView = new MainActivity.SingleTouchView(this);
        mCanvas = new Canvas();
        setContentView(R.layout.activity_main);

        FrameLayout frm_layout = (FrameLayout) findViewById(R.id.main_frame);
        frm_layout.addView(penView);

        redBtn = (Button) findViewById(R.id.red);
        blackBtn = (Button) findViewById(R.id.black);
        blueBtn = (Button) findViewById(R.id.blue);
        greenBtn = (Button) findViewById(R.id.green);
        rGroup = (RadioGroup)findViewById(R.id.widList);
        rGroup.check(R.id.wid2);

        undoBtn = (Button) findViewById(R.id.button1);
        undoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                penView.onClickUndo();
            }
        });

        redoBtn = (Button) findViewById(R.id.button2);
        redoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                penView.onClickRedo();
            }
        });

        eraseBtn = (Button) findViewById(R.id.button3);
        eraseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                penView.onClickErase();
            }
        });

        pointerBtn = (Button)findViewById(R.id.pointer);
    }

    public void mOnClicked(View v) {
        switch(v.getId()) {
            case  R.id.red:
                penColor = Color.RED;    break;
            case  R.id.black:
                penColor = Color.BLACK;  break;
            case  R.id.blue:
                penColor = Color.BLUE;  break;
            case  R.id.green:
                penColor = Color.GREEN;  break;
        }
    }

    public void mOnClicked2(View v) {
        switch(rGroup.getCheckedRadioButtonId()) {
            case  R.id.wid1:
                penWidth = 2;  break;
            case  R.id.wid2:
                penWidth = 4;  break;
            case  R.id.wid3:
                penWidth = 6;  break;
            case  R.id.wid4:
                penWidth = 10; break;
            case  R.id.wid5:
                penWidth = 15;  break;
        }
    }

    private ArrayList<Path> paths = new ArrayList<Path>();
    private ArrayList<Paint> paints = new ArrayList<Paint>();
    private ArrayList<Path> undonePaths = new ArrayList<Path>();          // 지운 Path 저장 리스트
    private ArrayList<Paint> undonePaints = new ArrayList<Paint>();       // 지운 Path의 Paint 저장 리스트

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
            int i=0;

            // Path 그리기
            if (pointerMode) {
                if (pointers.size() >= 2 )                                 // 최소 선 길이 : 2 sub Path 크기
                    pointers.remove(0);                                    // 가장 오래 전에 연결된 sub Path 제거

                for (Path p : pointers)
                    canvas.drawPath(p, mPaint);

            } else {
                for (Path p : paths) {                                                  // 이전에 그렸던 선을 그리기
                    Paint eachPaint = paints.get(i);                          // Path에 대응하는 Paint 가져오기
                    canvas.drawPath(p, eachPaint);
                    i = i + 1;
                }

            }
            canvas.drawPath(mPath, mPaint);
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
                    touch_up(x, y);//
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

            if (pointerMode) {
                if (++pcount == 7) {      		// sub Path 크기  결정
                    mPath.lineTo(mX, mY);
                    pointers.add(mPath);                         // sub Path 저장 리스트에 연결

                    mPath = new Path();		// 새 sub Path  생성
                    mPath.reset();
                    mPath.moveTo(mX, mY);
                    pcount = 0;
                }
            }
        }

        private void touch_up(float x, float y) {
            if (pointerMode) {
                mPath.reset();                          // 저장했던 모든 sub Path 제거

                mPath.moveTo(x-30,y);           // 터치를 뗀 최종 위치에  짧은 선(sub Path)  만 남겨둠
                mPath.lineTo(x,y);
                pointers.add(mPath);
            } else {

                mPath.lineTo(mX, mY);
                mCanvas.drawPath(mPath, mPaint);                          // 현재 그린 선 그리기
                paths.add(mPath);                                                     // 현재 그린 선의 Path 저장
                paints.add(mPaint);                                                   // 현재 그린 선의 Paint 저장
            }// 새로 그릴 선의 Path 객체 생성
            mPath = new Path();
        }

        public void onClickUndo () {
            if (paths.size()>0) {
                undonePaths.add(paths.remove(paths.size()-1));                  // 지운 Path 리스트에 연결
                undonePaints.add(paints.remove(paints.size()-1));
                invalidate();
            }
        }

        public void onClickRedo (){
            if (undonePaths.size()>0) {
                paths.add(undonePaths.remove(undonePaths.size()-1));
                paints.add(undonePaints.remove(undonePaints.size()-1));  // 그릴 Path 리스트에 연결
                invalidate();
            } }

        public void onClickErase () {
            while (paths.size()>0 ) {
                undonePaths.add(paths.remove(paths.size()-1));
                undonePaints.add(paints.remove(paints.size()-1));
                invalidate();
            }
        }
    }     //End of  SIngleTouchView Class
    public void mOnClicked3(View v) {                                               // Pointer 버튼 클릭 시
        if (pointerMode == false) {
            pointerMode = true;
            pointerBtn.setBackgroundColor(Color.BLUE);
        } else {
            pointerMode = false;
            pointerBtn.setBackgroundColor(Color.RED);
            pointers.clear();
        }
    }     // End of mOnClicked3()
}  //End of  ActivityMain
