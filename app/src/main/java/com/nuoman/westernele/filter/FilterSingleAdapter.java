package com.nuoman.westernele.filter;

import android.content.Context;
import android.view.View;

import com.nuoman.westernele.common.BaseAdapterHelper;
import com.nuoman.westernele.common.QuickAdapter;
import com.nuoman.westernelectric.R;

import java.util.List;

/**
 * Created by 陈建辉 on 15/12/4.
 */
public class FilterSingleAdapter extends QuickAdapter<SelectItemModel> {

    private int selectImageId;//选中后图标颜色

    private int selectPosition = -1;

    public FilterSingleAdapter(Context context, int layoutResId, List<SelectItemModel> data) {
        super(context, layoutResId, data);

    }

    public void setSelectImageId(int selectImageId){
        this.selectImageId = selectImageId;
        notifyDataSetChanged();
    }

    public void setSelectPosition(int position) {
        this.selectPosition = position;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(BaseAdapterHelper helper, SelectItemModel item, int position) {
        helper.setText(R.id.single_content_tv, item.getItemText());

        if(selectImageId!=0){
            helper.setImageResource(R.id.single_select_iv,selectImageId);
        }
        if (position == selectPosition) {
            helper.getView(R.id.single_select_iv).setVisibility(View.VISIBLE);
        } else {
            helper.getView(R.id.single_select_iv).setVisibility(View.INVISIBLE);
        }
    }

}
