package android.BB.ui.nearby;

import android.BB.bean.nearby.Comment;
import android.BB.bean.nearby.HostInfo;
import android.BB.finals.MyConstants;
import android.BB.util.AbsRecyclerAdapter;
import android.BB.widget.NoScrollGridView;
import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.kymjs.kjframe.KJBitmap;

import java.util.List;

import app.BB.R;

public class BBDetailListAdapter extends AbsRecyclerAdapter{
    private static final int VIEWTYPE_HEAD=3;
    private KJBitmap kjBitmap;
    private List<Comment> commentList;
    private HostInfo hostInfo;
    private ImageGridAdapter imageGridAdapter;
    public BBDetailListAdapter(Context context,List<Comment> commentList,HostInfo hostInfo) {
        super(context);
        kjBitmap=new KJBitmap();
        this.commentList =commentList;
        this.hostInfo=hostInfo;
    }

    @Override
    public void loadMore() {
        Comment comment=new Comment(2,"大骚逼一枚",Environment.getExternalStorageDirectory().getAbsolutePath() + MyConstants.IMAGE_PATH+"/qop.png" ,"3分钟前","我今天没吃药，感觉自己萌萌哒！");
        for(int i=0;i<10;i++){
            commentList.add(comment);
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
        return commentList.size()+1;
    }
    class HeadViewHolder extends RecyclerView.ViewHolder{
        private static final int DURATION_MILLS=500;
        private ImageView host_head;
        private ImageView img_praise;
        private ImageView img_comment;
        private ImageView img_forward;
        private TextView tv_nickname;
        private TextView tv_time;
        private TextView tv_content;
        private TextView tv_money;
        private TextView tv_praise;
        private TextView tv_comment;
        private TextView tv_forward;
        private Button btn;
        private NoScrollGridView gridView;
        private ScaleAnimation animation;
        private OnclickPraiseListener praiseListener;
        private boolean isPraise;
        private boolean isComment;
        private boolean isForward;

        public HeadViewHolder(View itemView) {
            super(itemView);
            isPraise=false;
            isComment=false;
            isForward=false;
            btn= (Button) itemView.findViewById(R.id.btn_bbdetail_host);
            host_head= (ImageView) itemView.findViewById(R.id.img_bbdetail_host_head);
            img_praise= (ImageView) itemView.findViewById(R.id.img_bbdetail_host_praise);
            img_comment= (ImageView) itemView.findViewById(R.id.img_bbdetail_host_comment);
            img_forward= (ImageView) itemView.findViewById(R.id.img_bbdetail_host_forward);
            tv_nickname = (TextView) itemView.findViewById(R.id.tv_bbdetail_host_nickname);
            tv_time = (TextView) itemView.findViewById(R.id.tv_bbdetail_host_time);
            tv_content = (TextView) itemView.findViewById(R.id.tv_bbdetail_host_content);
            tv_money = (TextView) itemView.findViewById(R.id.tv_bbdetail_host_money);
            tv_praise = (TextView) itemView.findViewById(R.id.tv_bbdetail_host_praise);
            tv_comment = (TextView) itemView.findViewById(R.id.tv_bbdetail_host_comment);
            tv_forward = (TextView) itemView.findViewById(R.id.tv_bbdetail_host_forward);
            gridView= (NoScrollGridView) itemView.findViewById(R.id.gridview_bbdetail_host_img);
            animation=new ScaleAnimation(1.0f,1.3f,1.0f,1.3f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
            animation.setDuration(DURATION_MILLS);
            praiseListener=new OnclickPraiseListener();
        }
        class OnclickPraiseListener implements View.OnClickListener{
            @Override
            public void onClick(View v) {
                if(isPraise){
                    Toast.makeText(mContext.getApplicationContext(),"你已经赞过了这个BB",Toast.LENGTH_SHORT).show();
                }else{
                    img_praise.setColorFilter(mContext.getResources().getColor(R.color.orange_press), PorterDuff.Mode.MULTIPLY);
                    scaleAnimation(img_praise);
                    tv_praise.setText(String.valueOf(Integer.parseInt(tv_praise.getText().toString())+1));
                    isPraise=true;
                }
            }
        }
        public void scaleAnimation(View view){
            view.startAnimation(animation);
        }
        public void bind() {
            kjBitmap.display(host_head, hostInfo.getUser_head());
            tv_nickname.setText(hostInfo.getNickname());
            tv_time.setText(hostInfo.getTime());
            tv_content.setText(hostInfo.getContent());
            tv_money.setText("价钱：" + hostInfo.getMoney() + "元");
            tv_praise.setText(String.valueOf(hostInfo.getPraise()));
            tv_comment.setText(String.valueOf(hostInfo.getComment()));
            tv_forward.setText(String.valueOf(hostInfo.getForward()));
            imageGridAdapter=new ImageGridAdapter(mContext,hostInfo.getImgs());
            gridView.setAdapter(imageGridAdapter);
            img_praise.setOnClickListener(praiseListener);
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
            user_id= commentList.get(pos).getUser_id();
            kjBitmap.display(user_head, commentList.get(pos).getUser_head());
            nickname.setText(commentList.get(pos).getNickname());
            content.setText(commentList.get(pos).getContent());
            time.setText(commentList.get(pos).getTime());
        }
    }
}
