package com.nuoman.westernele.warningCenter;

import com.nuoman.westernele.warningCenter.model.WarningInformation;

import java.util.List;

/**
 * warningCenter的契约类
 * Created by 杨小过 on 2016/11/21.
 */

public interface Contract {

    interface WarningCenterView {

        void setIsRead(String isRead);

        String getIsRead();

        void refreshInformation(List<WarningInformation> data);

        void loadMoreInformation(List<WarningInformation> data);

        void pullLoadMoreCompleted();

    }

    interface WarningCenterPresenter {
        void requestWarningInformation(int type);
    }

}
