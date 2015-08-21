package android.BB.ui.nearby;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import org.kymjs.kjframe.KJBitmap;

import app.BB.R;

public class ImageGridAdapter extends BaseAdapter{
    private String imgs[];
    private Context context;
    private KJBitmap kjBitmap;
    private LayoutInflater layoutInflater;
    public ImageGridAdapter(Context context,String imgs[]){
        this.context=context;
        this.imgs=imgs;
        kjBitmap=new KJBitmap();
        layoutInflater=LayoutInflater.from(context);
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
        ViewHolder viewHolder;
        if(convertView==null){
            convertView=layoutInflater.inflate(R.layout.item_nearby_bbdetail_imgs,null);
            viewHolder=new ViewHolder((ImageView) convertView.findViewById(R.id.img_bbdetail_item));
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
//        kjBitmap.display(viewHolder.imageView,imgs[position]);
        viewHolder.imageView.setImageBitmap(BitmapFactory.decodeFile(imgs[position]));
        return convertView;
    }
    class ViewHolder{
        private ImageView imageView;
        public ViewHolder(ImageView imageView){
            this.imageView=imageView;
        }
    }
}
