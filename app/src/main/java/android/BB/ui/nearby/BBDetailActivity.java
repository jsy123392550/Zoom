package android.BB.ui.nearby;

import android.BB.finals.MyConstants;
import android.BB.util.AbsRecyclerAdapter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import app.BB.R;

public class BBDetailActivity extends AppCompatActivity {
    private static final int GRID_SPANCOUNT=3;
    private RecyclerView item_recyclerView;
    private RecyclerView img_recyclerView;
    private SwipeRefreshLayout swipe;
    private AbsRecyclerAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;
    private Toolbar toolbar;
    private TextView tv_toolbar;
    private int lastVisibleItem;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bbdetail);
        init();
    }
    private void init(){
        toolbar= (Toolbar) findViewById(R.id.toolbar_bbdetail);
        tv_toolbar= (TextView) toolbar.findViewById(R.id.toolbar_tv);
        swipe= (SwipeRefreshLayout) findViewById(R.id.swipe_bbdetail);
        img_recyclerView= (RecyclerView) findViewById(R.id.recyclerview_bbdetail_host_img);
        item_recyclerView= (RecyclerView) findViewById(R.id.recyclerview_bbdetail);
        toolbar.setTitle(MyConstants.TEXT_NULL);
        tv_toolbar.setText(MyConstants.KEY_TOOLBAR_BBDETAIL);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.back_arrow);
        adapter=new BBDetailListAdapter(this);
        linearLayoutManager =new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
//        gridLayoutManager=new GridLayoutManager(this,GRID_SPANCOUNT);
//        img_recyclerView.setLayoutManager(gridLayoutManager);
        item_recyclerView.setLayoutManager(linearLayoutManager);
        item_recyclerView.setAdapter(adapter);
        item_recyclerView.setHasFixedSize(true);
        swipe.setColorSchemeColors(getResources().getColor(R.color.orange_normal), getResources().getColor(R.color.orange_press));
        swipe.setOnRefreshListener(new MyRefreshListener());
        item_recyclerView.addOnScrollListener(new MyScrollListener());
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==0){
                    adapter.refresh();
                    swipe.setRefreshing(false);
                    Toast.makeText(BBDetailActivity.this,"下拉刷新成功！",Toast.LENGTH_SHORT).show();
                }else if(msg.what==1){
                    adapter.loadMore();
                    Toast.makeText(BBDetailActivity.this,"上拉加载成功！",Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bbdetail,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }else if(id==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
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
            lastVisibleItem= linearLayoutManager.findLastCompletelyVisibleItemPosition();
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if(newState==RecyclerView.SCROLL_STATE_IDLE&&lastVisibleItem+1==adapter.getItemCount()){
                handler.sendEmptyMessageDelayed(1,3000);
            }
        }
    }
}
