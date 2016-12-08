package com.nuoman.westernele.common;

import com.google.gson.reflect.TypeToken;
import com.nuoman.westernele.common.utils.AppTools;

import java.util.HashMap;
import java.util.Map;

/**
 * 【CommonPresenter presenter】
 * AUTHOR: Alex
 * DATE: 22/10/2015 10:57
 */
public class CommonHomePresenter extends BasePresenter {


    public ICommonHomeAction iCommonAction;

    public CommonHomePresenter(ICommonHomeAction iCommonAction) {
        this.iCommonAction = iCommonAction;
    }


    /**
     * 有参公共方法调用
     *
     * @param methodName
     * @param model
     * @param typeToken
     */
    public void invokeInterfaceObtainData(boolean isPost, String part, String methodName, Object model, TypeToken<?> typeToken) {
        if (model == null) {
            commonApi(isPost, part, methodName, new HashMap<String, String>(), typeToken);
        } else {
            commonApi(isPost, part, methodName, AppTools.toMap(model), typeToken);

        }
    }

    @Override
    public void onResponse(String methodName, Object object, int status, Map<String, String> parameterMap) {

        switch (methodName) {
            default:
                iCommonAction.obtainDataHome(object, methodName, status, parameterMap);
                break;

        }

    }


}
