package com.nuoman.westernele.projectmanage.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.nuoman.westernele.common.BaseRecyclerAdapter;
import com.nuoman.westernele.common.NuoManConstant;
import com.nuoman.westernele.common.ViewHolder;
import com.nuoman.westernele.common.utils.AppTools;
import com.nuoman.westernele.projectmanage.ProjectWorkNumActivity;
import com.nuoman.westernele.projectmanage.model.ProjectModel;
import com.nuoman.westernelectric.R;

import java.util.List;

/**
 * Created by Alex on 2016/1/11.
 */
public class ProjectManageAdapter extends BaseRecyclerAdapter<ProjectModel> implements View.OnClickListener {

    Handler mHandler;

    private boolean isDisplayFocus;

    public ProjectManageAdapter(Context context, int layoutResId, List<ProjectModel> data, Handler mHandler) {
        super(context, layoutResId, data);
        this.mHandler = mHandler;
    }

    public void setIsFocusDisplay(boolean isDisplayFocus) {

        this.isDisplayFocus = isDisplayFocus;

    }

    @Override
    protected void convert(ViewHolder holder, ProjectModel model, int position) {

        RelativeLayout clue_focus_layout = holder.getView(R.id.clue_focus_layout);
        ImageView clue_avatar_iv = holder.getView(R.id.clue_avatar_iv);
        ImageView clue_focus_tv = holder.getView(R.id.clue_focus_tv);
        RelativeLayout rootLayout = holder.getView(R.id.root_layout);
        rootLayout.setOnClickListener(this);
        rootLayout.setTag(model);

        AppTools.setImageViewPicture(context, model.getProjectPic(), clue_avatar_iv);

        holder.setText(R.id.clue_name_tv, model.getProjectName());
        holder.setText(R.id.clue_create_time_tv, model.getFdate());
        holder.setText(R.id.clue_person_tv, model.getProjectManager());

        clue_focus_layout.setOnClickListener(this);
        clue_focus_layout.setTag(model);

        if (model.getIsCollected().equals("1")) {
            clue_focus_tv.setBackgroundResource(R.drawable.guaznhu_02_03);
        } else {
            clue_focus_tv.setBackgroundResource(R.drawable.guanzhu_01_03);
        }

    }

    @Override
    public void onClick(View v) {
        ProjectModel item = (ProjectModel) v.getTag();
        switch (v.getId()) {
            case R.id.clue_focus_layout:
                mHandler.sendMessage(mHandler.obtainMessage(NuoManConstant.UPDATE_FOCUS, item));
                break;
            case R.id.root_layout:
                context.startActivity(new Intent(context, ProjectWorkNumActivity.class).putExtra("id", item.getProjectId()));
                break;


        }
    }
}
