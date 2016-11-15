package com.nuoman.westernele.numberQuery;

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
        void updateSearchReason(List<NumberEntity> searchReasons);

    }

}
