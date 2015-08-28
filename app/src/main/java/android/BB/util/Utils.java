package android.BB.util;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;

public class Utils {
    public static void hideSoftInput(Context context){
        InputMethodManager im= (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        im.toggleSoftInput(0,InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
