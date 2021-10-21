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
    
    // 그림 영역 View 클래스 정의
    public class SingleTouchView extends View {
        public SingleTouchView(Context context)
        {
            super(context);
            mPaint = new Paint();             // Paint  생성
            mPaint.setAntiAlias(true);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setColor(Color.BLUE); // 색상을 지정
            mPaint.setStrokeWidth(4);      // 선의 굵기
        }
        @Override
        protected void onDraw(Canvas canvas) {
            // 사각형의 좌상,우하 좌표
            canvas.drawRect(100,100,200,200,mPaint);
           // 원의중심 x,y, 반지름
            canvas.drawCircle(300, 150, 40, mPaint); 
           //타원의 왼쪽Top, ,오른쪽 Bottom
            canvas.drawOval(400, 100, 600, 200, mPaint); 

            // path 자취 만들기
            mPaint.setColor(Color.RED); // 선 색상을 지정
            mPath = new Path();

            mPath.moveTo(100, 290); // 자취  시작점 이동
            mPath.lineTo(100+50, 290+50); // 자취 직선
            mPath.lineTo(100+100, 290); // 자취 직선
            mPath.lineTo(100+150, 290+50); // 자취 직선
            mPath.lineTo(100+200, 290); // 자취 직선
            canvas.drawPath(mPath, mPaint);
        }
    }
