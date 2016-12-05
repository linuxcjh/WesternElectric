package com.nuoman.westernele.contacts;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.google.gson.reflect.TypeToken;
import com.nuoman.westernele.api.NuoManService;
import com.nuoman.westernele.common.BaseFragment;
import com.nuoman.westernele.common.CommonPresenter;
import com.nuoman.westernele.common.ICommonAction;
import com.nuoman.westernele.common.NuoManConstant;
import com.nuoman.westernele.common.utils.AppTools;
import com.nuoman.westernele.common.utils.Utils;
import com.nuoman.westernele.components.SearchPopupWindow;
import com.nuoman.westernele.contacts.adapter.ClientRecordAdapter;
import com.nuoman.westernele.contacts.model.ContactModel;
import com.nuoman.westernele.contacts.model.Customer;
import com.nuoman.westernele.model.BaseTransModel;
import com.nuoman.westernelectric.R;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

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
public class ContactsLeaderFragment extends BaseFragment implements ICommonAction {


    @Bind(R.id.search_layout)
    RelativeLayout searchLayout;
    @Bind(R.id.pullLoadMoreRecyclerView)
    PullLoadMoreRecyclerView pullLoadMoreRecyclerView;
    @Bind(R.id.view_l)
    View viewL;

    private ClientRecordAdapter mAdapter;

    private List<Customer> contactModels = new ArrayList<>();

    private CommonPresenter commonPresenter = new CommonPresenter(this);
    private BaseTransModel transModel = new BaseTransModel();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_client_layout, null);
        ButterKnife.bind(this, view);
        init();
        invoke("");
        return view;
    }

    private void init() {

        commonPresenter.isShowProgressDialog = false;

        pullLoadMoreRecyclerView.setLinearLayout();
        pullLoadMoreRecyclerView.setFooterViewText("加载更多");


        mAdapter = new ClientRecordAdapter(getActivity(), R.layout.fragment_client_clue_item_layout, contactModels);
        pullLoadMoreRecyclerView.setAdapter(mAdapter);

        pullLoadMoreRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                invoke("");
            }

            @Override
            public void onLoadMore() {

            }
        });
    }

    /**
     * 调用方法获取数据
     */
    public void invoke(String searchContent) {
        transModel.setUserId(AppTools.getUser().getUserId());
        transModel.setSearch(searchContent);
        commonPresenter.invokeInterfaceObtainData(true, "appContactsCtrl", NuoManService.GETCONTACTSLIST, transModel, new TypeToken<ContactModel>() {
        });
    }

    @Override
    public void obtainData(Object data, String methodIndex, int status, Map<String, String> parameterMap) {
        pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
        if (data != null) {
            ContactModel model = (ContactModel) data;
            mAdapter.setData(model.getCustomer());
        }
    }

    /**
     * 显示搜索框
     */
    public void showPop() {
        SearchPopupWindow searchPopupWindow = new SearchPopupWindow(getActivity(), Utils.getDeviceHeightPixels(getActivity()), mHandler);
        searchPopupWindow.getPopupWindow().showAsDropDown(viewL, 0, -Utils.dip2px(getActivity(), 50));

    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case NuoManConstant.SEARCHPOPUPWINDOWRESULT:
                    invoke((String) msg.obj);
                    break;
            }
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @OnClick(R.id.search_layout)
    public void onClick() {
        showPop();
    }
}
