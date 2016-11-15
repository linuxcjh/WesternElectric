package com.nuoman.westernele.home.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.nuoman.tabletattendance.R;
import com.nuoman.westernele.common.BaseAdapterHelper;
import com.nuoman.westernele.common.QuickAdapter;
import com.nuoman.westernele.home.model.ApplicationModel;

import java.util.List;

/**
 * Created by Administrator on 2015/10/27.
 */
public class ApplicationAdapter extends QuickAdapter<ApplicationModel> {
    public ApplicationAdapter(Context context, int layoutResId, List<ApplicationModel> data) {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(BaseAdapterHelper helper, ApplicationModel item, int position) {
        ImageView imageView = helper.getView(R.id.item_image);
        ImageView tips = helper.getView(R.id.remind_tip_iv);
        if(item.isVisible()){
            tips.setVisibility(View.VISIBLE);
        }else{
            tips.setVisibility(View.INVISIBLE);
        }
        imageView.setImageResource(item.getIds());
        helper.setText(R.id.item_tv, item.getTitle());
    }

}
