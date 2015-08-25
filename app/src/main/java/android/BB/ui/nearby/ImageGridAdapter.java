package android.BB.ui.nearby;

import android.BB.util.ImageUtil;
import android.BB.widget.NoScrollGridView;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import org.kymjs.kjframe.KJBitmap;

import app.BB.R;

public class ImageGridAdapter extends BaseAdapter{
    private String imgs[];
    private Context context;
    private LayoutInflater layoutInflater;
    private ViewGroup.LayoutParams layoutParams;
    private TouchDimImage touchDimImage;
    private KJBitmap kjBitmap;
    public ImageGridAdapter(Context context,String imgs[]){
        this.context=context;
        this.imgs=imgs;
        layoutInflater=LayoutInflater.from(context);
        layoutParams=new GridView.LayoutParams(-1, ImageUtil.getGridImageHeight(context));
        touchDimImage=new TouchDimImage();
        kjBitmap=new KJBitmap();
    }
    @Override
    public int getCount() {
        return imgs.length;
    }

    @Override
    public Object getItem(int position) {
        return imgs[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.e("BB", "pos:" + position + "  parent:" + parent.getChildCount());
        ViewHolder viewHolder;
        if(convertView==null){
            convertView=layoutInflater.inflate(R.layout.item_nearby_bbdetail_imgs,null);
            viewHolder=new ViewHolder((ImageView) convertView.findViewById(R.id.img_bbdetail_item));
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.imageView.setLayoutParams(layoutParams);
        viewHolder.imageView.setOnTouchListener(touchDimImage);
//        ImageView imageView=(ImageView) convertView.findViewById(R.id.img_bbdetail_item);
//        imageView.setLayoutParams(layoutParams);
        if(((NoScrollGridView)parent).isOnMeasure){
            return convertView;
        }
//        kjBitmap.display(imageView, imgs[position]);
        viewHolder.imageView.setImageBitmap(BitmapFactory.decodeFile(imgs[position]));
        return convertView;
    }
    class TouchDimImage implements View.OnTouchListener{
        private int id;
        public TouchDimImage(){

        }
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int id=event.getAction();
            switch(id){
                case MotionEvent.ACTION_DOWN:
                    ((ImageView)v).setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
                    break;
                case MotionEvent.ACTION_UP:
                    ((ImageView)v).clearColorFilter();
                    break;
                case MotionEvent.ACTION_CANCEL:
                    ((ImageView)v).clearColorFilter();
                    break;
            }
            return true;
        }
    }
    class ViewHolder{
        private ImageView imageView;
        public ViewHolder(ImageView imageView){
            this.imageView=imageView;
        }
    }
}
