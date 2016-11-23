package com.nuoman.westernele.warningCenter;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nuoman.westernelectric.R;
import com.nuoman.westernele.common.BaseActivity;
import com.nuoman.westernele.warningCenter.model.WarningInformation;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WarningCenterActivity extends BaseActivity implements Contract.WarningCenterView {

    @Bind(R.id.title_mid_tv)
    TextView title_mid_tv;
    @Bind(R.id.lrv_warning_center)
    PullLoadMoreRecyclerView lrv_warning_center;

    private WarningCenterPresenterImp warningCenterPresenterImp;
    private WarningCenterAdapter warningCenterAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warning_center_layout);
        ButterKnife.bind(this);
        initVariable();
        initView();
        bindListener();
        bindAdapter();
    }

    private void initVariable() {
        warningCenterPresenterImp = new WarningCenterPresenterImp(this);
        warningCenterAdapter = new WarningCenterAdapter();
    }

    private void initView() {
        title_mid_tv.setText("预警信息");
        lrv_warning_center.setLinearLayout();
        lrv_warning_center.setFooterViewText("加载中...");
    }

    private void bindListener() {
        lrv_warning_center.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                warningCenterPresenterImp.requestWarningInformation(0);
            }

            @Override
            public void onLoadMore() {
                warningCenterPresenterImp.requestWarningInformation(1);
            }
        });
    }

    private void bindAdapter() {
        lrv_warning_center.setAdapter(warningCenterAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        warningCenterPresenterImp.requestWarningInformation(0);

    }

    @OnClick({R.id.title_left_tv})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.title_left_tv:
                finish();
                break;
        }
    }

    @Override
    public void refreshInformation(List<WarningInformation> data) {
        lrv_warning_center.setPullLoadMoreCompleted();
        warningCenterAdapter.refreshData(data);
    }

    @Override
    public void loadMoreInformation(List<WarningInformation> data) {
        lrv_warning_center.setPullLoadMoreCompleted();
        warningCenterAdapter.loadMoreInformation(data);
    }

    class WarningCenterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<WarningInformation> mData;

        WarningCenterAdapter() {
            mData = new ArrayList<>();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View inflateView = LayoutInflater.from(WarningCenterActivity.this)
                    .inflate(R.layout.item_warning_center, parent, false);
            return new WarningCenterViewHolder(inflateView);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((WarningCenterViewHolder) holder).initView(mData.get(position));
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        void refreshData(List<WarningInformation> data) {
            mData = data;
            notifyDataSetChanged();
        }

        void loadMoreInformation(List<WarningInformation> data) {
            mData.addAll(data);
            notifyDataSetChanged();
        }

        class WarningCenterViewHolder extends RecyclerView.ViewHolder {

            private TextView tv_warning_time, tv_warning, tv_project_name, tv_status;

            WarningCenterViewHolder(View itemView) {
                super(itemView);
                tv_warning = (TextView) itemView.findViewById(R.id.tv_warning);
                tv_warning_time = (TextView) itemView.findViewById(R.id.tv_warning_time);
                tv_project_name = (TextView) itemView.findViewById(R.id.tv_project_name);
                tv_status = (TextView) itemView.findViewById(R.id.tv_status);
            }

            void initView(WarningInformation warningInformation) {
                tv_warning_time.setText(warningInformation.getfDate());
                if (warningInformation.getAlertKind().equals("0")) {
                    tv_warning.setText("拖期预警");
                    tv_warning.setCompoundDrawablesWithIntrinsicBounds(
                            WarningCenterActivity.this.getResources().getDrawable(R.drawable.warning_icon),
                            null, null, null);
                    tv_project_name.setText(warningInformation.getProjectName());
                    tv_status.setText(warningInformation.getNoticeString());
                    tv_status.setVisibility(View.VISIBLE);
                } else {
                    tv_warning.setText("通知");
                    tv_warning.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                    tv_project_name.setText(warningInformation.getNoticeString());
                    tv_status.setVisibility(View.GONE);
                }

            }
        }
    }
}
