package android.BB.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

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
}
