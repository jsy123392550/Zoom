package android.BB.ui.nearby;

import android.BB.bean.nearby.BBInfo;
import android.BB.finals.MyConstants;
import android.BB.util.AbsRecyclerAdapter;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.kymjs.kjframe.KJBitmap;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import app.BB.R;

public class BBListAdapter extends AbsRecyclerAdapter {
    private BBInfo bbInfo;
    private List<BBInfo> mList;
    private KJBitmap kjBitmap;

    public BBListAdapter(Context context) {
        super(context);
        kjBitmap = new KJBitmap();
        mList = new ArrayList<>();
        File file1 = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + MyConstants.IMAGE_PATH);
        file1.mkdir();
        File file2 = new File(file1,"example.png");
        if (!file2.exists()) {
            try {
                file2.createNewFile();
                FileOutputStream fout = new FileOutputStream(file2);
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fout);
                Log.e("BB", "file:" + file2.getAbsolutePath());
                fout.flush();
                fout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        bbInfo = new BBInfo(1, file2.getAbsolutePath(), "帮忙去二教拿书", "300m", "二教前门门口", "15:56");
        for (int i = 0; i < 10; i++) {
            mList.add(bbInfo);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size() + 1;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        switch (viewType) {
            case VIEWTYPE_ITEM:
                View view1 = layoutInflater.inflate(R.layout.item_nearby_bb_list, viewGroup, false);
                return new ItemViewHolder(view1);
            case VIEWTYPE_FOOT:
                View view2 = layoutInflater.inflate(R.layout.recycler_foot, viewGroup, false);
                return new FootViewHolder(view2);
            case VIEWTYPE_DESC_SECOND:
                View view3 = layoutInflater.inflate(R.layout.item_nearby_bb_list, viewGroup, false);
                int margin_dp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 7, mContext.getResources().getDisplayMetrics());
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams.setMargins(margin_dp, margin_dp, margin_dp, 0);
                view3.setLayoutParams(layoutParams);
                return new ItemViewHolder(view3);
            default:
                return null;
        }
    }


    /*上拉加载更多*/
    public void loadMore() {
        for (int i = 0; i < 10; i++) {
            mList.add(bbInfo);
        }
        notifyItemInserted(getItemCount());
    }

    /*下拉刷新*/
    public void refresh() {

    }

    class ItemViewHolder extends AbsRecyclerAdapter.ItemViewHolder {
        private int info_id;
        private ImageView head;
        private TextView title;
        private TextView distance;
        private TextView time;
        private TextView position;
        private Button btn;

        public ItemViewHolder(View itemView) {
            super(itemView);
            head = (ImageView) itemView.findViewById(R.id.img_nearby_bb_item_head);
            title = (TextView) itemView.findViewById(R.id.tv_nearby_bb_item_title);
            distance = (TextView) itemView.findViewById(R.id.tv_nearby_bb_item_distance_content);
            time = (TextView) itemView.findViewById(R.id.tv_nearby_bb_item_time_content);
            position = (TextView) itemView.findViewById(R.id.tv_nearby_bb_item_position_content);
            btn = (Button) itemView.findViewById(R.id.btn_nearby_bb_item);
            initClickListener();
        }

        public void bind(int pos) {
            info_id = mList.get(pos).getInfo_id();
            kjBitmap.display(head,mList.get(pos).getUser_head());
//            head.setImageBitmap(BitmapFactory.decodeFile(mList.get(pos).getUser_head()));
            title.setText(mList.get(pos).getTitle());
            distance.setText(mList.get(pos).getDistance());
            time.setText(mList.get(pos).getTime());
            position.setText(mList.get(pos).getPosition());
        }

        public void initClickListener() {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null)
                        itemClickListener.click(info_id, getAdapterPosition());
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null)
                        itemClickListener.click(info_id, getAdapterPosition());
                }
            });
        }
    }
}

