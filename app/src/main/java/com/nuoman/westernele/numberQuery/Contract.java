package com.nuoman.westernele.numberQuery;

import com.nuoman.westernele.numberQuery.model.Number;

import java.util.List;

/**
 * 号码查询契约类
 * Created by 杨小过 on 2016/11/14.
 */

public interface Contract {

    interface NumberQueryPresenter {
        void queryNumber(String number);
    }

    interface NumberQueryView {
        void refreshNumber(List<Number> data);

        void showNotification(String msg);
    }

}
