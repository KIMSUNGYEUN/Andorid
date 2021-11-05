public class MainActivity extends AppCompatActivity {
  protected class MyView extends View {
    int x=100, y=100;
    String str;
    public MyView(Context context) {
      super(context);
      setBackgroundColor(Color.YELLOW);
    }
    @Override
    protected void onDraw(Canvas canvas) {
      Paint paint = new Paint();
      paint.setColor(Color.MAGENTA);
      canvas.drawRect(x, y, x+100, y+100, paint);
      paint.setTextSize(50);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
      x = (int) event.getX();
      y = (int) event.getY();
      invalidate();
      return true;
    }
  }
  
   @Override
   protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    MyView w = new MyView(this);
    setContentView(w);
   }
}
    
