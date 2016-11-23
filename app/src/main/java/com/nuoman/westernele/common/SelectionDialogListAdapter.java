package com.nuoman.westernele.common;

import android.content.Context;

import com.nuoman.westernele.mine.model.BaseDataModel;
import com.nuoman.westernelectric.R;

import java.util.List;

/**
 * Created by 唐磊 on 2015/11/5.
 *   选择对话框ListView 适配器
 */
public class SelectionDialogListAdapter extends QuickAdapter<BaseDataModel> {


    public SelectionDialogListAdapter(Context context, int layoutResId, List<BaseDataModel> data) {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(BaseAdapterHelper helper, BaseDataModel item, int position) {
        helper.setText(R.id.text,item.getDictionaryName());
    }
}
