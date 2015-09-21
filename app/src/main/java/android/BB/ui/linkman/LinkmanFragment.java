package android.BB.ui.linkman;

import android.BB.bean.User;
import android.BB.ui.main.BaseFragment;
import android.BB.ui.main.CustomApplication;
import android.BB.util.CharacterParser;
import android.BB.util.CollectionUtils;
import android.BB.util.PinyinComparator;
import android.BB.view.ClearEditText;
import android.BB.view.MyLetterView;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import android.BB.R;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import cn.bmob.im.bean.BmobChatUser;
import cn.bmob.im.db.BmobDB;
import cn.bmob.v3.listener.UpdateListener;

/**
 * 联系人
 * 初次进入，从服务器获取联系人数据，存入数据库
 * 以后每次进入，如果没有联网，就先读取数据库的内容
 * 如果联网，就从服务器获取数据
 * 长按删除联系人/更改备注名称
 */
public class LinkmanFragment extends BaseFragment {

    private View view;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private InputMethodManager inputMethodManager;
    private ClearEditText clearEditText;//搜索联系人的输入框
    private TextView dialog_hint;//滑动到某个联系人的中间提示
    MyLetterView right_letter;
    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser;
    /**
     * 根据拼音来排列ListView里面的数据类
     */
    private PinyinComparator pinyinComparator;
    List<User> friends = new ArrayList<User>();//联系人数据
    ImageView iv_msg_tips;//提示有新的联系人
    private LinkmanRecycleViewAdapter adapter;

    public static LinkmanFragment newInstance() {
        LinkmanFragment fragment = new LinkmanFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.ll_linkman, container, false);
//        init();
        return view;
    }

    private void init() {
        fab = (FloatingActionButton) view.findViewById(R.id.fab_add_contacts);
//        fab.setImageDrawable();
        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinComparator();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddContacts.class);
                startActivity(intent);
            }
        });
        initRightLetterView();
        initEditText();
        initListView();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        init();
    }

    /** 获取好友列表
     * queryMyfriends
     * @return void
     * @throws
     */
    private void queryMyfriends() {
        //是否有新的好友请求
        if(BmobDB.create(getActivity()).hasNewInvite()){
            iv_msg_tips.setVisibility(View.VISIBLE);
        }else{
            iv_msg_tips.setVisibility(View.GONE);
        }
        //在这里再做一次本地的好友数据库的检查，是为了本地好友数据库中已经添加了对方，但是界面却没有显示出来的问题
        // 重新设置下内存中保存的好友列表
        CustomApplication.getInstance().setContactList(CollectionUtils.list2map(BmobDB.create(getActivity()).getContactList()));

        Map<String,BmobChatUser> users = CustomApplication.getInstance().getContactList();
        //组装新的User
        filledData(CollectionUtils.map2list(users));


    }

    private void initRightLetterView() {
        right_letter = (MyLetterView) view.findViewById(R.id.right_letter);
        dialog_hint = (TextView) view.findViewById(R.id.dialog_hint);
        right_letter.setTextView(dialog_hint);
//        right_letter.setOnTouchingLetterChangedListener(new LetterListViewListener());
    }

    private void initEditText() {
        clearEditText = (ClearEditText) view.findViewById(R.id.edt_linkman_search);
        // 根据输入框输入值的改变来过滤搜索
        clearEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
                filterData(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initListView() {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_linkman);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        if(adapter==null){
            adapter = new LinkmanRecycleViewAdapter(getActivity());
            recyclerView.setAdapter(adapter);
        }else{
            adapter.notifyDataSetChanged();
        }
//        recyclerView.setOnClickListener(this);
//        recyclerView.setOnItemLongClickListener(this);

        recyclerView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // 隐藏软键盘
                if (getActivity().getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
                    if (getActivity().getCurrentFocus() != null)
                        inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);
                }
                return false;
            }
        });

    }

    /**
     * RecyclerView 的 Adapter
     */
    public class LinkmanRecycleViewAdapter extends RecyclerView.Adapter<LinkmanRecycleViewAdapter.ContactsViewHolder>
                implements SectionIndexer{
        private LayoutInflater mLayoutInflater;
        private Context mContext;
        public static final int VIEW_TYPE_HEADER = 0;
        public static final int VIEW_TYPE_REPLY = 1;

        private String[] mTitles = {"asd","bb","cc","dd","ee","ff","rr","xx","rr"};

        public LinkmanRecycleViewAdapter(Context context) {
//            mTitles = context.getResources().getStringArray(R.array.titles);//暂时获取数据
            mContext = context;
            
            mLayoutInflater = LayoutInflater.from(context);
        }

        /**
         * 当ListView数据发生变化时,调用此方法来更新ListView
         */
        public void updateListView(List<User> list) {
//            this.data = list;
            notifyDataSetChanged();
        }

        public void remove(User user){
//            this.data.remove(user);
            notifyDataSetChanged();
        }

        @Override
        public ContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            switch (viewType) {
                case VIEW_TYPE_HEADER://header——新朋友 添加请求
                    View v0 = mLayoutInflater.inflate(R.layout.listitem_linkman_new_friend, null);
                    ContactsViewHolder header = new ContactsViewHolder(v0);
//                    v0.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            //跳转到添加联系人的历史
//                            Intent intent = new Intent(getActivity(), ContactsHistory.class);
//                            startActivity(intent);
//                        }
//                    });
                    return header;
                case VIEW_TYPE_REPLY:
                    View vr = mLayoutInflater.inflate(R.layout.listitem_linkman, null);
//                    vr.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//
//                        }
//                    });
//                    vr.setOnLongClickListener(new View.OnLongClickListener() {
//                        @Override
//                        public boolean onLongClick(View v) {
//                            //长按删除联系人
//                            //开启对话框
//                            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                            builder.setMessage("删除该联系人？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
////                                    deleteContact();
//                                    ShowToast("shangchu...");
//                                }
//                            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    dialog.dismiss();
//                                }
//                            }).create().show();
////                            deleteContact();
//                            return false;
//                        }
//                    });
                    return new ContactsViewHolder(vr);
                default:
                    return null;
            }
        }

        @Override
        public void onBindViewHolder(ContactsViewHolder holder, int position) {
            switch (getItemViewType(position)) {
                case VIEW_TYPE_HEADER://header

                    break;
                case VIEW_TYPE_REPLY://各个联系人

//            holder.portrait.setImageDrawable(R.mipmap.add_contacts);
                    holder.name.setText(mTitles[position]);
                    // 根据position获取分类的首字母的Char ascii值
                    int section = getSectionForPosition(position);
                    // 如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
                    if (position == getPositionForSection(section)) {
                        holder.alpha.setVisibility(View.VISIBLE);
//                        holder.alpha.setText(friend.getSortLetters());
                        holder.alpha.setText("a");
                    } else {
                        holder.alpha.setVisibility(View.GONE);
                    }
                    break;
                default:
                    break;
            }
        }

        @Override
        public int getItemCount() {
            return mTitles == null ? 0 : mTitles.length;
        }

        @Override
        public int getItemViewType(int position) {
            return position == 0 ? VIEW_TYPE_HEADER : VIEW_TYPE_REPLY;
        }

        public class ContactsViewHolder extends RecyclerView.ViewHolder {
            /******************* header **********************/
            ImageView tips;

            /******************* 联系人信息 **********************/
            TextView alpha;//首字母——排序
            ImageView portrait;
            TextView name;
            public ContactsViewHolder(View view) {
                super(view);
                this.tips = (ImageView) view.findViewById(R.id.img_new_tips);//当有添加联系人的请求的时候出现
                this.alpha = (TextView) view.findViewById(R.id.alpha);
                this.portrait = (ImageView) view.findViewById(R.id.img_linkman_portrait);
                this.name = (TextView) view.findViewById(R.id.tv_linkman_nickname);
            }
        }

        /**
         * 根据ListView的当前位置获取分类的首字母的Char ascii值
         */
        public int getSectionForPosition(int position) {
            return mTitles[position].charAt(0);
        }

        /**
         * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
         */
        public int getPositionForSection(int section) {
            for (int i = 0; i < getItemCount(); i++) {
//                String sortStr = data.get(i).getSortLetters();
                String sortStr = mTitles[i];
                char firstChar = sortStr.toUpperCase().charAt(0);
                if (firstChar == section){
                    return i;
                }
            }

            return -1;
        }

        @Override
        public Object[] getSections() {
            return null;
        }

    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view , String data);
    }

    /**
     * 为ListView填充数据
     * @param date
     * @return
     */
    private void filledData(List<BmobChatUser> datas) {
        friends.clear();
        int total = datas.size();
        for (int i = 0; i < total; i++) {
            BmobChatUser user = datas.get(i);
            User sortModel = new User();
            sortModel.setAvatar(user.getAvatar());
            sortModel.setNick(user.getNick());
            sortModel.setUsername(user.getUsername());
            sortModel.setObjectId(user.getObjectId());
            sortModel.setContacts(user.getContacts());
            // 汉字转换成拼音
            String username = sortModel.getUsername();
            // 若没有username
            if (username != null) {
                String pinyin = characterParser.getSelling(sortModel.getUsername());
                String sortString = pinyin.substring(0, 1).toUpperCase();
                // 正则表达式，判断首字母是否是英文字母
                if (sortString.matches("[A-Z]")) {
                    sortModel.setSortLetters(sortString.toUpperCase());
                } else {
                    sortModel.setSortLetters("#");
                }
            } else {
                sortModel.setSortLetters("#");
            }
            friends.add(sortModel);
        }
        // 根据a-z进行排序
        Collections.sort(friends, pinyinComparator);
    }


    /**
     * 根据输入框中的值来过滤数据并更新ListView
     *
     * @param filterStr
     */
    private void filterData(String filterStr) {
        List<User> filterDateList = new ArrayList<User>();
        if (TextUtils.isEmpty(filterStr)) {
            filterDateList = friends;
        } else {
            filterDateList.clear();
            for (User sortModel : friends) {
                String name = sortModel.getUsername();
                if (name != null) {
                    if (name.indexOf(filterStr.toString()) != -1
                            || characterParser.getSelling(name).startsWith(
                            filterStr.toString())) {
                        filterDateList.add(sortModel);
                    }
                }
            }
        }
        // 根据a-z进行排序
        Collections.sort(filterDateList, pinyinComparator);
        adapter.updateListView(filterDateList);
    }

    /** 删除联系人
     * deleteContact
     * @return void
     * @throws
     */
    private void deleteContact(final User user){
        final ProgressDialog progress = new ProgressDialog(getActivity());
        progress.setMessage("正在删除...");
        progress.setCanceledOnTouchOutside(false);
        progress.show();
        userManager.deleteContact(user.getObjectId(), new UpdateListener() {

            @Override
            public void onSuccess() {
                // TODO Auto-generated method stub
                ShowToast("删除成功");
                //删除内存
                CustomApplication.getInstance().getContactList().remove(user.getUsername());
                //更新界面
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        progress.dismiss();
//                        userAdapter.remove(user);
                    }
                });
            }

            @Override
            public void onFailure(int arg0, String arg1) {
                // TODO Auto-generated method stub
                ShowToast("删除失败："+arg1);
                progress.dismiss();
            }
        });
    }
}
