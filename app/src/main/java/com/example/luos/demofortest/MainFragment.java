package com.example.luos.demofortest;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by luos on 2016/9/27.
 */
public class MainFragment extends Fragment implements OnRecyclerViewItemClickListener {
    public static final String EXTRA_ITEM = "com.exmaple.luos.demofortest.item";
    private static final String TAG = "MainFrament";
    private static final String EXTRA_LIST = "extra_list";
    private RecyclerView mRecyclerView;

    public static Fragment getInstance(ArrayList<NewsBean> list){
        MainFragment fragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(EXTRA_LIST,list);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main,container,false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerview);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
        Bundle bundle = getArguments();
        ArrayList<NewsBean> datas = bundle.getParcelableArrayList(EXTRA_LIST);
        Log.i(TAG,datas.size()+" ");
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getActivity(),datas);
        adapter.setOnItemClickListener(this);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(adapter);

        return v;
    }


    @Override
    public void OnItemClick(final View view, NewsBean data) {
        switch (view.getId()){
            case R.id.card_view:
                Intent intent = new Intent(getActivity(),ContentActivity.class);
                intent.putExtra(EXTRA_ITEM,data);
                startActivity(intent);
                break;
            case R.id.share_button:
                AlertDialog dialog = new AlertDialog.Builder(getActivity())
                        .setTitle("分享")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Snackbar.make(mRecyclerView,"操作成功",Snackbar.LENGTH_SHORT).show();
                            }
                        }).create();
                dialog.show();
                break;
        }
    }
}
