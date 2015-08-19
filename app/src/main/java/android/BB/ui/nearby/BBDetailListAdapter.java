package android.BB.ui.nearby;

import android.BB.bean.nearby.Comment;
import android.BB.finals.MyConstants;
import android.BB.util.AbsRecyclerAdapter;
import android.content.Context;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.kymjs.kjframe.KJBitmap;

import java.util.ArrayList;
import java.util.List;

import app.BB.R;

public class BBDetailListAdapter extends AbsRecyclerAdapter{
    private KJBitmap kjBitmap;
    private List<Comment> list;
    private Comment comment;
    public BBDetailListAdapter(Context context) {
        super(context);
        kjBitmap=new KJBitmap();
        list=new ArrayList<>();
        comment=new Comment(2,"大骚逼一枚", Environment.getExternalStorageDirectory().getAbsolutePath() + MyConstants.IMAGE_PATH+"/example.png","3分钟前","我今天没吃药，感觉自己萌萌哒！");
        for(int i=0;i<10;i++){
            list.add(comment);
        }
    }

    @Override
    public void loadMore() {
        for(int i=0;i<10;i++){
            list.add(comment);
        }
        notifyItemInserted(getItemCount());
    }

    @Override
    public void refresh() {

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch(viewType){
            case VIEWTYPE_ITEM:
                View view1=layoutInflater.inflate(R.layout.item_nearby_bbdetail_list,parent,false);
                return new ItemViewHolder(view1);
            case VIEWTYPE_FOOT:
                View view2=layoutInflater.inflate(R.layout.recycler_foot,parent,false);
                return new FootViewHolder(view2);
            default:
                return null;
        }
    }

    @Override
    public int getItemViewType(int position) {
        int viewType;
        if(position+1!=getItemCount())
            viewType=VIEWTYPE_ITEM;
        else
            viewType=VIEWTYPE_FOOT;
        return viewType;
    }

    @Override
    public int getItemCount() {
        return list.size()+1;
    }
    class ItemViewHolder extends AbsRecyclerAdapter.ItemViewHolder{
        private int user_id;
        private ImageView user_head;
        private TextView nickname;
        private TextView content;
        private TextView time;
        public ItemViewHolder(View itemView) {
            super(itemView);
            user_head= (ImageView) itemView.findViewById(R.id.img_nearby_bbdetail_item);
            nickname= (TextView) itemView.findViewById(R.id.tv_nearby_bbdetail_item_nickname);
            content= (TextView) itemView.findViewById(R.id.tv_nearby_bbdetail_item_content);
            time= (TextView) itemView.findViewById(R.id.tv_nearby_bbdetail_item_time);
        }

        @Override
        public void bind(int pos) {
            user_id=list.get(pos).getUser_id();
            kjBitmap.display(user_head,list.get(pos).getUser_head());
            nickname.setText(list.get(pos).getNickname());
            content.setText(list.get(pos).getContent());
            time.setText(list.get(pos).getTime());
        }
    }
}
