package android.BB.ui.linkman;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.BB.R;
import android.widget.ListView;

public class LinkmanFragment extends Fragment {

    private View view;
    private ListView listView;
    private FloatingActionButton fab;

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
        listView = (ListView) view.findViewById(R.id.listview_linkman);

    }
}
