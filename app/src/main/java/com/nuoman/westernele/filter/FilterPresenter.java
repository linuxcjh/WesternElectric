package com.nuoman.westernele.filter;

import com.nuoman.westernele.api.NuoManService;
import com.nuoman.westernele.common.BasePresenter;

import java.util.Map;

/**
 * Created by 陈建辉 on 2015/12/23.
 */
public class FilterPresenter extends BasePresenter {

    private IFilterAction iFilterAction;

    public FilterPresenter(IFilterAction iFilterAction) {
        this.iFilterAction = iFilterAction;
        isShowProgressDialog = false;
    }

    @Override
    public void onResponse(String methodName, Object object, int status, Map<String, String> parameterMap) {
        if (status == 1) {
            switch (methodName) {
//                case XxbService.GETFILTEROFCUSTOMER:
//                case XxbService.GETFILTEROFCONTACTS:
//                case XxbService.GETFILTEROFSALESLEADS:
//                case XxbService.GETFILTEROFFLOW:
//                case XxbService.GETFILTEROFBNSOPP:
//                case XxbService.GETFILTEROFCONTRACT:
//                case XxbService.GETFILTEROFRAPPORTEXAMINE:
//                case XxbService.GETFILTEROFCUSTOMERPAYMENTPLAN:
                case NuoManService.BILL_INFORMATION:
                    obtainFilterData(object);
                    break;
            }
        }
    }

    //    @Override
//    public void onResponse(String methodName, Object object, int status) {
//
//        if (status == 1) {
//            switch (methodName) {
////                case XxbService.GETFILTEROFCUSTOMER:
////                case XxbService.GETFILTEROFCONTACTS:
////                case XxbService.GETFILTEROFSALESLEADS:
////                case XxbService.GETFILTEROFFLOW:
////                case XxbService.GETFILTEROFBNSOPP:
////                case XxbService.GETFILTEROFCONTRACT:
////                case XxbService.GETFILTEROFRAPPORTEXAMINE:
////                case XxbService.GETFILTEROFCUSTOMERPAYMENTPLAN:
//                case NuoManService.BILL_INFORMATION:
//                    obtainFilterData(object);
//                    break;
//            }
//        }
//    }

    /**
     * 处理返回数据
     *
     * @param object
     */

    private void obtainFilterData(Object object) {
        FilterDataModel model = (FilterDataModel) object;
        iFilterAction.obtainFilterData(model);
    }

//    /**
//     * 客户筛选
//     */
//    public void getFilterOfCustomer() {
//        commonApi(XxbService.GETFILTEROFCUSTOMER, new TypeToken<FilterDataModel>() {
//        });
//    }
//
//    /**
//     * 客户筛选
//     */
//    public void getFilterOfContacts() {
//        commonApi(XxbService.GETFILTEROFCONTACTS, new TypeToken<FilterDataModel>() {
//        });
//    }
//
//    /**
//     * 客户筛选
//     */
//    public void getFilterOfSalesLeads() {
//        commonApi(XxbService.GETFILTEROFSALESLEADS, new TypeToken<FilterDataModel>() {
//        });
//    }
//
//    /**
//     * 审批筛选
//     */
//    public void getFilterOfFlow() {
//        commonApi(XxbService.GETFILTEROFFLOW, new TypeToken<FilterDataModel>() {
//        });
//    }
//
//    /**
//     * 商机筛选
//     */
//    public void getFilterOfBnsOpp() {
//        commonApi(XxbService.GETFILTEROFBNSOPP, new TypeToken<FilterDataModel>() {
//        });
//    }
//
//    /**
//     * 合同筛选
//     */
//    public void getFilterOfBusinessManage() {
//        commonApi(XxbService.GETFILTEROFBUSINESSMANAGE, new TypeToken<FilterDataModel>() {
//        });
//    }
//
//    /**
//     * 企业管理合同筛选
//     */
//    public void getFilterOfContract() {
//        commonApi(XxbService.GETFILTEROFCONTRACT, new TypeToken<FilterDataModel>() {
//        });
//    }
//
//    /**
//     * 回款筛选
//     */
//    public void getFilterOfCustomerPaymentPlan() {
//        commonApi(XxbService.GETFILTEROFCUSTOMERPAYMENTPLAN, new TypeToken<FilterDataModel>() {
//        });
//    }
//
//    /**
//     * 审核 —— 审核列表 —— 筛选条件 接口
//     */
//    public void getFilterOfRapportExamine() {
//        commonApi(XxbService.GETFILTEROFRAPPORTEXAMINE, new TypeToken<FilterDataModel>() {
//        });
//    }
}
