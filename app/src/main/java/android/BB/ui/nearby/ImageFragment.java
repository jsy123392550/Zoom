package android.BB.ui.nearby;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.kymjs.kjframe.KJBitmap;
import org.kymjs.kjframe.bitmap.BitmapCallBack;

import android.BB.R;

public class ImageFragment extends Fragment {
    private static final String IMG_URL = "img_url";
    private KJBitmap kjBitmap;
    private ImageView imageView;
    private ProgressBar progressBar;
    private String imgUrl;
    public static ImageFragment newInstance(String imgUrl) {
        ImageFragment fragment = new ImageFragment();
        Bundle args = new Bundle();
        args.putString(IMG_URL, imgUrl);
        fragment.setArguments(args);
        return fragment;
    }

    public ImageFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            imgUrl = getArguments().getString(IMG_URL);
        }
        kjBitmap=new KJBitmap();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_image_detail, container, false);
        imageView= (ImageView) view.findViewById(R.id.img_image_detail_fragment);
        progressBar= (ProgressBar) view.findViewById(R.id.progressbar_image_detail_fragment);
        kjBitmap.display(imageView, imgUrl, new BitmapCallBack() {
            @Override
            public void onPreLoad() {
                progressBar.setVisibility(ProgressBar.VISIBLE);
            }

            @Override
            public void onSuccess(Bitmap bitmap) {
                progressBar.setVisibility(ProgressBar.GONE);
            }

            @Override
            public void onFailure(Exception e) {
                Toast.makeText(getActivity(),"加载图片失败",Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(ProgressBar.GONE);
            }
        });
        return view;
    }


}
