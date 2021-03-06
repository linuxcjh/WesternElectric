package com.nuoman.westernele.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.nuoman.westernele.api.NuoManService;
import com.nuoman.westernele.billInformation.BillInformationActivity;
import com.nuoman.westernele.common.BaseFragment;
import com.nuoman.westernele.common.CommonPresenter;
import com.nuoman.westernele.common.ICommonAction;
import com.nuoman.westernele.common.utils.AppConfig;
import com.nuoman.westernele.common.utils.AppTools;
import com.nuoman.westernele.common.utils.DateUtil;
import com.nuoman.westernele.components.MyGridView;
import com.nuoman.westernele.home.adapter.ApplicationAdapter;
import com.nuoman.westernele.home.model.ApplicationModel;
import com.nuoman.westernele.home.model.MainModel;
import com.nuoman.westernele.informationRelease.InformationReleaseActivity;
import com.nuoman.westernele.model.BaseTransModel;
import com.nuoman.westernele.numberQuery.NumberQueryActivity;
import com.nuoman.westernele.projectSummary.ProjectSummaryActivity;
import com.nuoman.westernele.projectmanage.ProjectManageActivity;
import com.nuoman.westernele.warningCenter.WarningCenterActivity;
import com.nuoman.westernele.westNew.WestNewActivity;
import com.nuoman.westernelectric.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @Bind(R.id.unstart_laytout)
    LinearLayout unstartLaytout;
    @Bind(R.id.producting_layout)
    LinearLayout productingLayout;
    @Bind(R.id.completed_layout)
    LinearLayout completedLayout;
    private ApplicationAdapter mAdapter;
    private List<ApplicationModel> applicationModels;
    int index[] = {
            R.drawable.xibianxinwen_03,
            R.drawable.xiangmuchaxun_03,
            R.drawable.gonghaochaxun_03,
            R.drawable.zhangkuanxinxi_03,
            R.drawable.shenhe_03,
            R.drawable.xiaoxizhongxin_03};

    int index_5[] = {
            R.drawable.xibianxinwen_03,
            R.drawable.xiangmuchaxun_03,
            R.drawable.gonghaochaxun_03,
            R.drawable.zhangkuanxinxi_03,
            R.drawable.xiaoxizhongxin_03};

    private CommonPresenter commonPresenter = new CommonPresenter(this);
    private BaseTransModel transModel = new BaseTransModel();
    private MainModel mainPageModel = new MainModel();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_layout, null);
        ButterKnife.bind(this, view);

        init();
//        invoke();
        return view;
    }

    private void init() {


        commonPresenter.isShowProgressDialog = false;
//        if (AppTools.getUser().getSubcompany().size() > 0) {
//            companyNameTv.setText((AppTools.getUser().getSubcompany().get(0).getDataName()));
//        }
        titleTv.setText(String.format("%s智慧服务云平台", AppConfig.getStringConfig("subCompanyName", "")));

        appGridView.setFocusable(false);
        applicationModels = new ArrayList<>();

        String str[] = getActivity().getResources().getStringArray(R.array.apptitle);
        String str_5[] = getActivity().getResources().getStringArray(R.array.apptitle_5);

        if (!AppTools.getUser().getRoleId().equals("5")) {
            for (int i = 0; i < str.length; i++) {
                ApplicationModel model = new ApplicationModel();
                model.setIds(index[i]);
                model.setTitle(str[i]);
                applicationModels.add(model);
            }
        } else {
            for (int i = 0; i < str_5.length; i++) {
                ApplicationModel model = new ApplicationModel();
                model.setIds(index_5[i]);
                model.setTitle(str_5[i]);
                applicationModels.add(model);
            }
        }

        mAdapter = new ApplicationAdapter(getActivity(), R.layout.fragment_application_item, applicationModels);
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
    public void onResume() {
        super.onResume();
        dateTv.setText(DateUtil.getTime(DateUtil.getDatePattern()) + "  " + DateUtil.getWeekByNow());
        invoke();
    }

    @Override
    public void obtainData(Object data, String methodIndex, int status, Map<String, String> parameterMap) {
        switch (methodIndex) {
            case NuoManService.GETMAINPAGEINFO:

                if (data != null) {
                    mainPageModel = (MainModel) data;
                    unstartTv.setText(mainPageModel.getUnStart());
                    productingTv.setText(mainPageModel.getProducing());
                    completedTv.setText(mainPageModel.getFinished());
                    noticeContent.setText(mainPageModel.getNoticeInfo());

                    if (mainPageModel.getHasNewAccountInfo().equals("1")) {
                        applicationModels.get(3).setVisible(true);
                    } else {
                        applicationModels.get(3).setVisible(false);

                    }

                    if (!AppTools.getUser().getRoleId().equals("5")) {
                        if (mainPageModel.getHasNewAlertInfo().equals("1")) {
                            applicationModels.get(5).setVisible(true);
                        } else {
                            applicationModels.get(5).setVisible(false);
                        }
                    }

                    mAdapter.setData(applicationModels);


                }
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        Intent intent;

        if (!AppTools.getUser().getRoleId().equals("5")) {
            switch (position) {
                case 0:
                    intent = new Intent(getActivity(), WestNewActivity.class);
                    startActivity(intent);
                    break;
                case 1:
                    startActivity(new Intent(getActivity(), ProjectManageActivity.class));
                    break;
                case 2:
                    intent = new Intent(getActivity(), NumberQueryActivity.class);
                    startActivity(intent);
                    break;
                case 3:
                    intent = new Intent(getActivity(), BillInformationActivity.class);
                    intent.putExtra("hasRedPoint", mainPageModel.getHasNewAccountInfo());
                    startActivity(intent);
                    break;
                case 4:
                    intent = new Intent(getActivity(), InformationReleaseActivity.class);
                    startActivity(intent);
                    break;
                case 5:
                    intent = new Intent(getActivity(), WarningCenterActivity.class);
                    intent.putExtra("hasRedPoint", mainPageModel.getHasNewAlertInfo());
                    startActivity(intent);
                    break;

            }
        } else {
            switch (position) {
                case 0:
                    intent = new Intent(getActivity(), WestNewActivity.class);
                    startActivity(intent);
                    break;
                case 1:
                    startActivity(new Intent(getActivity(), ProjectManageActivity.class));
                    break;
                case 2:
                    intent = new Intent(getActivity(), NumberQueryActivity.class);
                    startActivity(intent);
                    break;
                case 3:
                    intent = new Intent(getActivity(), BillInformationActivity.class);
                    intent.putExtra("hasRedPoint", mainPageModel.getHasNewAccountInfo());
                    startActivity(intent);
                    break;
                case 4:
                    intent = new Intent(getActivity(), WarningCenterActivity.class);
                    intent.putExtra("hasRedPoint", mainPageModel.getHasNewAlertInfo());
                    startActivity(intent);
                    break;

            }
        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @OnClick({R.id.unstart_laytout, R.id.producting_layout, R.id.completed_layout})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.unstart_laytout:
                intent = new Intent(getContext(), ProjectSummaryActivity.class);
                intent.putExtra("kind", 0);
                startActivity(intent);
                break;
            case R.id.producting_layout:
                intent = new Intent(getContext(), ProjectSummaryActivity.class);
                intent.putExtra("kind", 1);
                startActivity(intent);
                break;
            case R.id.completed_layout:
                intent = new Intent(getContext(), ProjectSummaryActivity.class);
                intent.putExtra("kind", 2);
                startActivity(intent);
                break;
        }
    }
}
