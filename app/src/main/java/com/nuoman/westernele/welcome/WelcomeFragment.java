package com.nuoman.westernele.welcome;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nuoman.westernele.common.BaseFragment;
import com.nuoman.westernelectric.R;

/**
 * 欢迎页面第一页
 * Created by 杨小过 on 2016/11/23.
 */

public class WelcomeFragment extends BaseFragment {

    private TextView tv_big, tv_small;

    public static WelcomeFragment newInstance(int page) {
        WelcomeFragment welcomeFragment = new WelcomeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("page", page);
        welcomeFragment.setArguments(bundle);
        return welcomeFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_welcome_layout, container, false);
        tv_big = (TextView) rootView.findViewById(R.id.tv_big);
        tv_small = (TextView) rootView.findViewById(R.id.tv_small);
        int page = getArguments().getInt("page");
        initView(page);
        return rootView;
    }

    private void initView(int page) {
        switch (page) {
            case 0:
                tv_big.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.welcome_project_truck), null, null);
                tv_big.setText("项目实时追踪");
                tv_small.setText("项目进展变化实时准确追踪");
                break;
            case 1:
                tv_big.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.welcome_check), null, null);
                tv_big.setText("严格快速审查流程");
                tv_small.setText("严格监管每个流程的完成情况");
                break;
            case 2:
                tv_big.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.welcome_bill_information), null, null);
                tv_big.setText("账款信息及时通知");
                tv_small.setText("应收，付帐款信息及时提醒");
                break;
            case 3:
                tv_big.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.welcome_mobile_officing), null, null);
                tv_big.setText("移动办公更便捷");
                tv_small.setText("工作一手掌握");
                break;
        }
    }


}
