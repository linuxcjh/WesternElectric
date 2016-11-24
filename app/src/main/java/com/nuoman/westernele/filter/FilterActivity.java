package com.nuoman.westernele.filter;

import android.os.Bundle;

import com.nuoman.westernele.common.BaseActivity;

/**
 * AUTHOR: Alex
 * DATE: 25/12/2015 11:24
 */
public class FilterActivity extends BaseActivity implements IFilterAction {


//    @Bind(R.id.filter_layout)
//    FilterViewLayout filterLayout;
//    FilterPresenter filterPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.aty_filter_layout);
//        ButterKnife.bind(this);
//        ctivi
//        filterPresenter = new FilterPresenter(this);
//        filterPresenter.getFilterOfCustomer();
//
//        filterLayout.setTitleValue(new String[]{"筛选", "排序"});


    }

    @Override
    public void obtainFilterData(FilterDataModel data) {
//        filterLayout.obtainFilterData(data);

    }
}
