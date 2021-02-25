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
 * @date:2015/9/14
 * @author:dongxiaogang
 * @description: 自定义圆形进度条
 */
public class RoundProgress extends View {
    private Paint paint = new Paint();

    private int roundColor;
    private int roundProgressColor;
    private int textColor;
    private float textSize;
    private float roundWidth;
    private int max = 100;

    private int progress = 50;

    public RoundProgress(Context context) {
        this(context, null);
    }

    public RoundProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RoundProgress);
        //圆环的颜色
        roundColor = ta.getColor(R.styleable.RoundProgress_roundColor, Color.RED);
        //圆环进度的颜色
        roundProgressColor = ta.getColor(R.styleable.RoundProgress_roundProgressColor, Color.GREEN);
        //中间进度百分比文字字符串的颜色
        textColor = ta.getColor(R.styleable.RoundProgress_textColor, Color.GREEN);
        //中间进度百分比的字符串的字体大小
        textSize = ta.getDimension(R.styleable.RoundProgress_textSize, 15);
        //圆环的宽度
        roundWidth = ta.getDimension(R.styleable.RoundProgress_roundWidth, 5);
        ta.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
//第一步：绘制一个最外层的圆
        paint.setColor(roundColor);
        paint.setStrokeWidth(roundWidth);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        int center = getWidth() / 2;
        int radius = (int) (center - roundWidth / 2);
        canvas.drawCircle(center, center, radius, paint);
        //第二步：绘制正中间的文本
        float textWidth = paint.measureText(progress + "%");
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        paint.setStrokeWidth(0);
        canvas.drawText(progress + "%", center - textWidth / 2, center + textSize / 2, paint);

        //第三步：
        /**
         * 参数解释：
         * oval：绘制弧形圈所包含的矩形范围轮廓
         * 0：开始的角度
         * 360 * progress / max：扫描过的角度
         * false：是否包含圆心
         * paint：绘制弧形时候的画笔
         */
        RectF oval = new RectF(center - radius, center - radius, center + radius, center + radius);
        paint.setColor(roundProgressColor);
        paint.setStrokeWidth(roundWidth);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(oval, 0, 360 * progress / max, false, paint);
    }
    public void setProgress(int progress){
        this.progress = progress;
        if(progress>100){
            this.progress = 100;
        }
        postInvalidate();
    }
}
