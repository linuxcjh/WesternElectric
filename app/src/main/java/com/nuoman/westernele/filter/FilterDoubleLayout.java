package com.nuoman.westernele.filter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.nuoman.westernelectric.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * AUTHOR: Alex
 * DATE: 25/12/2015 10:47
 */
public class FilterDoubleLayout extends LinearLayout {

    OnFilterSelectedListener onSelectListener;


    public void setOnSelectListener(OnFilterSelectedListener onSelectListener) {
        this.onSelectListener = onSelectListener;
    }

    @Bind(R.id.left_listView)
    ListView leftListView;
    @Bind(R.id.right_listView)
    ListView rightListView;
    @Bind(R.id.reset_bt)
    Button resetBt;
    @Bind(R.id.confirm_bt)
    Button confirmBt;
    private Context mContext;
    private int selectImageId;//选中后图标颜色
    private int textColor;//字体颜色

    /**
     * Screen width
     */
    private int width;

    /**
     * Filter Data
     */
    private List<FilterItemDataModel> data = new ArrayList<>();

    private FilterDoubleLeftAdapter leftAdapter;

    private FilterDoubleRightAdapter rightAdapter;

    private int leftSelectPosition;//左边选中的位置

    public Map<Integer, Integer> selectMap = new HashMap<>();

    public FilterDoubleLayout(Context context) {
        this(context, null);
    }


    public FilterDoubleLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FilterDoubleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context);
    }

    private void initViews(Context context) {
        this.mContext = context;
        View view = LayoutInflater.from(mContext).inflate(R.layout.filter_double_layout, null);
        addView(view);
        ButterKnife.bind(this, view);

        leftListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FilterItemDataModel model = (FilterItemDataModel) parent.getItemAtPosition(position);
                leftSelectPosition = position;
                view.findViewById(R.id.root_layout).setBackgroundColor(Color.WHITE);
                leftAdapter.setSelectPosition(position);

                rightAdapter.setData(model.getFilterItems());
                rightAdapter.setLeftSelection(position);

                if (selectMap.containsKey(position)) {
                    rightAdapter.setSelectPosition(selectMap.get(position), position);
                }

                rightListView.setAdapter(rightAdapter);
            }
        });

        rightListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                rightAdapter.setLeftSelection(leftSelectPosition);
                rightAdapter.setSelectPosition(position, leftSelectPosition);

                ImageView imageView = (ImageView) view.findViewById(R.id.select_iv);
                if (imageView.getVisibility() != View.VISIBLE) {
                    selectMap.put(leftSelectPosition, position);
                } else {
                    if (selectMap.containsKey(leftSelectPosition)) {
                        selectMap.remove(leftSelectPosition);
                    }
                }
            }
        });
    }

    /**
     * init Data
     *
     * @param data
     */
    public void setData(List<FilterItemDataModel> data) {
        this.data = data;
        leftAdapter = new FilterDoubleLeftAdapter(mContext, R.layout.filter_double_left_item, data);
        leftListView.setAdapter(leftAdapter);
        if (data != null && data.size() > 0) {
            leftAdapter.setSelectPosition(0);
            rightAdapter = new FilterDoubleRightAdapter(mContext, R.layout.filter_double_right_item, data.get(0).getFilterItems());
            rightAdapter.setSelectPosition(0, 0);
            setDefaultSelection(data);
        } else {
            rightAdapter = new FilterDoubleRightAdapter(mContext, R.layout.filter_double_right_item);
        }
        rightAdapter.setSelectImageId(selectImageId);
        rightListView.setAdapter(rightAdapter);
    }


    /**
     * 设置默认选中项
     */
    private void setDefaultSelection(List<FilterItemDataModel> data) {
        for (int i = 0; i < data.size(); i++) {
            selectMap.put(i, 0);
        }
    }

    /**
     * 获取已选筛选值
     */
    public List<TransFilterItemDataModel> getFilterData() {

        List<TransFilterItemDataModel> resultList = new ArrayList<>();
        for (Iterator it = selectMap.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<Integer, Integer> entry = (Map.Entry<Integer, Integer>) it.next();
            TransFilterItemDataModel model = new TransFilterItemDataModel();
            List<SelectItemModel> filterItems = new ArrayList<>();

            int leftPosition = entry.getKey();
            int rightToLeftPosition = entry.getValue();
            filterItems.add(data.get(leftPosition).getFilterItems().get(rightToLeftPosition));
            model.setFilterKey(data.get(leftPosition).getFilterKey());
            model.setFilterValues(filterItems);
            resultList.add(model);
        }
        return resultList;
    }

    @OnClick({R.id.reset_bt, R.id.confirm_bt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.reset_bt:
                resetView();
                break;
            case R.id.confirm_bt:
                onSelectListener.obtainDoubleSelectedResult(getFilterData());
                break;

        }
    }


    /**
     * 重置
     */
    public void resetView() {
        if (selectMap != null && data != null) {
            selectMap.clear();
            setData(data);

        }
    }

    /**
     * 设置显示的颜色
     * @param selectImageId
     * @param textColor
     */
    public void setDisplayColor( int selectImageId, int textColor) {
        this.selectImageId = selectImageId;
        this.textColor = textColor;
        if (textColor != 0) {
            confirmBt.setTextColor(ContextCompat.getColor(mContext,textColor));
        }
    }
}
