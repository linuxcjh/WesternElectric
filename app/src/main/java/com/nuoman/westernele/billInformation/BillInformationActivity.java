package com.nuoman.westernele.billInformation;

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

public class BillInformationActivity extends BaseActivity {

    @Bind(R.id.title_left_tv)
    TextView title_left_tv;

    @Bind(R.id.title_mid_tv)
    TextView title_mid_tv;

    @Bind(R.id.rv_bill_information)
    PullLoadMoreRecyclerView rv_bill_information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_information_layout);
        ButterKnife.bind(this);

        initVariable();
        initLayout();
        bindListener();
        bindAdapter();
    }

    private void initVariable() {

    }

    private void initLayout() {
        title_mid_tv.setText("账款信息");
        rv_bill_information.setLinearLayout();
    }

    private void bindListener() {
        rv_bill_information.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {
            }
        });
    }

    private void bindAdapter() {

    }

    @OnClick({R.id.title_left_tv})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.title_left_tv:
                finish();
                break;
        }
    }

    class BillInformationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

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
