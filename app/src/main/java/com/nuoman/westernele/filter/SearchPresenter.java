package com.nuoman.westernele.filter;

import com.nuoman.westernele.api.NuoManService;
import com.nuoman.westernele.common.BasePresenter;

import java.util.List;
import java.util.Map;

/**
 * Created by 陈建辉 on 2015/12/23.
 */
public class SearchPresenter extends BasePresenter {

    private ISearchAction iSearchAction;

    public SearchPresenter(ISearchAction iSearchAction) {
        this.iSearchAction = iSearchAction;
    }

    @Override
    public void onResponse(String methodName, Object object, int status, Map<String, String> parameterMap) {
        if (status == 1) {
            switch (methodName) {
                case NuoManService.BILL_INFORMATION:
                    obtainSearchData(object);
                    break;
            }
        }
    }


    /**
     * 处理返回数据
     *
     * @param object
     */

    private void obtainSearchData(Object object) {
        List<SelectItemModel> model = (List<SelectItemModel>) object;
        iSearchAction.obtainSearchData(model);
    }


    /**
     * 客户搜索
     */
    public void autoCompleteOfSearcher() {
//        commonApi(NuoManService.BILL_INFORMATION, new TypeToken<List<SelectItemModel>>() {
//        });
    }


}
