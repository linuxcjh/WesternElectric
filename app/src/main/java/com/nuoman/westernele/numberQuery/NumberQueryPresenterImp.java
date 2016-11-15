package com.nuoman.westernele.numberQuery;

/**
 * numberQuery的presenter
 * Created by 杨小过 on 2016/11/14.
 */

public class NumberQueryPresenterImp implements Contract.NumberQueryPresenter {

    private Contract.NumberQueryView mNumberQueryView;

    public NumberQueryPresenterImp(Contract.NumberQueryView numberQueryView){
        mNumberQueryView=numberQueryView;
    }

    @Override
    public void queryNumber(String number) {
    }
}
