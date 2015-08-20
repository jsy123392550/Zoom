package android.BB.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

public class BBImageView extends RecyclerView{
    public BBImageView(Context context) {
        super(context);
    }

    public BBImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BBImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onDraw(Canvas c) {
        super.onDraw(c);
    }
    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
    }
    //    public BBImageView(Context context) {
//        this(context, null);
//    }
//
//    public BBImageView(Context context, AttributeSet attrs) {
//        this(context, attrs, 0);
//    }
//
//    public BBImageView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//    }
//
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//    }
}
