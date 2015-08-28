package android.BB.ui.nearby;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.BB.R;
import android.widget.Toast;

public class NearbyResourceFragment extends Fragment {
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayoutManager layoutManager;
    private BBListAdapter adapter;
    private int lastVisibleItem;
    private Handler handler;
    private boolean isLoad;
    public static NearbyResourceFragment newInstance() {
        NearbyResourceFragment fragment = new NearbyResourceFragment();
        return fragment;
    }

    public NearbyResourceFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_nearby_pager, container, false);
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
                    Toast.makeText(getActivity(), "下拉刷新成功！", Toast.LENGTH_SHORT).show();
                }else if(msg.what==1){
                    adapter.loadMore();
                    isLoad=false;
                    Toast.makeText(getActivity(),"上拉加载成功！",Toast.LENGTH_SHORT).show();
                }
            }
        };
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
