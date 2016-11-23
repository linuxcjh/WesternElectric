package com.nuoman.westernele.informationDetail;

import com.nuoman.westernele.informationDetail.model.InformationDetail;

/**
 * 审核信息契约类
 * Created by 杨小过 on 2016/11/22.
 */

public interface Contract {

    interface InformationDetailView {
        void formatView(InformationDetail informationDetail);

        void checkFinish(String oper);
    }

    interface InformationDetailPresenter {
        void requestInformationDetail(String nodeId);

        void requestCheck(String nodeId,String oper);
    }

}
