package com.nuoman.westernele.informationRelease;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nuoman.tabletattendance.R;
import com.nuoman.westernele.common.BaseActivity;
import com.nuoman.westernele.informationDetail.InformationDetailActivity;
import com.nuoman.westernele.informationRelease.model.ReleaseInformation;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InformationReleaseActivity extends BaseActivity implements Contract.InformationReleaseView {

    @Bind(R.id.rv_information_release)
    PullLoadMoreRecyclerView rv_information_release;

    @Bind(R.id.title_mid_tv)
    TextView title_mid_tv;

    private InformationReleasePresenterImp informationReleasePresenterImp;
    private InformationReleaseAdapter informationReleaseAdapter;

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
        informationReleasePresenterImp = new InformationReleasePresenterImp(this);
        informationReleaseAdapter = new InformationReleaseAdapter();
    }

    private void initLayout() {
        title_mid_tv.setText("信息发布");
        rv_information_release.setLinearLayout();
    }

    private void bindListener() {
        rv_information_release.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                informationReleasePresenterImp.requestInformation(0);
            }

            @Override
            public void onLoadMore() {
                informationReleasePresenterImp.requestInformation(1);
            }
        });
    }

    private void bindAdapter() {
        rv_information_release.setAdapter(informationReleaseAdapter);
        rv_information_release.getRecyclerView()
                .addOnItemTouchListener(new ItemClickListener());
    }

    @Override
    protected void onResume() {
        super.onResume();
        informationReleasePresenterImp.requestInformation(0);
    }

    @OnClick({R.id.title_left_tv})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.title_left_tv:
                finish();
                break;
        }
    }

    /**
     * 单个项目点击操作
     *
     * @param nodeId 传入下级页面的数据
     */
    public void itemClick(String nodeId) {
        Intent intent = new Intent(this, InformationDetailActivity.class);
        intent.putExtra("nodeId", nodeId);
        startActivity(intent);
    }

    @Override
    public void refreshInformation(List<ReleaseInformation> releaseInformationList) {
        informationReleaseAdapter.refresh(releaseInformationList);
        rv_information_release.setPullLoadMoreCompleted();
    }

    @Override
    public void loadMoreInformation(List<ReleaseInformation> releaseInformationList) {
        informationReleaseAdapter.loadMore(releaseInformationList);
        rv_information_release.setPullLoadMoreCompleted();
    }

    class InformationReleaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<ReleaseInformation> mReleaseInformationList;

        InformationReleaseAdapter() {
            mReleaseInformationList = new ArrayList<>();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View inflateView = LayoutInflater.from(InformationReleaseActivity.this)
                    .inflate(R.layout.item_information_release, parent, false);
            return new InformationReleaseViewHolder(inflateView);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((InformationReleaseViewHolder) holder).format(mReleaseInformationList.get(position));
        }

        @Override
        public int getItemCount() {
            return mReleaseInformationList.size();
        }

        /**
         * 刷新列表
         *
         * @param releaseInformationList 要刷新的列表
         */
        void refresh(List<ReleaseInformation> releaseInformationList) {
            mReleaseInformationList = releaseInformationList;
            notifyDataSetChanged();
        }

        /**
         * 加载更多
         *
         * @param releaseInformationList 要加载的列表
         */
        void loadMore(List<ReleaseInformation> releaseInformationList) {
            mReleaseInformationList.addAll(releaseInformationList);
            notifyDataSetChanged();
        }

        /**
         * 获取每项数据
         *
         * @param position 位置
         * @return 数据
         */
        ReleaseInformation getItemData(int position) {
            return mReleaseInformationList.get(position);
        }

        class InformationReleaseViewHolder extends RecyclerView.ViewHolder {

            private TextView tv_date, tv_project_name, tv_status;

            InformationReleaseViewHolder(View itemView) {
                super(itemView);
                tv_date = (TextView) itemView.findViewById(R.id.tv_date);
                tv_project_name = (TextView) itemView.findViewById(R.id.tv_project_name);
                tv_status = (TextView) itemView.findViewById(R.id.tv_status);
            }

            void format(ReleaseInformation releaseInformation) {
                tv_date.setText(releaseInformation.getFdate());
                tv_project_name.setText(releaseInformation.getProjectName());
                switch (releaseInformation.getProjectState()) {
                    case "0":
                        tv_status.setText("待审核");
                        break;
                    case "1":
                        tv_status.setText("已通过");
                        break;
                    case "2":
                        tv_status.setText("未通过");
                        break;
                }
            }
        }
    }


    private class ItemClickListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;

        ItemClickListener() {
            gestureDetector = new GestureDetector(InformationReleaseActivity.this,
                    new GestureDetector.SimpleOnGestureListener() {
                        @Override
                        public boolean onSingleTapUp(MotionEvent e) {
                            return true;
                        }
                    });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View childView = rv.findChildViewUnder(e.getX(), e.getY());
            if (childView != null && gestureDetector.onTouchEvent(e)) {
                InformationReleaseAdapter informationReleaseAdapter = (InformationReleaseAdapter) rv.getAdapter();
                if (informationReleaseAdapter != null) {
                    /*
                    单项点击事件
                     */
                    itemClick(informationReleaseAdapter.getItemData(
                            rv.getChildLayoutPosition(childView))
                            .getNodeId());

                } else {
                    throw new RuntimeException("NoAdapter");
                }
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

}
