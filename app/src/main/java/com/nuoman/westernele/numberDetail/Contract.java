package com.nuoman.westernele.numberDetail;

import com.nuoman.westernele.numberDetail.model.BasicInformation;

/**
 * 契约类
 * Created by 杨小过 on 2016/11/22.
 */

public interface Contract {

    interface BasicInformationView {
        void setView(BasicInformation basicInformation);
    }

    interface BasicInformationPresenter {
        void requestBasicInformation(String number);
    }

}
