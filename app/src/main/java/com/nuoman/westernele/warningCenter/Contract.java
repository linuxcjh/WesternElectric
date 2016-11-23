package com.nuoman.westernele.warningCenter;

import com.nuoman.westernele.warningCenter.model.WarningInformation;

import java.util.List;

/**
 * warningCenter的契约类
 * Created by 杨小过 on 2016/11/21.
 */

public interface Contract {

    interface WarningCenterView {
        void refreshInformation(List<WarningInformation> data);

        void loadMoreInformation(List<WarningInformation> data);

    }

    interface WarningCenterPresenter {
        void requestWarningInformation(int type);
    }

}
