package android.BB.ui.nearby;


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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import app.BB.R;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("BB","BB oncreate");
        View view=inflater.inflate(R.layout.fragment_nearby_bb, container, false);
        recyclerView= (RecyclerView) view.findViewById(R.id.recyclerview_fragment_nearby_bb);
        swipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.swipe_fragment_nearby_bb);
        adapter=new BBListAdapter(getActivity());
        init();
        recyclerView.setAdapter(adapter);
        layoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        /*模拟网络请求*/
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
                startActivity(new Intent(getActivity(),BBDetailActivity.class));
            }
        });
        return view;
    }

    private void init(){
        isLoad=false;
        recyclerView.addOnScrollListener(new MyScrollListener());
        recyclerView.setHasFixedSize(true);
        swipeRefreshLayout.setColorSchemeColors(getActivity().getResources().getColor(R.color.orange_normal),getActivity().getResources().getColor(R.color.orange_press));
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
