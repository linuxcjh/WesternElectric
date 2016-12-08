package com.nuoman.westernele.informationDetail;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nuoman.westernele.common.BaseActivity;
import com.nuoman.westernele.common.utils.AppConfig;
import com.nuoman.westernele.common.utils.AppTools;
import com.nuoman.westernele.components.displayimage.ShowWebImageActivity;
import com.nuoman.westernele.informationDetail.model.InformationDetail;
import com.nuoman.westernelectric.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InformationDetailActivity extends BaseActivity implements Contract.InformationDetailView {


    @Bind(R.id.tv_detail_project_name)
    TextView tv_detail_project_name;
    @Bind(R.id.tv_detail_status)
    TextView tv_detail_status;
    @Bind(R.id.tv_project_node)
    TextView tv_project_node;
    @Bind(R.id.tv_project_leader)
    TextView tv_project_leader;
    @Bind(R.id.tv_plan_start_date)
    TextView tv_plan_start_date;
    @Bind(R.id.tv_real_start_date)
    TextView tv_real_start_date;
    @Bind(R.id.tv_plan_end_date)
    TextView tv_plan_end_date;
    @Bind(R.id.tv_real_end_date)
    TextView tv_real_end_date;
    @Bind(R.id.tv_is_overdue)
    TextView tv_is_overdue;
    @Bind(R.id.iv_detail_photo)
    ImageView iv_detail_photo;
    @Bind(R.id.btn_pass)
    Button btn_pass;
    @Bind(R.id.btn_reject)
    Button btn_reject;

    private InformationDetailPresenterImp informationDetailPresenterImp;
    private String nodeId;
    private String picUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_detail_layout);
        ButterKnife.bind(this);
        initVariable();
        bindListener();
    }

    private void initVariable() {
        informationDetailPresenterImp = new InformationDetailPresenterImp(this);
        nodeId = getIntent().getStringExtra("nodeId");
        informationDetailPresenterImp.requestInformationDetail(nodeId);
    }

    private void bindListener() {
        btn_reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                informationDetailPresenterImp.requestCheck(nodeId, "2");
            }
        });
        btn_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                informationDetailPresenterImp.requestCheck(nodeId, "1");
            }
        });

        iv_detail_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> paths = new ArrayList<>();
                paths.add(picUrl);
                Intent intent = new Intent(InformationDetailActivity.this, ShowWebImageActivity.class);
                intent.putExtra(ShowWebImageActivity.IMAGE_URLS, (Serializable) paths);
                intent.putExtra(ShowWebImageActivity.POSITION, 1);
                startActivity(intent);
            }
        });
    }

    @OnClick(R.id.title_left_tv)
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_left_tv:
                finish();
                break;
        }
    }

    @Override
    public void formatView(InformationDetail informationDetail) {
        tv_detail_project_name.setText(informationDetail.getProjectName());
        tv_project_node.setText(String.format("项目节点：%s", informationDetail.getNodeName()));
        tv_project_leader.setText(String.format("项目负责人：%s", informationDetail.getProjectManager()));
        tv_plan_start_date.setText(String.format("计划开工时间：%s", informationDetail.getPlanStartDate()));
        tv_plan_end_date.setText(String.format("实际开工时间：%s", informationDetail.getPlanEndDate()));
        tv_real_start_date.setText(String.format("计划完成时间：%s", informationDetail.getActualStartDate()));
        tv_real_end_date.setText(String.format("实际完成时间：%s", informationDetail.getActualEndDate()));
        AppTools.setImageViewClub(this, informationDetail.getPicUrl(), iv_detail_photo);
        picUrl = informationDetail.getPicUrl();
        switch (informationDetail.getNodeCheckStatus()) {
            case "0":
                tv_detail_status.setText("待审核");
                tv_detail_status.setTextColor(Color.parseColor("#7c7c7c"));
                break;
            case "1":
                tv_detail_status.setText("已通过");
                tv_detail_status.setTextColor(Color.BLACK);
                break;
            case "2":
                tv_detail_status.setText("未通过");
                tv_detail_status.setTextColor(Color.parseColor("#dc291c"));
                break;
        }
        switch (informationDetail.getNodeStatus()) {
            case "0":
                tv_is_overdue.setText("未完成");
                break;
            case "1":
                tv_is_overdue.setText("按时完成");
                break;
            case "2":
                tv_is_overdue.setText("逾期完成");
                tv_is_overdue.setTextColor(Color.parseColor("#dc291c"));
                break;
            case "3":
                tv_is_overdue.setText("其他失败");
                break;

        }
    }

    @Override
    public void checkFinish(String oper) {
        switch (oper) {
            case "1":
                Toast.makeText(AppConfig.getContext(), "审核已经通过", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case "2":
                Toast.makeText(AppConfig.getContext(), "审核已拒绝", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }
}
