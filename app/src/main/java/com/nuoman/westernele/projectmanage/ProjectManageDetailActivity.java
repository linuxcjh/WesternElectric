package com.nuoman.westernele.projectmanage;

import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.nuoman.westernele.api.NuoManService;
import com.nuoman.westernele.common.BaseActivity;
import com.nuoman.westernele.common.CommonPresenter;
import com.nuoman.westernele.common.ICommonAction;
import com.nuoman.westernele.common.utils.AppTools;
import com.nuoman.westernele.login.model.CompanyInfoModel;
import com.nuoman.westernele.model.BaseTransModel;
import com.nuoman.westernele.projectmanage.adapter.ProjectManageDetailsAdapter;
import com.nuoman.westernele.projectmanage.model.ProjectDetailItemModel;
import com.nuoman.westernelectric.R;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 详情
 */
public class ProjectManageDetailActivity extends BaseActivity implements ICommonAction {


    @Bind(R.id.title_left_tv)
    TextView titleLeftTv;
    @Bind(R.id.title_mid_tv)
    TextView titleMidTv;
    @Bind(R.id.title_right_tv)
    TextView titleRightTv;
    @Bind(R.id.pullLoadMoreRecyclerView)
    PullLoadMoreRecyclerView pullLoadMoreRecyclerView;
    private ProjectManageDetailsAdapter mAdapter;

    private List<CompanyInfoModel> contactModels = new ArrayList<>();
    private ProjectDetailItemModel model = new ProjectDetailItemModel();

    private CommonPresenter commonPresenter = new CommonPresenter(this);
    private BaseTransModel transModel = new BaseTransModel();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details_layout);
        ButterKnife.bind(this);
        initView();
        invoke();

    }


    private void initView() {

        contactModels.add(new CompanyInfoModel("技术", "0"));
        contactModels.add(new CompanyInfoModel("主要原材料及零部件采购", "1"));
        contactModels.add(new CompanyInfoModel("生产过程", "2"));
        contactModels.add(new CompanyInfoModel("运输及安装", "3"));

        titleMidTv.setText("详情");
        commonPresenter.isShowProgressDialog = true;
        pullLoadMoreRecyclerView.setLinearLayout();
        pullLoadMoreRecyclerView.setFooterViewText("加载更多");
        pullLoadMoreRecyclerView.setPushRefreshEnable(false);

        mAdapter = new ProjectManageDetailsAdapter(this, R.layout.project_details_item_layout, contactModels);
        pullLoadMoreRecyclerView.setAdapter(mAdapter);

        pullLoadMoreRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                invoke();
            }

            @Override
            public void onLoadMore() {

            }
        });

    }

    /**
     * 调用方法获取数据
     */
    public void invoke() {
        transModel.setUserId(AppTools.getUser().getUserId());
        transModel.setOrderId(getIntent().getStringExtra("id"));
        commonPresenter.invokeInterfaceObtainData(true, "appNodeCtrl", NuoManService.GETNODELISTBYORDERID, transModel, new TypeToken<ProjectDetailItemModel>() {
        });
    }

    @Override
    public void obtainData(Object data, String methodIndex, int status, Map<String, String> parameterMap) {
        switch (methodIndex) {
            case NuoManService.GETNODELISTBYORDERID:
                pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
                if (data != null) {
                    ProjectDetailItemModel model = (ProjectDetailItemModel) data;
                    mAdapter.setDataResource(model);
                }
                break;
        }
    }


    @OnClick(R.id.title_left_tv)
    public void onClick() {
        finish();
    }

}
