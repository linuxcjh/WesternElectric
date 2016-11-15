package com.nuoman.westernele.informationRelease;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nuoman.tabletattendance.R;
import com.nuoman.westernele.common.BaseActivity;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InformationReleaseActivity extends BaseActivity {

    @Bind(R.id.rv_information_release)
    PullLoadMoreRecyclerView rv_information_release;

    @Bind(R.id.title_mid_tv)
    TextView title_mid_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_release_layout);
        ButterKnife.bind(this);

        initVariable();
        initLayout();
        bindListener();
        bindAdapter();
    }

    private void initVariable() {


    }

    private void initLayout() {
        title_mid_tv.setText("信息发布");
        rv_information_release.setLinearLayout();
    }

    private void bindListener() {

    }

    private void bindAdapter() {

    }

    @OnClick({R.id.title_left_tv})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.title_left_tv:
                finish();
                break;
        }
    }

    class InformationReleaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }

}
