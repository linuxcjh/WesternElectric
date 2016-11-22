package com.nuoman.westernele.contacts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.nuoman.tabletattendance.R;
import com.nuoman.westernele.common.BaseFragment;
import com.nuoman.westernele.contacts.adapter.ClientRecordAdapter;
import com.nuoman.westernele.contacts.model.ClientRecordModel;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 管理
 * 2016/1/13
 */
public class ContactsLeaderFragment extends BaseFragment {


    @Bind(R.id.search_layout)
    RelativeLayout searchLayout;
    @Bind(R.id.pullLoadMoreRecyclerView)
    PullLoadMoreRecyclerView pullLoadMoreRecyclerView;

    private ClientRecordAdapter mAdapter;

    private List<ClientRecordModel> data = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_client_layout, null);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {

        data.add(new ClientRecordModel());
        data.add(new ClientRecordModel());
        data.add(new ClientRecordModel());
        data.add(new ClientRecordModel());
        data.add(new ClientRecordModel());
        data.add(new ClientRecordModel());
        data.add(new ClientRecordModel());
        data.add(new ClientRecordModel());
        data.add(new ClientRecordModel());
        data.add(new ClientRecordModel());
        data.add(new ClientRecordModel());
        data.add(new ClientRecordModel());
        data.add(new ClientRecordModel());
        data.add(new ClientRecordModel());
        data.add(new ClientRecordModel());
        data.add(new ClientRecordModel());
        data.add(new ClientRecordModel());
        data.add(new ClientRecordModel());
        pullLoadMoreRecyclerView.setLinearLayout();
        mAdapter = new ClientRecordAdapter(getActivity(), R.layout.fragment_client_clue_item_layout, data);
        pullLoadMoreRecyclerView.setAdapter(mAdapter);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


}
