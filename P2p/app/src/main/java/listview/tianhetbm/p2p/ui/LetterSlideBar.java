package listview.tianhetbm.p2p.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

/**
 * @date:2015/1/16
 * @author:dongxiaogang
 * @description: 自定义字母搜索
 */
public class LetterSlideBar extends View{
    private Paint mPaint;
    private Paint bgPaint;


    private static String[] letters={"!","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","#"};
    private String mCurrentLetter;
    public LetterSlideBar(Context context) {
        this(context,null);
    }

    public LetterSlideBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LetterSlideBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(sp2px(12));
        mPaint.setColor(Color.parseColor("#000000"));

        bgPaint=new Paint();
        bgPaint.setAntiAlias(true);

        bgPaint.setColor(Color.GREEN);

    }

    private float sp2px(int sp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,sp,getResources().getDisplayMetrics());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int textWidth= (int) mPaint.measureText("W")+20;
        int width=getPaddingLeft()+getPaddingRight()+textWidth;
        int height=MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //int x=getPaddingLeft();
        int itemHeight=(getHeight()-getPaddingTop()-getPaddingBottom())/letters.length;
        for(int i=0;i<letters.length;i++){
            int letterCenterY= i*itemHeight+itemHeight/2+getPaddingTop();
            Paint.FontMetrics fontMetrics=mPaint.getFontMetrics();
            int dy= (int) ((fontMetrics.bottom-fontMetrics.top)/2-fontMetrics.bottom);
            int baseLine=letterCenterY+dy;
            int textWidth= (int) mPaint.measureText(letters[i]);
            int x=getWidth()/2-textWidth/2;
            if(letters[i].equals(mCurrentLetter)){

                canvas.drawCircle(getWidth()/2, itemHeight / 2 + i * itemHeight, 20, bgPaint);
                mPaint.setColor(Color.RED);
                canvas.drawText(letters[i],x,baseLine,mPaint);
            }else{
                mPaint.setColor(Color.BLACK);
                canvas.drawText(letters[i],x,baseLine,mPaint);
            }

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                float curentY= event.getY();
                int itemHeight=(getHeight()-getPaddingTop()-getPaddingBottom())/letters.length;
                int currentLetterPostion= (int) (curentY/itemHeight);
                if(currentLetterPostion<0){
                    currentLetterPostion=0;
                }
                if(currentLetterPostion>letters.length-1){
                    currentLetterPostion=letters.length-1;
                }
                //Log.e("测试字母",letters[currentLetterPostion]);
                mCurrentLetter=letters[currentLetterPostion];
                listener.down(mCurrentLetter,true);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                listener.down(mCurrentLetter,false);
                break;
        }
        return true;
    }
    public OnClickListener listener;
    public void setOnClick(OnClickListener listener){
        this.listener=listener;
    }
    public interface OnClickListener{
        void down(String curentLeter,boolean isShow);
    }
    /*设置当前按下的是那个字母*/
    public void setTouchIndex(String word) {
//        for (int i = 0; i < letters.length; i++) {
//            if (letters[i].equals(word)) {
        mCurrentLetter = word;
                invalidate();
                return;
//            }
//        }
    }
}
