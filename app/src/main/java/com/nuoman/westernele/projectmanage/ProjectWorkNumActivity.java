package com.nuoman.westernele.projectmanage;

import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.nuoman.westernele.api.NuoManService;
import com.nuoman.westernele.common.BaseActivity;
import com.nuoman.westernele.common.CommonPresenter;
import com.nuoman.westernele.common.ICommonAction;
import com.nuoman.westernele.login.model.CompanyInfoModel;
import com.nuoman.westernele.model.BaseTransModel;
import com.nuoman.westernele.projectmanage.adapter.ProjectManageStaffListAdapter;
import com.nuoman.westernelectric.R;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 工号列表
 */
public class ProjectWorkNumActivity extends BaseActivity implements ICommonAction {


    @Bind(R.id.title_left_tv)
    TextView titleLeftTv;
    @Bind(R.id.title_mid_tv)
    TextView titleMidTv;
    @Bind(R.id.title_right_tv)
    TextView titleRightTv;
    @Bind(R.id.pullLoadMoreRecyclerView)
    PullLoadMoreRecyclerView pullLoadMoreRecyclerView;
    private ProjectManageStaffListAdapter mAdapter;

    private List<CompanyInfoModel> contactModels = new ArrayList<>();

    private CommonPresenter commonPresenter = new CommonPresenter(this);
    private BaseTransModel transModel = new BaseTransModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_work_num_layout);
        ButterKnife.bind(this);
        initView();
        invoke();

    }


    private void initView() {

        titleMidTv.setText("人员");
        commonPresenter.isShowProgressDialog = false;
        pullLoadMoreRecyclerView.setLinearLayout();
        pullLoadMoreRecyclerView.setFooterViewText("加载更多");

        mAdapter = new ProjectManageStaffListAdapter(this, R.layout.project_manage_staff_item_layout, contactModels);
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
        transModel.setProjectId(getIntent().getStringExtra("id"));
        commonPresenter.invokeInterfaceObtainData(true, "appNodeCtrl", NuoManService.GETORDERLISTBYPROJECTID, transModel, new TypeToken<List<CompanyInfoModel>>() {
        });
    }

    @Override
    public void obtainData(Object data, String methodIndex, int status, Map<String, String> parameterMap) {
        switch (methodIndex) {
            case NuoManService.GETORDERLISTBYPROJECTID:
                pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
                if (data != null) {
                    List<CompanyInfoModel> model = (List<CompanyInfoModel>) data;
                    mAdapter.setData(model);
                }
                break;
        }
    }


    @OnClick(R.id.title_left_tv)
    public void onClick() {
        finish();
    }

}
