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
        Dialog dialog=new Dialog(context);
        dialog.setContentView(R.layout.dialog_wait);
        int width=(int)(0.8*DensityUtils.getScreenW(context));
        dialog.getWindow().setLayout(width, WindowManager.LayoutParams.WRAP_CONTENT);
        TextView t_msg= (TextView) dialog.findViewById(R.id.dialog_wait_t);
        if(msg==null||msg.length()==0){
            t_msg.setText("");
        }else{
            t_msg.setText(msg);
        }
        return dialog;
    }
}
