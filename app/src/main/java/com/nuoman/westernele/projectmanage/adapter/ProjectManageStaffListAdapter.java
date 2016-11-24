package com.nuoman.westernele.projectmanage.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import com.nuoman.westernele.common.BaseRecyclerAdapter;
import com.nuoman.westernele.common.ViewHolder;
import com.nuoman.westernele.login.model.CompanyInfoModel;
import com.nuoman.westernele.numberDetail.view.NumberDetailActivity;
import com.nuoman.westernelectric.R;

import java.util.List;

/**
 * Created by Alex on 2016/1/11.
 */
public class ProjectManageStaffListAdapter extends BaseRecyclerAdapter<CompanyInfoModel> implements View.OnClickListener {

    public ProjectManageStaffListAdapter(Context context, int layoutResId, List<CompanyInfoModel> data) {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(ViewHolder holder, CompanyInfoModel model, int position) {

        RelativeLayout rootLayout = holder.getView(R.id.root_layout);
        holder.setText(R.id.staff_name_tv, model.getDataName());
        rootLayout.setOnClickListener(this);
        rootLayout.setTag(model);


    }

    @Override
    public void onClick(View v) {
        CompanyInfoModel item = (CompanyInfoModel) v.getTag();
        switch (v.getId()) {
            case R.id.root_layout:

                context.startActivity(new Intent(context, NumberDetailActivity.class).putExtra("number",item.getId()).putExtra("title",item.getDataName()));


                break;

        }
    }
}
