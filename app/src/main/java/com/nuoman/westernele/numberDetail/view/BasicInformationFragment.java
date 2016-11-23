package com.nuoman.westernele.numberDetail.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nuoman.westernelectric.R;
import com.nuoman.westernele.common.BaseFragment;
import com.nuoman.westernele.common.utils.AppTools;
import com.nuoman.westernele.numberDetail.Contract;
import com.nuoman.westernele.numberDetail.model.BasicInformation;
import com.nuoman.westernele.numberDetail.presenter.BasicInformationPresenterImp;

import butterknife.ButterKnife;

/**
 * 项目基本信息fragment
 * Created by 杨小过 on 2016/11/22.
 */

public class BasicInformationFragment extends BaseFragment implements Contract.BasicInformationView {

    private LinearLayout ll_contract_no, ll_internal_coordination_phone, ll_project_level;
    private TextView tv_product_number, tv_contract_no, tv_sign_contract_date, tv_contract_delivery_date,
            tv_product_category, tv_project_level, tv_delivery_date, tv_sale_manager,
            tv_sale_manager_phone, tv_internal_coordination, tv_internal_coordination_phone,
            tv_payment_method, tv_warranty_period, tv_payment_process;

    private BasicInformationPresenterImp basicInformationPresenterImp;
    private String number;


    public static BasicInformationFragment newInstance(String number) {
        BasicInformationFragment basicInformationFragment = new BasicInformationFragment();
        Bundle args = new Bundle();
        args.putString("number", number);
        basicInformationFragment.setArguments(args);
        return basicInformationFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_basic_informatio, container, false);
        ButterKnife.bind(rootView);
        initVariable();
        initView(rootView);
        return rootView;
    }

    private void initView(View rootView) {
        ll_contract_no = (LinearLayout) rootView.findViewById(R.id.ll_contract_no);
        ll_internal_coordination_phone = (LinearLayout) rootView.findViewById(R.id.ll_internal_coordination_phone);
        ll_project_level = (LinearLayout) rootView.findViewById(R.id.ll_project_level);
        tv_product_number = (TextView) rootView.findViewById(R.id.tv_product_number);
        tv_sign_contract_date = (TextView) rootView.findViewById(R.id.tv_sign_contract_date);
        tv_contract_delivery_date = (TextView) rootView.findViewById(R.id.tv_contract_delivery_date);
        tv_product_category = (TextView) rootView.findViewById(R.id.tv_product_category);
        tv_project_level = (TextView) rootView.findViewById(R.id.tv_project_level);
        tv_delivery_date = (TextView) rootView.findViewById(R.id.tv_delivery_date);
        tv_sale_manager = (TextView) rootView.findViewById(R.id.tv_sale_manager);
        tv_sale_manager_phone = (TextView) rootView.findViewById(R.id.tv_sale_manager_phone);
        tv_internal_coordination = (TextView) rootView.findViewById(R.id.tv_internal_coordination);
        tv_internal_coordination_phone = (TextView) rootView.findViewById(R.id.tv_internal_coordination_phone);
        tv_contract_no = (TextView) rootView.findViewById(R.id.tv_contract_no);
        tv_payment_method = (TextView) rootView.findViewById(R.id.tv_payment_method);
        tv_warranty_period = (TextView) rootView.findViewById(R.id.tv_warranty_period);
        tv_payment_process = (TextView) rootView.findViewById(R.id.tv_payment_process);
    }

    private void initVariable() {
        basicInformationPresenterImp = new BasicInformationPresenterImp(this);
        number = getArguments().getString("number");
    }

    @Override
    public void onResume() {
        super.onResume();
        basicInformationPresenterImp.requestBasicInformation(number);
    }

    @Override
    public void setView(BasicInformation basicInformation) {
        tv_product_number.setText(basicInformation.getOrderNo());
        tv_contract_no.setText(basicInformation.getContractNo());
        tv_sign_contract_date.setText(basicInformation.getContractSignDate());
        tv_contract_delivery_date.setText(basicInformation.getContractDeliveryDate());
        tv_product_category.setText(basicInformation.getProductType());
        tv_project_level.setText(basicInformation.getProjectLevel());
        tv_delivery_date.setText(basicInformation.getSendDate());
        tv_sale_manager.setText(basicInformation.getSaleManager());
        tv_sale_manager_phone.setText(basicInformation.getSaleManagerTel());
        tv_internal_coordination.setText(basicInformation.getInnerManager());
        tv_internal_coordination_phone.setText(basicInformation.getInnerManagerTel());
        tv_payment_method.setText(basicInformation.getPayType());
        tv_warranty_period.setText(basicInformation.getWarrantyExplaination());
        tv_payment_process.setText(basicInformation.getPayProcess());

        if (AppTools.getUser().getRoleId().equals("5")) {
            ll_contract_no.setVisibility(View.GONE);
            ll_internal_coordination_phone.setVisibility(View.GONE);
            ll_project_level.setVisibility(View.GONE);
        }

    }
}
