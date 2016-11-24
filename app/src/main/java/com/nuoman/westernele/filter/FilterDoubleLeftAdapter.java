package com.nuoman.westernele.filter;

import android.content.Context;

import com.nuoman.westernele.common.BaseAdapterHelper;
import com.nuoman.westernele.common.QuickAdapter;
import com.nuoman.westernelectric.R;

import java.util.List;

/**
 * Created by Alex on 15/12/4.
 */
public class FilterDoubleLeftAdapter extends QuickAdapter<FilterItemDataModel> {

    private int selectPosition = -1;
    public FilterDoubleLeftAdapter(Context context, int layoutResId, List<FilterItemDataModel> data) {
        super(context, layoutResId, data);

    }

    public void setSelectPosition(int position) {
        this.selectPosition = position;
        notifyDataSetChanged();
    }


    @Override
    protected void convert(BaseAdapterHelper helper, FilterItemDataModel item, int position) {
        helper.setText(R.id.content_tv,item.getFilterText());

        if (selectPosition == position) {
            helper.getView(R.id.root_layout).setBackgroundResource(R.color.colorWhite);
        } else {
            helper.getView(R.id.root_layout).setBackgroundResource(R.color.colorBg);
        }

    }

}
