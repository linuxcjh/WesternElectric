package com.nuoman.westernele.projectmanage;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.nuoman.westernele.api.NuoManService;
import com.nuoman.westernele.common.BaseActivity;
import com.nuoman.westernele.common.CommonPresenter;
import com.nuoman.westernele.common.ICommonAction;
import com.nuoman.westernele.common.NuoManConstant;
import com.nuoman.westernele.common.utils.AppTools;
import com.nuoman.westernele.common.utils.Utils;
import com.nuoman.westernele.components.SearchPopupWindow;
import com.nuoman.westernele.filter.FilterViewLayout;
import com.nuoman.westernele.filter.OnFilterSelectedListener;
import com.nuoman.westernele.filter.SelectItemModel;
import com.nuoman.westernele.filter.TransFilterItemDataModel;
import com.nuoman.westernele.login.model.CompanyInfoModel;
import com.nuoman.westernele.model.BaseTransModel;
import com.nuoman.westernele.projectmanage.adapter.ProjectManageAdapter;
import com.nuoman.westernele.projectmanage.model.ProjectModel;
import com.nuoman.westernelectric.R;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProjectManageActivity extends BaseActivity implements ICommonAction, OnFilterSelectedListener {


    @Bind(R.id.search_layout)
    LinearLayout searchLayout;
    @Bind(R.id.pullLoadMoreRecyclerView)
    PullLoadMoreRecyclerView pullLoadMoreRecyclerView;
    @Bind(R.id.back_iv)
    ImageView backIv;
    @Bind(R.id.filter_layout)
    FilterViewLayout filterLayout;
    @Bind(R.id.search_tv)
    TextView searchTv;
    @Bind(R.id.root_layout)
    RelativeLayout rootLayout;

    private ProjectManageAdapter mAdapter;

    private List<ProjectModel> contactModels = new ArrayList<>();

    private CommonPresenter commonPresenter = new CommonPresenter(this);
    private BaseTransModel transModel = new BaseTransModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_manage_layout);
        ButterKnife.bind(this);
        initView();
        invoke();

    }


    private void initView() {
        filterLayout.setTitleValue(new String[]{"筛选", "排序"});
        setFilerData();

        commonPresenter.isShowProgressDialog = false;
        pullLoadMoreRecyclerView.setLinearLayout();
        pullLoadMoreRecyclerView.setFooterViewText("加载更多");

        mAdapter = new ProjectManageAdapter(this, R.layout.project_manage_item_layout, contactModels, mHandler);
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

    private void setFilerData() {


    }

    /**
     * 调用方法获取数据
     */
    public void invoke() {
        transModel.setUserId(AppTools.getUser().getUserId());
        transModel.setSearch("");
        transModel.setSort("1");
        transModel.setCondition("0");
        commonPresenter.invokeInterfaceObtainData(true, "appProjectCtrl", NuoManService.GETPROJECTLISTCONDITION, transModel, new TypeToken<List<ProjectModel>>() {
        });
        commonPresenter.invokeInterfaceObtainData(true, "appProjectCtrl", NuoManService.GETPROJECTCONDITIONS, null, new TypeToken<List<CompanyInfoModel>>() {
        });
    }


    /**
     * 显示搜索框
     */
    public void showPop() {
        SearchPopupWindow searchPopupWindow = new SearchPopupWindow(this, Utils.getDeviceHeightPixels(this), mHandler);
        searchPopupWindow.getPopupWindow().showAsDropDown(rootLayout, 0, -Utils.dip2px(this, 50));

    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case NuoManConstant.SEARCHPOPUPWINDOWRESULT:
//                    mClientPresenter.isSearch = true;
//                    mClientPresenter.transClientListModel.setFilter("");
//                    mClientPresenter.transClientListModel.setSorter("");
//                    mClientPresenter.transClientListModel.setKeyword(((SelectModel) msg.obj).getName());
//                    mClientPresenter.searchCustomerList();
                    break;
                case NuoManConstant.UPDATE_FOCUS:

                    ProjectModel m = (ProjectModel) msg.obj;
                    BaseTransModel transModel = new BaseTransModel();
                    if (m.getIsCollected().equals("1")) {
                        transModel.setState("0");
                    } else {
                        transModel.setState("1");
                    }
                    transModel.setUserId(AppTools.getUser().getUserId());
                    transModel.setProjectId(m.getProjectId());

                    commonPresenter.invokeInterfaceObtainData(true, "appProjectCtrl", NuoManService.PROJECTCOLLECTION, transModel, new TypeToken<CompanyInfoModel>() {
                    });

                    break;
            }
        }
    };
//    @Override
//    public void obtainFilterData(FilterDataModel data) {
//
//    }

    @Override
    public void obtainDoubleSelectedResult(List<TransFilterItemDataModel> resultList) {

    }

    @Override
    public void obtainSingleFilterSelectedResult(SelectItemModel resultModel) {

    }

    @Override
    public void obtainData(Object data, String methodIndex, int status, Map<String, String> parameterMap) {
        switch (methodIndex) {
            case NuoManService.GETPROJECTLISTCONDITION:
                pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
                if (data != null) {
                    List<ProjectModel> model = (List<ProjectModel>) data;
                    mAdapter.setData(model);
                }
                break;
            case NuoManService.GETPROJECTCONDITIONS:
                if (data != null) {
                    List<CompanyInfoModel> models = (List<CompanyInfoModel>) data;
                    List<SelectItemModel> filterSingle = new ArrayList<>();
                    for (int i = 0; i < models.size(); i++) {
                        filterSingle.add(new SelectItemModel(models.get(i).getId(), models.get(i).getDataName()));
                    }
                    filterLayout.setSingleFilterData(filterSingle);
                }
                filterLayout.setOnSelectListener(this);
                List<SelectItemModel> filterSingle = new ArrayList<>();
                filterSingle.add(new SelectItemModel("1", "以时间正序排列"));
                filterSingle.add(new SelectItemModel("2", "以时间反序排列"));
                filterSingle.add(new SelectItemModel("3", "以项目名称正序排列"));
                filterSingle.add(new SelectItemModel("4", "以项目名称反序排列"));
                filterLayout.setSingleFilterData(filterSingle);
                break;
            case NuoManService.PROJECTCOLLECTION:
                if (status == 1) {
                    commonPresenter.invokeInterfaceObtainData(true, "appProjectCtrl", NuoManService.GETPROJECTLISTCONDITION, transModel, new TypeToken<List<ProjectModel>>() {
                    });
                }
                break;
        }

    }


    @OnClick({R.id.back_iv, R.id.search_layout, R.id.search_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_iv:
                finish();
                break;
            case R.id.search_layout:
                break;
            case R.id.search_tv:
                showPop();
                break;
        }
    }
}
