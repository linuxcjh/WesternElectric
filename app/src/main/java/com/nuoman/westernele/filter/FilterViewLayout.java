package com.nuoman.westernele.filter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nuoman.westernele.common.utils.Utils;
import com.nuoman.westernelectric.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 筛选View
 * AUTHOR: Alex
 * DATE: 25/12/2015 10:47
 */
public class FilterViewLayout extends LinearLayout implements OnFilterSelectedListener {

    OnFilterSelectedListener onSelectListener;

    public void setOnSelectListener(OnFilterSelectedListener onSelectListener) {
        this.onSelectListener = onSelectListener;
    }


    private List<View> mViewArray = new ArrayList<>();
    private PopupWindow popupWindow;

    private int selectPosition;//选择位置
    private Context mContext;

    /**
     * Screen width
     */
    private int width;

    private List<TextView> titleViews = new ArrayList<>();


    public FilterSingleLayout singleLayout;
    public FilterDoubleLayout filterDoubleLayout;


    private int colorId; //标题字体颜色
    private int colorImageId;//标题箭头
    private int selectImageId;//选中后图标颜色

    public FilterViewLayout(Context context) {
        this(context, null);
    }

    public FilterViewLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FilterViewLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context);
    }

    private void initViews(Context context) {
        this.mContext = context;
        width = Utils.getDeviceWidthPixels(mContext);
        setOrientation(LinearLayout.HORIZONTAL);
    }

    /**
     * 单列筛选
     *
     * @param filterSingle
     */
    public void setSingleFilterData(List<SelectItemModel> filterSingle) {
        addFilterSingleLayout(filterSingle);
    }

    /**
     * 双列筛选
     *
     * @param filter
     */
    public void setDoubleFilterData(List<FilterItemDataModel> filter) {
        addFilterDoubleLayout(filter);
    }

    /**
     * 添加筛选view
     *
     * @param filterDoubleData
     */
    private void addFilterDoubleLayout(List<FilterItemDataModel> filterDoubleData) {

        filterDoubleLayout = new FilterDoubleLayout(mContext);
        filterDoubleLayout.setDisplayColor(selectImageId, colorId);
        filterDoubleLayout.setOnSelectListener(this);
        filterDoubleLayout.setData(filterDoubleData);
        addContent(filterDoubleLayout);

    }

    /**
     * 添加排序view
     *
     * @param filterSingleData
     */
    private void addFilterSingleLayout(List<SelectItemModel> filterSingleData) {

        singleLayout = new FilterSingleLayout(mContext, selectImageId);
        singleLayout.setOnSelectListener(this);
        singleLayout.setData(filterSingleData);
        addContent(singleLayout);
    }

    private void addContent(View view) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, Utils.getDeviceHeightPixels(mContext) / 2);
        RelativeLayout r = new RelativeLayout(mContext);
        RelativeLayout rout = new RelativeLayout(mContext);
        RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        r.addView(view, layoutParams);
        rout.addView(r, rl);
        rout.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorTranBg));
        rout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        mViewArray.add(rout);
    }

    /**
     * 标题数据
     *
     * @param titleArr
     */
    public void setTitleValue(String[] titleArr) {
        for (int i = 0; i < titleArr.length; i++) {
            LayoutParams params = new LayoutParams(width / (titleArr.length + 1), ViewGroup.LayoutParams.MATCH_PARENT);
            View view = LayoutInflater.from(mContext).inflate(R.layout.filter_tab_layout, null);
            final TextView contentText = (TextView) view.findViewById(R.id.filter_tv);
            contentText.setText(titleArr[i]);
            view.setTag(i);
            titleViews.add(contentText);
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectPosition = (int) v.getTag();
                    showPopupWindow((int) v.getTag());

                }
            });
            addView(view, params);
        }
    }

    /**
     * 恢复textview样式
     *
     * @param selectPosition
     */
    private void resetTextView(int selectPosition) {

        for (int i = 0; i < titleViews.size(); i++) {
            if (i == selectPosition) {
                setTextTheme(titleViews.get(i), colorId != 0 ? colorId : R.color.colorBlue, colorImageId != 0 ? colorImageId : R.drawable.segment_arrow_select);
            } else {
                setTextTheme(titleViews.get(i), R.color.colorAssist, R.drawable.segment_arrow);
            }
        }
    }

    private void setTextTheme(TextView tv, int colorId, int drId) {
        tv.setTextColor(ContextCompat.getColor(mContext, colorId));
        Drawable dr = getResources().getDrawable(drId);
        dr.setBounds(0, 0, dr.getMinimumWidth(), dr.getMinimumHeight());
        tv.setCompoundDrawables(null, null, dr, null);
        float scala = getResources().getDisplayMetrics().density;
        int padding_size = (int) (5 * scala + 0.5f);
        tv.setCompoundDrawablePadding(padding_size);
    }

    /**
     * 显示showPopupWindow
     */
    private void showPopupWindow(int position) {

        if (popupWindow == null && mViewArray.size() > 0) {
            popupWindow = new PopupWindow(mViewArray.get(position), LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            popupWindow.setFocusable(true);
            popupWindow.setTouchable(true);
            popupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
            popupWindow.setOutsideTouchable(true);
            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    for (int i = 0; i < titleViews.size(); i++) {
                        setTextTheme(titleViews.get(i), R.color.colorAssist, R.drawable.segment_arrow);
                    }
                }
            });
            showPopup(position);
        } else {

            try {
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                } else {
                    showPopup(position);
                }
            } catch (Exception e) {
                Toast.makeText(mContext.getApplicationContext(), "数据未加载，稍后重试！", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        resetTextView(selectPosition);
    }

    private void showPopup(int position) {
        if (popupWindow.getContentView() != mViewArray.get(position)) {
            popupWindow.setContentView(mViewArray.get(position));
        }
        popupWindow.showAsDropDown(this, 0, 0);

    }

    @Override
    public void obtainDoubleSelectedResult(List<TransFilterItemDataModel> resultList) {
        popupWindow.dismiss();
        if (singleLayout != null) {
            singleLayout.resetView();
        }
        onSelectListener.obtainDoubleSelectedResult(resultList);

    }

    @Override
    public void obtainSingleFilterSelectedResult(SelectItemModel resultModel, int select) {
        popupWindow.dismiss();
        if (filterDoubleLayout != null) {
            filterDoubleLayout.resetView();
        }
        onSelectListener.obtainSingleFilterSelectedResult(resultModel, selectPosition);
    }


    /**
     * 设置颜色
     *
     * @param colorId
     * @param colorImageId
     * @param selectImageId
     */
    public void setDisplayColor(int colorId, int colorImageId, int selectImageId) {
        this.colorId = colorId;
        this.colorImageId = colorImageId;
        this.selectImageId = selectImageId;

    }

}
