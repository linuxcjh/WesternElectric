package com.nuoman.westernele.projectSummary;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nuoman.westernele.common.BaseActivity;
import com.nuoman.westernele.projectSummary.model.ProjectData;
import com.nuoman.westernelectric.R;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProjectSummaryActivity extends BaseActivity implements Contract.ProjectSummaryView {

    @Bind(R.id.lrv_project_summary)
    PullLoadMoreRecyclerView lrv_project_summary;
    @Bind(R.id.title_mid_tv)
    TextView title_mid_tv;

    private ProjectSummaryPresenterImp projectSummaryPresenterImp;
    private ProjectSummaryAdapter projectSummaryAdapter;
    private int kind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_summary_layout);
        ButterKnife.bind(this);
        initVariable();
        initView();
        bindListener();
        bindAdapter();
    }

    private void initVariable() {
        projectSummaryPresenterImp = new ProjectSummaryPresenterImp(this);
        projectSummaryAdapter = new ProjectSummaryAdapter();
        kind = getIntent().getIntExtra("kind", 0);
    }

    private void initView() {
        lrv_project_summary.setLinearLayout();
        switch (kind) {
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

    private void bindListener() {
        lrv_project_summary.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                projectSummaryPresenterImp.requestProject(kind, 0);
            }

            @Override
            public void onLoadMore() {
                projectSummaryPresenterImp.requestProject(kind, 1);
            }
        });
    }

    private void bindAdapter() {
        lrv_project_summary.setAdapter(projectSummaryAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        projectSummaryPresenterImp.requestProject(kind, 0);
    }

    @OnClick({R.id.title_left_tv})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_left_tv:
                finish();
                break;
        }
    }

    @Override
    public void refreshList(List<ProjectData> projects) {
        projectSummaryAdapter.refresh(projects);
        lrv_project_summary.setPullLoadMoreCompleted();
    }

    @Override
    public void loadMoreList(List<ProjectData> projects) {
        projectSummaryAdapter.loadMore(projects);
        lrv_project_summary.setPullLoadMoreCompleted();
    }

    private class ProjectSummaryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<ProjectData> mProjects;

        ProjectSummaryAdapter() {
            mProjects = new ArrayList<>();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View inflateView = LayoutInflater.from(ProjectSummaryActivity.this)
                    .inflate(R.layout.item_project_summary, parent, false);
            return new ProjectSummaryViewHolder(inflateView);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ProjectSummaryViewHolder projectSummaryViewHolder = (ProjectSummaryViewHolder) holder;
            projectSummaryViewHolder.formatView(mProjects.get(position));
        }

        @Override
        public int getItemCount() {
            return mProjects.size();
        }

        /**
         * 刷新列表
         *
         * @param projects 要刷新的列表
         */
        void refresh(List<ProjectData> projects) {
            mProjects = projects;
            notifyDataSetChanged();
        }

        /**
         * 加载更多
         *
         * @param projects 要加载的列表
         */
        void loadMore(List<ProjectData> projects) {
            mProjects.addAll(projects);
            notifyDataSetChanged();
        }

        private class ProjectSummaryViewHolder extends RecyclerView.ViewHolder {

            //            ImageView iv_project_icon;
            TextView /*tv_project_name_summary, tv_project_time_summary,*/
                    tv_project_manage_people_summary;

            ProjectSummaryViewHolder(View itemView) {
                super(itemView);
                /*iv_project_icon = (ImageView) itemView.findViewById(R.id.iv_project_icon);
                tv_project_name_summary = (TextView) itemView.findViewById(R.id.tv_project_name_summary);
                tv_project_time_summary = (TextView) itemView.findViewById(R.id.tv_project_time_summary);*/
                tv_project_manage_people_summary = (TextView) itemView.findViewById(R.id.tv_project_manage_people_summary);
            }

            void formatView(ProjectData projectData) {
                /*AppTools.setImageViewPicture(ProjectSummaryActivity.this,
                        projectData.getProjectPic(), iv_project_icon);
                tv_project_name_summary.setText(projectData.getProjectName());
                tv_project_time_summary.setText(projectData.getFdate());*/
                tv_project_manage_people_summary.setText(projectData.getSaleOrderName());
            }
        }
    }
}
