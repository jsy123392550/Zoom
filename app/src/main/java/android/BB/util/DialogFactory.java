package android.BB.util;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.TextView;


import android.BB.R;

public class DialogFactory {
    public static Dialog createWaitDialog(Context context,String msg){
        Dialog dialog=new Dialog(context,R.style.WaitDialog);
        dialog.setContentView(R.layout.dialog_wait);
        DisplayMetrics dm=context.getResources().getDisplayMetrics();
        dialog.getWindow().setLayout(dm.widthPixels/2, (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,80,dm));
        TextView tv_msg= (TextView) dialog.findViewById(R.id.tv_dialog_wait);
        if(msg==null||msg.length()==0){
            tv_msg.setText("");
        }else{
            tv_msg.setText(msg);
        }
        return dialog;
    }
    public static Dialog createEditDialog(Context context,String msg){
        Dialog dialog=new Dialog(context,R.style.EditDialog);
        dialog.setContentView(R.layout.dialog_edit);
        DisplayMetrics dm=context.getResources().getDisplayMetrics();
        dialog.getWindow().setLayout(dm.widthPixels-200,dm.heightPixels/6);
        TextView tv_msg= (TextView) dialog.findViewById(R.id.tv_dialog_edit);
        if(msg==null||msg.length()==0){
            tv_msg.setText("");
        }else{
            tv_msg.setText(msg);
        }
        return dialog;
    }
}
