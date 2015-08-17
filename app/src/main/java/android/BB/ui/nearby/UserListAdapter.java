package android.BB.ui.nearby;

import android.BB.bean.nearby.UserInfo;
import android.BB.finals.MyConstants;
import android.BB.util.AbsRecyclerAdapter;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.BB.R;

public class UserListAdapter extends AbsRecyclerAdapter {
    private List<UserInfo> mList;
    private UserInfo userInfo;
    public UserListAdapter(Context context) {
        super(context);
        mList = new ArrayList<>();
        userInfo = new UserInfo("我是一个大帅逼","400m",1,Environment.getExternalStorageDirectory().getAbsolutePath() + MyConstants.IMAGE_PATH + "/example.png",3);
        for (int i = 0; i < 10; i++) {
            mList.add(userInfo);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        switch(viewType){
            case VIEWTYPE_ITEM:
                View view1 = layoutInflater.inflate(R.layout.item_nearby_user_list, viewGroup, false);
                return new ItemViewHolder(view1);
            case VIEWTYPE_FOOT:
                View view2 = layoutInflater.inflate(R.layout.recycler_foot, viewGroup, false);
                return new FootViewHolder(view2);
            case VIEWTYPE_DESC_SECOND:
                View view3 = layoutInflater.inflate(R.layout.item_nearby_user_list, viewGroup, false);
                int margin_dp=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,7,mContext.getResources().getDisplayMetrics());
                RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(-2,-2);
                layoutParams.setMargins(margin_dp, margin_dp, margin_dp, 0);
                view3.setLayoutParams(layoutParams);
                return new ItemViewHolder(view3);
            default:
                return null;
        }
    }


    @Override
    public int getItemCount() {
        return mList.size() + 1;
    }

    /*上拉加载更多*/
    public void loadMore() {
        for(int i=0;i<10;i++){
            mList.add(userInfo);
        }
        notifyItemInserted(getItemCount());
    }

    /*下拉刷新*/
    public void refresh() {

    }
    class ItemViewHolder extends AbsRecyclerAdapter.ItemViewHolder {
        private int user_id;
        private ImageView head;
        private TextView nickname;
        private TextView distance;
        private RatingBar ratingBar;
        private Button btn;
        public ItemViewHolder(View itemView) {
            super(itemView);
            head = (ImageView) itemView.findViewById(R.id.img_nearby_user_item_head);
            nickname = (TextView) itemView.findViewById(R.id.tv_nearby_user_item_nickname_content);
            distance = (TextView) itemView.findViewById(R.id.tv_nearby_user_item_distance_content);
            ratingBar= (RatingBar) itemView.findViewById(R.id.ratingbar_nearby_user_item_credit);
            btn= (Button) itemView.findViewById(R.id.btn_nearby_user_item);
            initClickListener();
        }
        public void bind(int pos){
            user_id=mList.get(pos).getUser_id();
            head.setImageBitmap(BitmapFactory.decodeFile(mList.get(pos).getUser_head()));
            nickname.setText(mList.get(pos).getNickname());
            distance.setText(mList.get(pos).getDistance());
            ratingBar.setRating(mList.get(pos).getCredit());
        }

        public void initClickListener() {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(itemClickListener!=null)
                        itemClickListener.click(user_id,getAdapterPosition());
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(itemClickListener!=null)
                        itemClickListener.click(user_id,getAdapterPosition());
                }
            });
        }
    }

    class FootViewHolder extends ViewHolder {
        public FootViewHolder(View view) {
            super(view);
        }
    }
}

