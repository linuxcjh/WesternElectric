package com.nuoman.westernele.filter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.nuoman.westernelectric.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * AUTHOR: Alex
 * DATE: 25/12/2015 10:47
 */
public class FilterSingleLayout extends LinearLayout {

    OnFilterSelectedListener onSelectListener;

    public void setOnSelectListener(OnFilterSelectedListener onSelectListener) {
        this.onSelectListener = onSelectListener;
    }


    @Bind(R.id.single_listView)
    ListView mListView;
    private Context mContext;
    private List<SelectItemModel> filterSingleModels;
    private FilterSingleAdapter adapter;
    private int selectImageId;//选中后图标颜色


    public FilterSingleLayout(Context context) {
        this(context, null);
    }

    public FilterSingleLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FilterSingleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context);
    }

    public FilterSingleLayout(Context context, int selectImageId) {
        this(context);
        this.selectImageId = selectImageId;
    }

    private void initViews(Context context) {
        this.mContext = context;
        View view = LayoutInflater.from(mContext).inflate(R.layout.filter_single_layout, null);
        addView(view);
        ButterKnife.bind(this, view);

    }


    /**
     * init Data
     *
     * @param data
     */
    public void setData(List<SelectItemModel> data) {
        this.filterSingleModels = data;
        adapter = new FilterSingleAdapter(mContext, R.layout.filter_single_item, filterSingleModels);
        adapter.setSelectImageId(selectImageId);
        resetView();
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setSelectPosition(position);
                onSelectListener.obtainSingleFilterSelectedResult((SelectItemModel) parent.getItemAtPosition(position), position);

            }
        });

    }

    /**
     * 重置
     */
    public void resetView() {
        if (adapter != null && filterSingleModels != null && filterSingleModels.size() > 0) {
            adapter.setSelectPosition(0);
        }
    }


}
