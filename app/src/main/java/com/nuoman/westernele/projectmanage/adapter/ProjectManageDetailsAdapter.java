package com.nuoman.westernele.projectmanage.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nuoman.westernele.common.BaseRecyclerAdapter;
import com.nuoman.westernele.common.ViewHolder;
import com.nuoman.westernele.login.model.CompanyInfoModel;
import com.nuoman.westernele.projectmanage.model.ProjectDetailItemModel;
import com.nuoman.westernele.projectmanage.model.ProjectDetailModel;
import com.nuoman.westernelectric.R;

import java.util.List;

/**
 * Created by Alex on 2016/1/11.
 */
public class ProjectManageDetailsAdapter extends BaseRecyclerAdapter<CompanyInfoModel> implements View.OnClickListener {
    ProjectDetailItemModel itemModels = new ProjectDetailItemModel();

    public ProjectManageDetailsAdapter(Context context, int layoutResId, List<CompanyInfoModel> data) {
        super(context, layoutResId, data);
    }

    public void setDataResource(ProjectDetailItemModel model) {
        this.itemModels = model;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(ViewHolder holder, CompanyInfoModel model, int position) {

        RelativeLayout rootLayout = holder.getView(R.id.root_layout);
        holder.setText(R.id.name_tv, model.getDataName());
        final ImageView imageView =holder.getView(R.id.status_iv);
        final GridLayout gridLayout = holder.getView(R.id.gridLayout_tect);

        rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (gridLayout.getVisibility() == View.VISIBLE) {
                    gridLayout.setVisibility(View.GONE);
                    imageView.setBackgroundResource(R.drawable.btn_dropdown01_03);
                } else {
                    gridLayout.setVisibility(View.VISIBLE);
                    imageView.setBackgroundResource(R.drawable.btn_dropdown02_03);
                }
            }
        });


        switch (model.getId()) {
            case "0":
                setApprovalFlowData(gridLayout, itemModels.getTech());
                gridLayout.setVisibility(View.VISIBLE);
                imageView.setBackgroundResource(R.drawable.btn_dropdown02_03);

                break;
            case "1":
                setApprovalFlowData(gridLayout, itemModels.getPurchase());

                break;
            case "2":
                setApprovalFlowData(gridLayout, itemModels.getProduction());

                break;
            case "3":
                setApprovalFlowData(gridLayout, itemModels.getTransport());

                break;
        }
    }

    @Override
    public void onClick(View v) {
        CompanyInfoModel item = (CompanyInfoModel) v.getTag();
        switch (v.getId()) {
            case R.id.root_layout:


                break;

        }
    }

    /**
     * 设置审批流程列表
     *
     * @param transport
     */
    public void setApprovalFlowData(GridLayout flowGridlayout, final List<ProjectDetailModel> transport) {
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
                            View view = LayoutInflater.from(context).inflate(R.layout.project_details_inner_item_layout, null);
                            TextView title_name_tv = (TextView) view.findViewById(R.id.title_name_tv);
                            TextView item_one_tv = (TextView) view.findViewById(R.id.item_one_tv);
                            TextView item_two_tv = (TextView) view.findViewById(R.id.item_two_tv);
                            TextView item_three_tv = (TextView) view.findViewById(R.id.item_three_tv);
                            TextView item_four_tv = (TextView) view.findViewById(R.id.item_four_tv);
                            ImageView imageView = (ImageView) view.findViewById(R.id.image_iv);

                            title_name_tv.setText(model.getNodeName());
                            item_one_tv.setText("计划开始    " + model.getPlanStartDate());
                            item_two_tv.setText("计划完工    " + model.getPlanEndDate());
                            item_three_tv.setText("实际开始    " + model.getActualStartDate());
                            item_four_tv.setText("实际完工    " + model.getActualEndDate());

                            imageView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
//                                    Intent intent = new Intent(ApprovalDetailsNewActivity.this, ApproverDetailsActivity.class);
//                                    intent.putExtra("personId", model.getApproveUserId());
//                                    startActivity(intent);
                                }
                            });


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
