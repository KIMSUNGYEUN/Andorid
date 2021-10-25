protected void onCreate(Bundle savedInstanceState) {
        ……….
        redBtn = (Button) findViewById(R.id.red);
        blackBtn = (Button) findViewById(R.id.black);
        blueBtn = (Button) findViewById(R.id.blue);
        greenBtn = (Button) findViewById(R.id.green);
        rGroup = (RadioGroup)findViewById(R.id.widList);
        rGroup.check(R.id.wid2);
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
