package listview.tianhetbm.p2p.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import listview.tianhetbm.p2p.R;

/**
 * @date:2020/12/9
 * @author:dongxiaogang
 * @description: 仿照QQ计步器
 */
public class QQStepView extends View{
    private int mOutColor= Color.BLUE;
    private int mInnerColor=Color.RED;
    private Paint mOutPaint;
    private Paint mInnerPaint;
    private Paint mTextPaint;
    private int mBorderWidth=10;
    private int mTextSize=15;

    public QQStepView(Context context) {
        this(context,null);
    }

    public QQStepView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public QQStepView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context,AttributeSet attrs) {
        TypedArray array=context.obtainStyledAttributes(attrs,R.styleable.QQStepView);
        mOutColor=array.getColor(R.styleable.QQStepView_mOutColor,mOutColor);
        mInnerColor=array.getColor(R.styleable.QQStepView_mInnerColor,mInnerColor);
        mBorderWidth= (int) array.getDimension(R.styleable.QQStepView_mBorderWidth,mBorderWidth);
        mTextSize=array.getDimensionPixelSize(R.styleable.QQStepView_mTextSize,mTextSize);

        array.recycle();


        mOutPaint=new Paint();
        mOutPaint.setColor(mOutColor);
        mOutPaint.setAntiAlias(true);
        mOutPaint.setStrokeWidth(mBorderWidth);
        mOutPaint.setStyle(Paint.Style.STROKE);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width=MeasureSpec.getSize(widthMeasureSpec);
        int height=MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width>height?height:width,width>height?height:width);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int center=getWidth()/2;
        int raduis=center-mBorderWidth;
        RectF rectf=new RectF(center-raduis,center-raduis,center+raduis,center+raduis);

        canvas.drawArc(rectf,135,270,false,mOutPaint);

    }
}
