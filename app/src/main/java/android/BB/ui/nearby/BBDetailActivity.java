package android.BB.ui.nearby;

import android.BB.bean.nearby.Comment;
import android.BB.bean.nearby.HostInfo;
import android.BB.finals.MyConstants;
import android.BB.util.AbsRecyclerAdapter;
import android.BB.util.Utils;
import android.BB.widget.MyItemDecoration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import android.BB.R;

public class BBDetailActivity extends AppCompatActivity {
    private RecyclerView item_recyclerView;
    private SwipeRefreshLayout swipe;
    private AbsRecyclerAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private Toolbar toolbar;
    private TextView tv_toolbar;
    private LinearLayout layout_comment;
    private EditText et_comment;
    private Button btn_comment;
    private int lastVisibleItem;
    private Handler handler;
    private boolean isLoad;
    private CommentCallback callback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bbdetail);
        init();
    }
    private void init(){
        isLoad=false;
        toolbar= (Toolbar) findViewById(R.id.toolbar_bbdetail);
        tv_toolbar= (TextView) toolbar.findViewById(R.id.toolbar_tv);
        swipe= (SwipeRefreshLayout) findViewById(R.id.swipe_bbdetail);
        item_recyclerView= (RecyclerView) findViewById(R.id.recyclerview_bbdetail);
        layout_comment= (LinearLayout) findViewById(R.id.layout_bbdetail_comment);
        et_comment= (EditText) findViewById(R.id.et_bbdetail_comment);
        btn_comment= (Button) findViewById(R.id.btn_bbdetail_comment);
        btn_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.hideSoftInput(getApplicationContext());
                callback.change();
            }
        });
        toolbar.setTitle(MyConstants.TEXT_NULL);
        tv_toolbar.setText(MyConstants.KEY_TOOLBAR_BBDETAIL);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.back_arrow);
        adapter=new BBDetailListAdapter(this,(List<Comment>)getIntent().getSerializableExtra(MyConstants.KEY_COMMENT_LIST),(HostInfo)getIntent().getSerializableExtra(MyConstants.KEY_HOSTINFO),getIntent().getBooleanExtra(MyConstants.KEY_BB_TYPE,false));
        linearLayoutManager =new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        item_recyclerView.setLayoutManager(linearLayoutManager);
        item_recyclerView.setAdapter(adapter);
        item_recyclerView.setHasFixedSize(true);
        item_recyclerView.addItemDecoration(new MyItemDecoration(this,getResources().getDrawable(R.drawable.item_decoration_bbdetail), (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,8,getResources().getDisplayMetrics())));
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
                    isLoad=false;
                    Toast.makeText(BBDetailActivity.this,"上拉加载成功！",Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    public EditText getEt_comment() {
        return et_comment;
    }

    public void setCallback(CommentCallback callback) {
        this.callback = callback;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bbdetail,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public interface CommentCallback {
        public void change();
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
                if(!isLoad) {
                    isLoad=true;
                    handler.sendEmptyMessageDelayed(1, 3000);
                }
            }
        }
    }
}
