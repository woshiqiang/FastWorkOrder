package com.hbck.fastworkorder.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * drawableLeft与文本一起居中显示
 * 
 * @author 农民伯伯
 * @see http://www.cnblogs.com/over140/p/3464348.html
 * 
 */
public class DrawableCenterEditText extends EditText {

    public DrawableCenterEditText(Context context, AttributeSet attrs,
                                  int defStyle) {
        super(context, attrs, defStyle);
    }

    public DrawableCenterEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawableCenterEditText(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable[] drawables = getCompoundDrawables();
        if (drawables != null) {
            Drawable drawableLeft = drawables[0];
            if (drawableLeft != null) {
                float textWidth = getPaint().measureText(getText().toString());
                if (TextUtils.isEmpty(getText().toString())){
                    textWidth = getPaint().measureText(getHint().toString());
                }
                int drawablePadding = getCompoundDrawablePadding();
                int drawableWidth = 0;
                drawableWidth = drawableLeft.getIntrinsicWidth();
                float bodyWidth = textWidth + drawableWidth + drawablePadding;
                canvas.translate((getWidth() - bodyWidth) / 2, 0);
            }
        }
        super.onDraw(canvas);
    }
}