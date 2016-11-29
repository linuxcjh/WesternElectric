package com.nuoman.westernele.projectSummary;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nuoman.westernele.common.BaseActivity;
import com.nuoman.westernelectric.R;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ProjectSummaryActivity extends BaseActivity implements Contract.ProjectSummaryView {

    @Bind(R.id.lrv_project_summary)
    PullLoadMoreRecyclerView lrv_project_summary;
    @Bind(R.id.title_mid_tv)
    TextView title_mid_tv;

    private ProjectSummaryPresenterImp projectSummaryPresenterImp;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_summary_layout);
        ButterKnife.bind(this);
        initVariable();
        initView();
        bindAdapter();
    }

    private void initVariable() {
        projectSummaryPresenterImp = new ProjectSummaryPresenterImp(this);
        type = getIntent().getIntExtra("type", 0);
    }

    private void initView() {
        lrv_project_summary.setLinearLayout();
        switch (type) {
            case 0:
                title_mid_tv.setText("未进行");
                break;
            case 1:
                title_mid_tv.setText("生产中");
                break;
            case 2:
                title_mid_tv.setText("已完工");
                break;
        }
    }

    private void bindAdapter() {

    }

    class ProjectSummaryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

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
