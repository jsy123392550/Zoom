package android.BB.util;

import android.content.Context;
import android.util.DisplayMetrics;

public class ImageUtil {
    private static final int RATE_HEIGHT=7;
    public static int getGridImageHeight(Context context){
        DisplayMetrics dm=context.getResources().getDisplayMetrics();
        return dm.heightPixels/RATE_HEIGHT;
    }
}
