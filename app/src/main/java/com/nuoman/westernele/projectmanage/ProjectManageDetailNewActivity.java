package com.nuoman.westernele.projectmanage;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.nuoman.westernele.api.NuoManService;
import com.nuoman.westernele.common.BaseActivity;
import com.nuoman.westernele.common.CommonPresenter;
import com.nuoman.westernele.common.CustomProgressDialog;
import com.nuoman.westernele.common.ICommonAction;
import com.nuoman.westernele.common.utils.AppTools;
import com.nuoman.westernele.model.BaseTransModel;
import com.nuoman.westernele.projectmanage.model.ProjectDetailItemModel;
import com.nuoman.westernele.projectmanage.model.ProjectDetailModel;
import com.nuoman.westernelectric.R;

import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 详情
 */
public class ProjectManageDetailNewActivity extends BaseActivity implements ICommonAction {


    @Bind(R.id.title_left_tv)
    TextView titleLeftTv;
    @Bind(R.id.title_mid_tv)
    TextView titleMidTv;
    @Bind(R.id.title_right_tv)
    TextView titleRightTv;
    @Bind(R.id.name_1_tv)
    TextView name1Tv;
    @Bind(R.id.status_1_iv)
    ImageView status1Iv;
    @Bind(R.id.root_1_layout)
    RelativeLayout root1Layout;
    @Bind(R.id.gridLayout_1_tect)
    GridLayout gridLayout1Tect;
    @Bind(R.id.name_2_tv)
    TextView name2Tv;
    @Bind(R.id.status_2_iv)
    ImageView status2Iv;
    @Bind(R.id.root_2_layout)
    RelativeLayout root2Layout;
    @Bind(R.id.gridLayout_2_tect)
    GridLayout gridLayout2Tect;
    @Bind(R.id.name_3_tv)
    TextView name3Tv;
    @Bind(R.id.status_3_iv)
    ImageView status3Iv;
    @Bind(R.id.root_3_layout)
    RelativeLayout root3Layout;
    @Bind(R.id.gridLayout_3_tect)
    GridLayout gridLayout3Tect;
    @Bind(R.id.name_4_tv)
    TextView name4Tv;
    @Bind(R.id.status_4_iv)
    ImageView status4Iv;
    @Bind(R.id.root_4_layout)
    RelativeLayout root4Layout;
    @Bind(R.id.gridLayout_4_tect)
    GridLayout gridLayout4Tect;
    private ProjectDetailItemModel model = new ProjectDetailItemModel();

    private CommonPresenter commonPresenter = new CommonPresenter(this);
    private BaseTransModel transModel = new BaseTransModel();

    public CustomProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details_new_layout);
        ButterKnife.bind(this);
        initView();
        invoke();

    }


    private void initView() {
        progressDialog = new CustomProgressDialog(this);

//        contactModels.add(new CompanyInfoModel("技术", "0"));
//        contactModels.add(new CompanyInfoModel("主要原材料及零部件采购", "1"));
//        contactModels.add(new CompanyInfoModel("生产过程", "2"));
//        contactModels.add(new CompanyInfoModel("运输及安装", "3"));

        titleMidTv.setText("详情");

    }

    /**
     * 调用方法获取数据
     */
    public void invoke() {
        transModel.setUserId(AppTools.getUser().getUserId());
        transModel.setOrderId(getIntent().getStringExtra("id"));
        commonPresenter.invokeInterfaceObtainData(true, "appNodeCtrl", NuoManService.GETNODELISTBYORDERID, transModel, new TypeToken<ProjectDetailItemModel>() {
        });
        progressDialog.show();
    }

    @Override
    public void obtainData(Object data, String methodIndex, int status, Map<String, String> parameterMap) {
        progressDialog.dismiss();
        switch (methodIndex) {
            case NuoManService.GETNODELISTBYORDERID:
                if (data != null) {
                    model = (ProjectDetailItemModel) data;
                    root1Layout.performClick();

                }
                break;
        }
    }


    @OnClick(R.id.title_left_tv)
    public void onClick() {
        finish();
    }

    @OnClick({R.id.root_1_layout, R.id.root_2_layout, R.id.root_3_layout, R.id.root_4_layout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.root_1_layout:
                setView(gridLayout1Tect, status1Iv, model.getTech());
                break;
            case R.id.root_2_layout:
                setView(gridLayout2Tect, status2Iv, model.getPurchase());

                break;
            case R.id.root_3_layout:
                setView(gridLayout3Tect, status3Iv, model.getProduction());

                break;
            case R.id.root_4_layout:
                setView(gridLayout4Tect, status4Iv, model.getTransport());

                break;
        }
    }


    private void setView(GridLayout gridLayout1Tect, ImageView status1Iv, List<ProjectDetailModel> itemModels) {
        if (gridLayout1Tect.getVisibility() == View.VISIBLE) {
            gridLayout1Tect.setVisibility(View.GONE);
            status1Iv.setBackgroundResource(R.drawable.btn_dropdown01_03);
        } else {
            gridLayout1Tect.setVisibility(View.VISIBLE);
            status1Iv.setBackgroundResource(R.drawable.btn_dropdown02_03);
        }
        setApprovalFlowData(gridLayout1Tect, itemModels);
    }

    /**
     * 设置审批流程列表
     *
     * @param transport
     */
    public void setApprovalFlowData(GridLayout flowGridlayout, final List<ProjectDetailModel> transport) {

        if (flowGridlayout.getChildCount() > 0) {
            return;
        }
        if (transport != null && transport.size() > 0) {

            flowGridlayout.removeAllViews();
            int size = transport.size();

            if (size > 0) {
                final int column = 1; //列数
                int rows = size;//行数

                flowGridlayout.setRowCount(rows);
                flowGridlayout.setColumnCount(column);

                int count = 0;
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < column; j++) {
                        if (size > count) {
                            final ProjectDetailModel model = transport.get(count);
                            View view = LayoutInflater.from(this).inflate(R.layout.project_details_inner_item_layout, null);
                            TextView title_name_tv = (TextView) view.findViewById(R.id.title_name_tv);
                            final TextView item_one_tv = (TextView) view.findViewById(R.id.item_one_tv);
                            final TextView item_two_tv = (TextView) view.findViewById(R.id.item_two_tv);
                            final TextView item_three_tv = (TextView) view.findViewById(R.id.item_three_tv);
                            final TextView item_four_tv = (TextView) view.findViewById(R.id.item_four_tv);
                            final ImageView imageView = (ImageView) view.findViewById(R.id.image_iv);


                            final ImageView edit_iv = (ImageView) view.findViewById(R.id.edit_iv);
                            final ImageView commit_iv = (ImageView) view.findViewById(R.id.commit_iv);
                            final ImageView camera_03 = (ImageView) view.findViewById(R.id.camera_03);
                            final LinearLayout edit_layout = (LinearLayout) view.findViewById(R.id.edit_layout);

                            //控件值初始化
                            title_name_tv.setText(model.getNodeName());
                            item_one_tv.setText("计划开始    " + model.getPlanStartDate());
                            item_two_tv.setText("计划完工    " + model.getPlanEndDate());
                            item_three_tv.setText("实际开始    " + model.getActualStartDate());
                            item_four_tv.setText("实际完工    " + model.getActualEndDate());

                            //图片按钮
                            imageView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
//                                    Intent intent = new Intent(ApprovalDetailsNewActivity.this, ApproverDetailsActivity.class);
//                                    intent.putExtra("personId", model.getApproveUserId());
//                                    startActivity(intent);
                                    edit_layout.setVisibility(View.VISIBLE);
                                    imageView.setVisibility(View.GONE);
                                }
                            });


                            //编辑按钮
                            edit_iv.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    imageView.setVisibility(View.GONE);
                                    edit_iv.setVisibility(View.GONE);
                                    commit_iv.setVisibility(View.VISIBLE);
                                    camera_03.setVisibility(View.VISIBLE);

                                    item_one_tv.setEnabled(true);
                                    item_two_tv.setEnabled(true);
                                    item_three_tv.setEnabled(true);
                                    item_four_tv.setEnabled(true);

                                }
                            });

                            //提交按钮
                            commit_iv.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {


                                }
                            });


                            //拍照按钮
                            camera_03.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {


                                }
                            });

                            //选择日期
                            item_one_tv.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    AppTools.obtainData(ProjectManageDetailNewActivity.this, item_one_tv,"计划开始    ");


                                }
                            });
                            item_two_tv.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    AppTools.obtainData(ProjectManageDetailNewActivity.this, item_two_tv,"计划完工    " );

                                }
                            });
                            item_three_tv.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    AppTools.obtainData(ProjectManageDetailNewActivity.this, item_three_tv,"实际开始    ");


                                }
                            });
                            item_four_tv.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    AppTools.obtainData(ProjectManageDetailNewActivity.this, item_four_tv,"实际完工    " );

                                }
                            });

                            item_one_tv.setEnabled(false);
                            item_two_tv.setEnabled(false);
                            item_three_tv.setEnabled(false);
                            item_four_tv.setEnabled(false);

                            count++;

                            GridLayout.Spec rowSpec = GridLayout.spec(i);
                            GridLayout.Spec columnSpec = GridLayout.spec(j);
                            GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec, columnSpec);
                            params.setGravity(Gravity.FILL);
                            flowGridlayout.addView(view, params);
                        }
                    }
                }
            }
        }
    }


}
