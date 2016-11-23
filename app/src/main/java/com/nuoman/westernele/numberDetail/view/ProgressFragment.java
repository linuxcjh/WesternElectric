package com.nuoman.westernele.numberDetail.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nuoman.tabletattendance.R;
import com.nuoman.westernele.common.BaseFragment;

/**
 * 项目进度fragment
 * Created by 杨小过 on 2016/11/22.
 */

public class ProgressFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_progress, container, false);
        return rootView;
    }
}