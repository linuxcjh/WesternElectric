package com.nuoman.westernele.numberDetail.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nuoman.westernelectric.R;
import com.nuoman.westernele.common.BaseFragment;

/**
 * 项目进度fragment
 * Created by 杨小过 on 2016/11/22.
 */

public class ProgressChartFragment extends BaseFragment {

    public static void newInstance(){
        
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_progress, container, false);
        return rootView;
    }
}
