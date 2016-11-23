package com.nuoman.westernele.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.nuoman.tabletattendance.R;
import com.nuoman.westernele.api.NuoManService;
import com.nuoman.westernele.billInformation.BillInformationActivity;
import com.nuoman.westernele.common.BaseFragment;
import com.nuoman.westernele.common.CommonPresenter;
import com.nuoman.westernele.common.ICommonAction;
import com.nuoman.westernele.common.utils.AppTools;
import com.nuoman.westernele.components.MyGridView;
import com.nuoman.westernele.home.adapter.ApplicationAdapter;
import com.nuoman.westernele.home.model.ApplicationModel;
import com.nuoman.westernele.home.model.MainModel;
import com.nuoman.westernele.informationRelease.InformationReleaseActivity;
import com.nuoman.westernele.model.BaseTransModel;
import com.nuoman.westernele.numberQuery.NumberQueryActivity;
import com.nuoman.westernele.warningCenter.WarningCenterActivity;
import com.nuoman.westernele.westNew.WestNewActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 管理
 * 2016/1/13
 */
public class MainFragment extends BaseFragment implements AdapterView.OnItemClickListener, ICommonAction {

    @Bind(R.id.title_tv)
    TextView titleTv;
    @Bind(R.id.date_tv)
    TextView dateTv;
    @Bind(R.id.company_name_tv)
    TextView companyNameTv;
    @Bind(R.id.app_gridView)
    MyGridView appGridView;
    @Bind(R.id.unstart_tv)
    TextView unstartTv;
    @Bind(R.id.producting_tv)
    TextView productingTv;
    @Bind(R.id.completed_tv)
    TextView completedTv;
    @Bind(R.id.notice_content)
    TextView noticeContent;
    private ApplicationAdapter mAdapter;
    private List<ApplicationModel> data;
    int index[] = {
            R.drawable.xibianxinwen_03,
            R.drawable.xiangmuchaxun_03,
            R.drawable.gonghaochaxun_03,
            R.drawable.zhangkuanxinxi_03,
            R.drawable.zhangkuanxinxi_03,
            R.drawable.xiaoxizhongxin_03};


    private CommonPresenter commonPresenter = new CommonPresenter(this);
    private BaseTransModel transModel = new BaseTransModel();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_layout, null);
        ButterKnife.bind(this, view);

        init();
        invoke();
        return view;
    }

    private void init() {

        commonPresenter.isShowProgressDialog = false;
        if (AppTools.getUser().getSubcompany().size() > 0) {
            companyNameTv.setText((AppTools.getUser().getSubcompany().get(0).getDataName()));
        }
        appGridView.setFocusable(false);
        data = new ArrayList<>();
        String str[] = getActivity().getResources().getStringArray(R.array.apptitle);
        for (int i = 0; i < str.length; i++) {
            ApplicationModel model = new ApplicationModel();
            model.setIds(index[i]);
            model.setTitle(str[i]);
            data.add(model);
        }
        mAdapter = new ApplicationAdapter(getActivity(), R.layout.fragment_application_item, data);
        appGridView.setAdapter(mAdapter);
        appGridView.setOnItemClickListener(this);


    }

    /**
     * 调用方法获取数据
     */
    public void invoke() {
        transModel.setUserId(AppTools.getUser().getUserId());
        commonPresenter.invokeInterfaceObtainData(true, "appUserCtrl", NuoManService.GETMAINPAGEINFO, transModel, new TypeToken<MainModel>() {
        });
    }


    @Override
    public void obtainData(Object data, String methodIndex, int status, Map<String, String> parameterMap) {
        switch (methodIndex) {
            case NuoManService.GETMAINPAGEINFO:

                if (data != null) {
                    MainModel mainPageModel = (MainModel) data;
                    unstartTv.setText(mainPageModel.getUnStart());
                    productingTv.setText(mainPageModel.getProducing());
                    completedTv.setText(mainPageModel.getFinished());
                    noticeContent.setText(mainPageModel.getNoticeInfo());
                }
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        Intent intent;
        switch (position) {
            case 0:
                intent = new Intent(getActivity(), WestNewActivity.class);
                startActivity(intent);
                break;
            case 1:
                break;
            case 2:
                intent = new Intent(getActivity(), NumberQueryActivity.class);
                startActivity(intent);
                break;
            case 3:
                intent = new Intent(getActivity(), BillInformationActivity.class);
                startActivity(intent);
                break;
            case 4:
                intent = new Intent(getActivity(), InformationReleaseActivity.class);
                startActivity(intent);
                break;
            case 5:
                intent = new Intent(getActivity(), WarningCenterActivity.class);
                startActivity(intent);
                break;

        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


}
