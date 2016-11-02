package com.nuoman.westernele.Adapter;

import android.content.Context;

import com.nuoman.westernele.common.BaseAdapterHelper;
import com.nuoman.westernele.common.QuickAdapter;
import com.nuoman.westernele.model.BaseReceivedModel;

import java.util.List;

/**
 * Created by Alex on 2015/11/4.
 */
public class ParentInformationAdapter extends QuickAdapter<BaseReceivedModel> {

    boolean isTeacher;

    public ParentInformationAdapter(Context context, int layoutResId, List<BaseReceivedModel> data, boolean isTeacher) {
        super(context, layoutResId, data);
        this.isTeacher = isTeacher;
    }

    @Override
    protected void convert(BaseAdapterHelper helper, BaseReceivedModel item, int position) {

//        LinearLayout rootLayout = helper.getView(R.id.root_layout);


    }
}
