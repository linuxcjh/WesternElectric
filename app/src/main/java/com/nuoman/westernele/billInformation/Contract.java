package com.nuoman.westernele.billInformation;

import com.nuoman.westernele.billInformation.model.BillInformation;

import java.util.List;

/**
 * 账款信息契约类
 * Created by 杨小过 on 2016/11/18.
 */

public interface Contract {

    interface BillInformationView {

        void setIsRead(String isRead);

        String getIsRead();

        void refreshInformation(List<BillInformation> data);

        void loadMoreInformation(List<BillInformation> data);

        void pullLoadMoreCompleted();
    }

    interface BillInformationPresenter {
        void requestBillInformation(int type);
    }
}
