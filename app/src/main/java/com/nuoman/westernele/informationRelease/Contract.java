package com.nuoman.westernele.informationRelease;

import com.nuoman.westernele.informationRelease.model.ReleaseInformation;

import java.util.List;

/**
 * 信息发布契约类
 * Created by 杨小过 on 2016/11/22.
 */

public interface Contract {

    interface InformationReleaseView {
        void refreshInformation(List<ReleaseInformation> releaseInformationList);

        void loadMoreInformation(List<ReleaseInformation> releaseInformationList);

        void pullLoadMoreCompleted();
    }

    interface InformationReleasePresenter {
        void requestInformation(int type);
    }

}
