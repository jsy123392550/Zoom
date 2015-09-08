package android.BB.ui.linkman;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.BB.R;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 初次进入，从服务器获取联系人数据，存入数据库
 * 以后每次进入，如果没有联网，就先读取数据库的内容
 * 如果联网，就从服务器获取数据
 * 长按删除联系人/更改备注名称
 */
public class LinkmanFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private ArrayList<HashMap> datalists;//数据源

    public static LinkmanFragment newInstance() {
        LinkmanFragment fragment = new LinkmanFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.ll_linkman, container, false);
        init();
        return view;
    }

    private void init() {
        fab = (FloatingActionButton) view.findViewById(R.id.fab_add_contacts);
//        fab.setImageDrawable();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddContacts.class);
                startActivity(intent);
            }
        });
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_linkman);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));


//        recyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                //跳转到相应联系人界面
//
//            }
//        });
    }

    public class LinkmanRecycleViewAdapter extends RecyclerView.Adapter<LinkmanRecycleViewAdapter.ContactsViewHolder> {
        private final LayoutInflater mLayoutInflater;
        private final Context mContext;
        private String[] mTitles;

        public LinkmanRecycleViewAdapter(Context context) {
//            mTitles = context.getResources().getStringArray(R.array.titles);
            mContext = context;
            
            mLayoutInflater = LayoutInflater.from(context);
        }

        @Override
        public ContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_linkman, null);
            v.findViewById(R.id.tv_linkman_nickname);

            return null;
        }

        @Override
        public void onBindViewHolder(ContactsViewHolder holder, int position) {
            holder.mTextView.setText(mTitles[position]);
        }

        @Override
        public int getItemCount() {
            return mTitles == null ? 0 : mTitles.length;
        }

        public class ContactsViewHolder extends RecyclerView.ViewHolder {
            TextView mTextView;

            ContactsViewHolder(View view) {
                super(view);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("NormalTextViewHolder", "onClick--> position = " + getPosition());
                    }
                });
            }
        }
    }
}
