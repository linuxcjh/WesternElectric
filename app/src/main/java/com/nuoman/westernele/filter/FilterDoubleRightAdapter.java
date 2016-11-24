package com.nuoman.westernele.filter;

import android.content.Context;
import android.view.View;

import com.nuoman.westernele.common.BaseAdapterHelper;
import com.nuoman.westernele.common.QuickAdapter;
import com.nuoman.westernelectric.R;

import java.util.List;

/**
 * Created by 唐磊 on 15/12/4.
 */
public class FilterDoubleRightAdapter extends QuickAdapter<SelectItemModel> {

    private int selectImageId;//选中后图标颜色

    private int selectPosition = -1;
    private int leftSelection;//选中左边的位置
    private int rightToLeftPosition;//右边选中时对应左边的位置


    public FilterDoubleRightAdapter(Context context, int layoutResId) {
        this(context, layoutResId, null);

    }

    public FilterDoubleRightAdapter(Context context, int layoutResId, List<SelectItemModel> data) {
        super(context, layoutResId, data);

    }


    public void setLeftSelection(int leftSelection) {
        this.leftSelection = leftSelection;

    }

    public void setSelectPosition(int position, int rightToLeftPosition) {
        this.selectPosition = position;
        this.rightToLeftPosition = rightToLeftPosition;
        notifyDataSetChanged();
    }

    public void setSelectImageId(int selectImageId) {
        this.selectImageId = selectImageId;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(BaseAdapterHelper helper, SelectItemModel item, int position) {

        helper.setText(R.id.content_tv, item.getItemText());
        if (selectImageId != 0) {
            helper.setImageResource(R.id.select_iv, selectImageId);
        }
        if (position == selectPosition && leftSelection == rightToLeftPosition) {
            helper.getView(R.id.select_iv).setVisibility(View.VISIBLE);
        } else {
            helper.getView(R.id.select_iv).setVisibility(View.INVISIBLE);
        }
    }

}
