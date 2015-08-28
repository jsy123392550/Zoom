package android.BB.ui.nearby;


import android.BB.bean.nearby.Comment;
import android.BB.bean.nearby.HostInfo;
import android.BB.finals.MyConstants;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.BB.R;

public class NearbyBBFragment extends Fragment{
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private BBListAdapter adapter;
    private LinearLayoutManager layoutManager;
    private int lastVisibleItem;
    private Handler handler;
    private boolean isLoad;
    public NearbyBBFragment() {
    }
    public static NearbyBBFragment newInstance(){
        NearbyBBFragment fragment=new NearbyBBFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_nearby_pager, container, false);
        recyclerView= (RecyclerView) view.findViewById(R.id.recyclerview_fragment_nearby_pager);
        swipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.swipe_fragment_nearby_pager);
        init();
        return view;
    }

    private void init(){
        adapter=new BBListAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        layoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==0){
                    adapter.refresh();
                    swipeRefreshLayout.setRefreshing(false);
                    Toast.makeText(getActivity(),"下拉刷新成功！",Toast.LENGTH_SHORT).show();
                }else if(msg.what==1){
                    adapter.loadMore();
                    isLoad=false;
                    Toast.makeText(getActivity(),"上拉加载成功！",Toast.LENGTH_SHORT).show();
                }
            }
        };
        adapter.setClickListener(new BBListAdapter.ItemClickListener() {
            @Override
            public void click(int info_id, int pos) {
                Intent intent=new Intent(getActivity(),BBDetailActivity.class);
                ArrayList<String> imgs=new ArrayList<>();
                String img=Environment.getExternalStorageDirectory().getAbsolutePath() + MyConstants.IMAGE_PATH+"/qop.png";
                List<Comment> commentList=new ArrayList<Comment>();
                Comment comment=new Comment(2,"大骚逼一枚",img ,"3分钟前","我今天没吃药，感觉自己萌萌哒！");
                for(int i=0;i<10;i++){
                    commentList.add(comment);
                    if(i>=3)
                        imgs.add(img);
                }
                HostInfo hostInfo=new HostInfo(1,imgs,"1个小时前","我在二教2217教室上课的时候忘拿雨伞了，有顺路的同学能帮我带到11栋吗？",img,"我是一个大帅逼",5,11,3,6);
                intent.putExtra(MyConstants.KEY_HOSTINFO,hostInfo);
                intent.putExtra(MyConstants.KEY_COMMENT_LIST, (Serializable) commentList);
                startActivity(intent);
            }
        });
        isLoad=false;
        recyclerView.addOnScrollListener(new MyScrollListener());
        recyclerView.setHasFixedSize(true);
        swipeRefreshLayout.setColorSchemeColors(getActivity().getResources().getColor(R.color.orange_normal), getActivity().getResources().getColor(R.color.orange_press));
        swipeRefreshLayout.setOnRefreshListener(new MyRefreshListener());
    }
    class MyRefreshListener implements SwipeRefreshLayout.OnRefreshListener{
        @Override
        public void onRefresh() {
            handler.sendEmptyMessageDelayed(0,3000);
        }
    }
    class MyScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItem=layoutManager.findLastCompletelyVisibleItemPosition();
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if(newState==RecyclerView.SCROLL_STATE_IDLE&&lastVisibleItem+1==adapter.getItemCount()){
                if(!isLoad){
                    isLoad=true;
                    handler.sendEmptyMessageDelayed(1,3000);
                }
            }
        }
    }
}
