package com.yj.baselibrary.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.AttributeSet;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import com.yj.baselibrary.R;
@SuppressLint("AppCompatCustomView")
public class ArcImageView extends ImageView {
    /*
     *弧形高度
     */
    private int mArcHeight;
    private static final String TAG = "ArcImageView";

    public ArcImageView(Context context) {
        this(context, null);
    }

    public ArcImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArcImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ArcImageView);
        mArcHeight = typedArray.getDimensionPixelSize(R.styleable.ArcImageView_arcHeight, 0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Path path = new Path();
        path.moveTo(0, 0);
//        path.lineTo(0, getHeight());
        path.lineTo(0, getHeight() -  mArcHeight);
//        path.quadTo(getWidth() / 2, getHeight() - 2 * mArcHeight, getWidth(), getHeight());
        path.quadTo(getWidth() / 2, getHeight(), getWidth(), getHeight()- mArcHeight);
        path.lineTo(getWidth(), 0);
        path.close();
        canvas.clipPath(path);
        super.onDraw(canvas);
    }
}