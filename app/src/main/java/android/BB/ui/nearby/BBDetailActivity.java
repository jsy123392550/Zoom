package android.BB.ui.nearby;

import android.BB.finals.MyConstants;
import android.BB.util.AbsRecyclerAdapter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import app.BB.R;

public class BBDetailActivity extends AppCompatActivity {
    private RecyclerView item_recyclerView;
    private RecyclerView img_recyclerView;
    private AbsRecyclerAdapter adapter;
    private LinearLayoutManager layoutManager;
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
        item_recyclerView= (RecyclerView) findViewById(R.id.recyclerview_bbdetail);
        toolbar.setTitle(MyConstants.TEXT_NULL);
        tv_toolbar.setText(MyConstants.KEY_TOOLBAR_BBDETAIL);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.mipmap.back_arrow);
        adapter=new BBDetailListAdapter(this);
        layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        item_recyclerView.setLayoutManager(layoutManager);
        item_recyclerView.setAdapter(adapter);
        item_recyclerView.addOnScrollListener(new MyScrollListener());
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                    adapter.loadMore();
                    Toast.makeText(BBDetailActivity.this,"上拉加载成功！",Toast.LENGTH_SHORT).show();
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
                handler.sendEmptyMessageDelayed(0,3000);
            }
        }
    }
}
