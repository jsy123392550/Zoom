package android.BB.ui.nearby;

import android.BB.bean.nearby.Comment;
import android.BB.bean.nearby.HostInfo;
import android.BB.finals.MyConstants;
import android.BB.util.AbsRecyclerAdapter;
import android.BB.widget.NoScrollGridView;
import android.content.Context;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.kymjs.kjframe.KJBitmap;

import java.util.ArrayList;
import java.util.List;

import app.BB.R;

public class BBDetailListAdapter extends AbsRecyclerAdapter{
    private static final int VIEWTYPE_HEAD=3;
    private KJBitmap kjBitmap;
    private List<Comment> list;
    private Comment comment;
    private HostInfo hostInfo;
    private ImageGridAdapter imageGridAdapter;
    public BBDetailListAdapter(Context context) {
        super(context);
        kjBitmap=new KJBitmap();
        list=new ArrayList<>();
        comment=new Comment(2,"大骚逼一枚", Environment.getExternalStorageDirectory().getAbsolutePath() + MyConstants.IMAGE_PATH+"/qop.png","3分钟前","我今天没吃药，感觉自己萌萌哒！");
        for(int i=0;i<10;i++){
            list.add(comment);
        }
        hostInfo=new HostInfo(1,null,"1个小时前","我在二教2217教室上课的时候忘拿雨伞了，有顺路的同学能帮我带到11栋吗？",Environment.getExternalStorageDirectory().getAbsolutePath()+MyConstants.IMAGE_PATH+"/qop.png","我是一个大帅逼",5,11,3,6);
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
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int pos) {
        if(viewHolder instanceof ItemViewHolder)
            ((ItemViewHolder) viewHolder).bind(pos);
        else if(viewHolder instanceof HeadViewHolder)
            ((HeadViewHolder) viewHolder).bind();
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
            case VIEWTYPE_HEAD:
                View view3=layoutInflater.inflate(R.layout.head_bbdetail,parent,false);
                return new HeadViewHolder(view3);
            default:
                return null;
        }
    }

    @Override
    public int getItemViewType(int position) {
        int viewType;
        if(position+1==getItemCount())
            viewType=VIEWTYPE_FOOT;
        else if(position==0)
            viewType=VIEWTYPE_HEAD;
        else
            viewType=VIEWTYPE_ITEM;
        return viewType;
    }

    @Override
    public int getItemCount() {
        return list.size()+1;
    }
    class HeadViewHolder extends RecyclerView.ViewHolder{
        private ImageView host_head;
        private TextView nickname;
        private TextView time;
        private TextView content;
        private TextView money;
        private TextView praise;
        private TextView comment;
        private TextView forward;
        private Button btn;
        private NoScrollGridView gridView;
        private final String PATH=Environment.getExternalStorageDirectory().getAbsolutePath() + MyConstants.IMAGE_PATH+"/qop.png";
        public HeadViewHolder(View itemView) {
            super(itemView);
            btn= (Button) itemView.findViewById(R.id.btn_bbdetail_host);
            host_head= (ImageView) itemView.findViewById(R.id.img_bbdetail_host_head);
            nickname= (TextView) itemView.findViewById(R.id.tv_bbdetail_host_nickname);
            time= (TextView) itemView.findViewById(R.id.tv_bbdetail_host_time);
            content= (TextView) itemView.findViewById(R.id.tv_bbdetail_host_content);
            money= (TextView) itemView.findViewById(R.id.tv_bbdetail_host_money);
            praise= (TextView) itemView.findViewById(R.id.tv_bbdetail_host_praise);
            comment= (TextView) itemView.findViewById(R.id.tv_bbdetail_host_comment);
            forward= (TextView) itemView.findViewById(R.id.tv_bbdetail_host_forward);
            gridView= (NoScrollGridView) itemView.findViewById(R.id.gridview_bbdetail_host_img);
        }

        public void bind() {
            kjBitmap.display(host_head, hostInfo.getUser_head());
            nickname.setText(hostInfo.getNickname());
            time.setText(hostInfo.getTime());
            content.setText(hostInfo.getContent());
            money.setText("价钱：" + hostInfo.getMoney() + "元");
            praise.setText(String.valueOf(hostInfo.getPraise()));
            comment.setText(String.valueOf(hostInfo.getComment()));
            forward.setText(String.valueOf(hostInfo.getForward()));
            imageGridAdapter=new ImageGridAdapter(mContext,new String[]{PATH, PATH, PATH, PATH, PATH, PATH});
//            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                }
//            });
            gridView.setAdapter(imageGridAdapter);
            Log.e("BB", "img bind");
        }
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
