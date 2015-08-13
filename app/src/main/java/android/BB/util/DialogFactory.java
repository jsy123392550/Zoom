package android.BB.util;

import android.app.Dialog;
import android.content.Context;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import org.kymjs.kjframe.utils.DensityUtils;

import app.BB.R;

public class DialogFactory {
    public static Dialog createWaitDialog(Context context,String msg){
        Dialog dialog=new Dialog(context,R.style.WaitDialog);
        dialog.setContentView(R.layout.dialog_wait);
        int width=DensityUtils.getScreenW(context)/2;
        dialog.getWindow().setLayout(width, DensityUtils.dip2px(context,80));
        TextView t_msg= (TextView) dialog.findViewById(R.id.tv_dialog_wait);
        if(msg==null||msg.length()==0){
            t_msg.setText("");
        }else{
            t_msg.setText(msg);
        }
        return dialog;
    }
}
