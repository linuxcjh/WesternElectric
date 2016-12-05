package com.nuoman.westernele.numberDetail.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nuoman.westernele.common.BaseFragment;
import com.nuoman.westernele.numberDetail.Contract;
import com.nuoman.westernele.numberDetail.model.NodeStatus;
import com.nuoman.westernele.numberDetail.model.ProgressChart;
import com.nuoman.westernele.numberDetail.presenter.ProgressChartPresenterImp;
import com.nuoman.westernele.projectmanage.IClearToHome;
import com.nuoman.westernelectric.R;

/**
 * 项目进度fragment
 * Created by 杨小过 on 2016/11/22.
 */

public class ProgressChartFragment extends BaseFragment implements Contract.ProgressChartView {

    private TextView tv_technology, tv_technology_point, tv_purchase, tv_purchase_point,
            tv_product, tv_product_point, tv_shipment, tv_shipment_point,
            tv_left, tv_right, tv_progress_chart_one, tv_progress_chart_two,
            tv_progress_chart_three, tv_progress_chart_percent;
    private ViewPager vp_progress_chart;
    private ImageView toHomeIv;

    private ProgressChartPresenterImp progressChartPresenterImp;


    public static ProgressChartFragment newInstance(String orderId) {
        ProgressChartFragment progressChartFragment = new ProgressChartFragment();
        Bundle bundle = new Bundle();
        bundle.putString("orderId", orderId);
        progressChartFragment.setArguments(bundle);
        return progressChartFragment;
    }

    IClearToHome iClearToHome;


    public void setIClearToHomeListener(IClearToHome iClearToHome) {
        this.iClearToHome = iClearToHome;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_progress, container, false);
        initVariable();
        initView(rootView);
        bindListener();
        return rootView;
    }

    private void initVariable() {
        progressChartPresenterImp = new ProgressChartPresenterImp(this);
    }

    private void initView(View rootView) {
        tv_technology = (TextView) rootView.findViewById(R.id.tv_technology);
        tv_technology_point = (TextView) rootView.findViewById(R.id.tv_technology_point);
        tv_purchase = (TextView) rootView.findViewById(R.id.tv_purchase);
        tv_purchase_point = (TextView) rootView.findViewById(R.id.tv_purchase_point);
        tv_product = (TextView) rootView.findViewById(R.id.tv_product);
        tv_product_point = (TextView) rootView.findViewById(R.id.tv_product_point);
        tv_shipment = (TextView) rootView.findViewById(R.id.tv_shipment);
        tv_shipment_point = (TextView) rootView.findViewById(R.id.tv_shipment_point);
        tv_left = (TextView) rootView.findViewById(R.id.tv_left);
        tv_right = (TextView) rootView.findViewById(R.id.tv_right);
        tv_progress_chart_one = (TextView) rootView.findViewById(R.id.tv_progress_chart_one);
        tv_progress_chart_two = (TextView) rootView.findViewById(R.id.tv_progress_chart_two);
        tv_progress_chart_three = (TextView) rootView.findViewById(R.id.tv_progress_chart_three);
        tv_progress_chart_percent = (TextView) rootView.findViewById(R.id.tv_progress_chart_percent);
        vp_progress_chart = (ViewPager) rootView.findViewById(R.id.vp_progress_chart);
        toHomeIv = (ImageView) rootView.findViewById(R.id.to_home_iv);

    }

    private void bindListener() {
        tv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressChartPresenterImp.lastPage();
            }
        });
        tv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressChartPresenterImp.nextPage();
            }
        });

        tv_progress_chart_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressChartPresenterImp.lastPage();
            }
        });
        tv_progress_chart_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressChartPresenterImp.nextPage();
            }
        });

        tv_technology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setNodeDetail(0);
            }
        });

        tv_purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setNodeDetail(1);
            }
        });

        tv_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setNodeDetail(2);
            }
        });

        tv_shipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setNodeDetail(3);
            }
        });

        vp_progress_chart.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        tv_progress_chart_one.setText("运装");
                        tv_progress_chart_two.setText("技术");
                        tv_progress_chart_three.setText("采购");
                        break;
                    case 1:
                        tv_progress_chart_one.setText("技术");
                        tv_progress_chart_two.setText("采购");
                        tv_progress_chart_three.setText("生产");
                        break;
                    case 2:
                        tv_progress_chart_one.setText("采购");
                        tv_progress_chart_two.setText("生产");
                        tv_progress_chart_three.setText("运装");
                        break;
                    case 3:
                        tv_progress_chart_one.setText("生产");
                        tv_progress_chart_two.setText("运装");
                        tv_progress_chart_three.setText("技术");
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        toHomeIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClearToHome.clearToHome();
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        progressChartPresenterImp.requestProgressChart(getArguments().getString("orderId"));
    }

    @Override
    public void setView(ProgressChart progressChart) {
        switch (progressChart.getPurchaseStatus()) {
            case "ok":
                tv_purchase.setBackgroundResource(R.drawable.progress_chart_finished);
                break;
            case "errorfinish":
                tv_purchase.setBackgroundResource(R.drawable.progress_chart_finished);
                tv_purchase_point.setVisibility(View.VISIBLE);
                break;
            case "errordoing":
                tv_purchase.setBackgroundResource(R.drawable.progress_chart_ongoing);
                tv_purchase_point.setVisibility(View.VISIBLE);
                break;
            case "doing":
                tv_purchase.setBackgroundResource(R.drawable.progress_chart_ongoing);
                break;
            case "unstart":
                tv_purchase.setBackgroundResource(R.drawable.progress_chart_not_start);
                break;
        }
        switch (progressChart.getProduceStatus()) {
            case "ok":
                tv_product.setBackgroundResource(R.drawable.progress_chart_finished);
                break;
            case "errorfinish":
                tv_product.setBackgroundResource(R.drawable.progress_chart_finished);
                tv_product_point.setVisibility(View.VISIBLE);
                break;
            case "errordoing":
                tv_product.setBackgroundResource(R.drawable.progress_chart_ongoing);
                tv_product_point.setVisibility(View.VISIBLE);
                break;
            case "doing":
                tv_product.setBackgroundResource(R.drawable.progress_chart_ongoing);
            case "unstart":
                tv_product.setBackgroundResource(R.drawable.progress_chart_not_start);
                break;
        }
        switch (progressChart.getTechStatus()) {
            case "ok":
                tv_technology.setBackgroundResource(R.drawable.progress_chart_finished);
                break;
            case "errorfinish":
                tv_technology.setBackgroundResource(R.drawable.progress_chart_finished);
                tv_technology_point.setVisibility(View.VISIBLE);
                break;
            case "errordoing":
                tv_technology.setBackgroundResource(R.drawable.progress_chart_ongoing);
                tv_technology_point.setVisibility(View.VISIBLE);
                break;
            case "doing":
                tv_technology.setBackgroundResource(R.drawable.progress_chart_ongoing);
            case "unstart":
                tv_technology.setBackgroundResource(R.drawable.progress_chart_not_start);
                break;
        }
        switch (progressChart.getTransportStatus()) {
            case "ok":
                tv_shipment.setBackgroundResource(R.drawable.progress_chart_finished);
                break;
            case "errorfinish":
                tv_shipment.setBackgroundResource(R.drawable.progress_chart_finished);
                tv_shipment_point.setVisibility(View.VISIBLE);
                break;
            case "errordoing":
                tv_shipment.setBackgroundResource(R.drawable.progress_chart_ongoing);
                tv_shipment_point.setVisibility(View.VISIBLE);
                break;
            case "doing":
                tv_shipment.setBackgroundResource(R.drawable.progress_chart_ongoing);
            case "unstart":
                tv_shipment.setBackgroundResource(R.drawable.progress_chart_not_start);
                break;
        }
        tv_progress_chart_percent.setText(String.format
                ("%s%%", progressChart.getTotalCompletePercent()));
        if(getActivity()!=null){
            vp_progress_chart.setAdapter(new ProgressChartAdapter(getActivity().getSupportFragmentManager(),
                    progressChart.getNodeStatus()));
        }


    }

    @Override
    public void setNodeDetail(int index) {
        switch (index) {
            case 0:
                vp_progress_chart.setCurrentItem(0);
                break;
            case 1:
                vp_progress_chart.setCurrentItem(1);
                break;
            case 2:
                vp_progress_chart.setCurrentItem(2);
                break;
            case 3:
                vp_progress_chart.setCurrentItem(3);
                break;
        }
    }

    class ProgressChartAdapter extends FragmentPagerAdapter {

        private NodeStatus mNodeStatus;

        ProgressChartAdapter(FragmentManager fm, NodeStatus nodeStatus) {
            super(fm);
            mNodeStatus = nodeStatus;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return ProgressChartPartFragment.newInstance(mNodeStatus.getTech());
                case 1:
                    return ProgressChartPartFragment.newInstance(mNodeStatus.getPurchase());
                case 2:
                    return ProgressChartPartFragment.newInstance(mNodeStatus.getProduction());
                case 3:
                    return ProgressChartPartFragment.newInstance(mNodeStatus.getTransport());
            }
            return null;
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
