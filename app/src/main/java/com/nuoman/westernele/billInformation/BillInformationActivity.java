package com.nuoman.westernele.billInformation;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nuoman.westernelectric.R;
import com.nuoman.westernele.billInformation.model.BillInformation;
import com.nuoman.westernele.common.BaseActivity;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BillInformationActivity extends BaseActivity implements Contract.BillInformationView {

    @Bind(R.id.title_left_tv)
    TextView title_left_tv;

    @Bind(R.id.title_mid_tv)
    TextView title_mid_tv;

    @Bind(R.id.rv_bill_information)
    PullLoadMoreRecyclerView rv_bill_information;

    private BillInformationAdapter billInformationAdapter;
    private BillInformationPresenterImp billInformationPresenterImp;

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
        billInformationPresenterImp = new BillInformationPresenterImp(this);
        billInformationAdapter = new BillInformationAdapter();
    }

    private void initLayout() {
        title_mid_tv.setText("账款信息");
        rv_bill_information.setLinearLayout();
    }

    private void bindListener() {
        rv_bill_information.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                billInformationPresenterImp.requestBillInformation(0);
            }

            @Override
            public void onLoadMore() {
                billInformationPresenterImp.requestBillInformation(1);

            }
        });
    }

    private void bindAdapter() {
        rv_bill_information.setAdapter(billInformationAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        billInformationPresenterImp.requestBillInformation(0);
    }

    @OnClick({R.id.title_left_tv})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.title_left_tv:
                finish();
                break;
        }
    }

    /**
     * 刷新列表
     *
     * @param data 刷新的数据
     */
    @Override
    public void refreshInformation(List<BillInformation> data) {
        rv_bill_information.setPullLoadMoreCompleted();
        billInformationAdapter.refreshData(data);
    }

    /**
     * 给列表加载更多
     *
     * @param data 添加的数据
     */
    @Override
    public void loadMoreInformation(List<BillInformation> data) {
        rv_bill_information.setPullLoadMoreCompleted();
        billInformationAdapter.loadMoreInformation(data);
    }


    class BillInformationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<BillInformation> mData;

        BillInformationAdapter() {
            mData = new ArrayList<>();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View inflateView = LayoutInflater.from(BillInformationActivity.this)
                    .inflate(R.layout.item_bill_information, parent, false);
            return new BillInformationViewHolder(inflateView);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            if (holder instanceof BillInformationViewHolder)
                ((BillInformationViewHolder) holder).initView(position % 3, mData.get(position));
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        void refreshData(List<BillInformation> data) {
            mData = data;
            notifyDataSetChanged();
        }

        void loadMoreInformation(List<BillInformation> data) {
            mData.addAll(data);
            notifyDataSetChanged();
        }

        class BillInformationViewHolder extends RecyclerView.ViewHolder {

            private ImageView iv_color;
            private TextView tv_one, tv_two, tv_three;

            BillInformationViewHolder(View itemView) {
                super(itemView);
                iv_color = (ImageView) itemView.findViewById(R.id.iv_color);
                tv_one = (TextView) itemView.findViewById(R.id.tv_one);
                tv_two = (TextView) itemView.findViewById(R.id.tv_two);
                tv_three = (TextView) itemView.findViewById(R.id.tv_three);
            }

            void initView(int count, BillInformation billInformation) {
                switch (count) {
                    case 0:
                        iv_color.setBackgroundColor(Color.parseColor("#fcaf3b"));
                        break;
                    case 1:
                        iv_color.setBackgroundColor(Color.parseColor("#88acd9"));
                        break;
                    case 2:
                        iv_color.setBackgroundColor(Color.parseColor("#8ec51f"));
                        break;
                }

                tv_one.setText(billInformation.getfDate());
                tv_two.setText(billInformation.getDataString());
                tv_three.setText(billInformation.getMoney());

            }
        }
    }

}
