package ssafy.d102.pyunny.customView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.d102.pyunny.R;

@SuppressLint("AppCompatCustomView")
public class CustomTextOutLineView extends TextView {
    private boolean stroke = false;
    private float strokeWidth = 0.0f;
    private int strokeColor;


    public CustomTextOutLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context,attrs);
    }

    public CustomTextOutLineView(Context context) {
        super(context);
    }

    public CustomTextOutLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context,attrs);
    }

    private void initView(Context context, AttributeSet attrs){
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomTextOutLineView);
        stroke = a.getBoolean(R.styleable.CustomTextOutLineView_textStroke,false);
        strokeWidth = a.getFloat(R.styleable.CustomTextOutLineView_textStrokeWidth,0.0f);
        strokeColor = a.getColor(R.styleable.CustomTextOutLineView_textStrokeColor,0xffffffff);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(stroke){
            ColorStateList stateList = getTextColors();
            getPaint().setStyle(Paint.Style.STROKE);
            getPaint().setStrokeWidth(strokeWidth);
            setTextColor(strokeColor);
            super.onDraw(canvas);
            getPaint().setStyle(Paint.Style.FILL);
            setTextColor(stateList);
        }
        super.onDraw(canvas);
    }
}
