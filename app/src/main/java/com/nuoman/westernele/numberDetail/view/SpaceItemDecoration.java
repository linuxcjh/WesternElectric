package com.nuoman.westernele.numberDetail.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 节点信息间距
 * Created by 杨小过 on 2016/11/24.
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int horizontal;
    private int vertical;

    public SpaceItemDecoration(int horizontal, int vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = horizontal;
        outRect.bottom = vertical;
        if (parent.getChildLayoutPosition(view) % 3 == 0)
            outRect.left = 0;
    }
}
