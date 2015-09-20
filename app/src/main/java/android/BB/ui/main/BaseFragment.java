package android.BB.ui.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.widget.Toast;

import cn.bmob.im.BmobChatManager;
import cn.bmob.im.BmobUserManager;
import cn.bmob.im.util.BmobLog;

/**
 * Created by Kalina on 2015/9/19.
 */
public class BaseFragment extends Fragment {

    public BmobUserManager userManager;
    public BmobChatManager manager;
    public LayoutInflater mInflater;
    Toast mToast;
    public CustomApplication mApplication;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mApplication = CustomApplication.getInstance();
        userManager = BmobUserManager.getInstance(getActivity());
        manager = BmobChatManager.getInstance(getActivity());
        mInflater = LayoutInflater.from(getActivity());
    }


    public BaseFragment() {

    }

    public void ShowToast(String text) {
        if (mToast == null) {
            mToast = Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
        }
        mToast.show();
    }

    public void ShowToast(int text) {
        if (mToast == null) {
            mToast = Toast.makeText(getActivity(), text, Toast.LENGTH_LONG);
        } else {
            mToast.setText(text);
        }
        mToast.show();
    }


    /**
     * 打Log
     * ShowLog
     */
    public void ShowLog(String msg) {
        BmobLog.i(msg);
    }


}
