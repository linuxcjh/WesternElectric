package com.nuoman.westernele.numberDetail.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nuoman.westernele.common.BaseFragment;
import com.nuoman.westernele.numberDetail.model.NodeStatusItem;
import com.nuoman.westernelectric.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 进度图标下部的布局
 * Created by 杨小过 on 2016/11/24.
 */

public class ProgressChartPartFragment extends BaseFragment {


    public static ProgressChartPartFragment newInstance(List<NodeStatusItem> nodeStatusItems) {
        ProgressChartPartFragment progressChartPartFragment = new ProgressChartPartFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("nodeStatusItems", (Serializable) nodeStatusItems);
        progressChartPartFragment.setArguments(bundle);
        return progressChartPartFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_progress_chart_part, container, false);
        RecyclerView rv_grid = (RecyclerView) rootView.findViewById(R.id.rv_grid);
        rv_grid.setLayoutManager(new GridLayoutManager(getContext(), 3));
        rv_grid.addItemDecoration(new SpaceItemDecoration(39,9));
        rv_grid.setAdapter(new ProgressChartPartAdapter());
        return rootView;
    }

    class ProgressChartPartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<NodeStatusItem> data;

        ProgressChartPartAdapter() {
            data = new ArrayList<>();
            Bundle arguments = getArguments();
            @SuppressWarnings("unchecked")
            List<NodeStatusItem> nodeStatusItems = (List<NodeStatusItem>) arguments.getSerializable("nodeStatusItems");
            data = nodeStatusItems;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View inflateView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_progress_chart_part, parent, false);
            return new ProgressChartViewHolder(inflateView);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((ProgressChartViewHolder) holder).setView(data.get(position));
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class ProgressChartViewHolder extends RecyclerView.ViewHolder {

            private TextView tv_progress_chart_part;

            ProgressChartViewHolder(View itemView) {
                super(itemView);
                tv_progress_chart_part = (TextView) itemView.findViewById(R.id.tv_progress_chart_part);
            }

            void setView(NodeStatusItem nodeStatusItem) {
                switch (nodeStatusItem.getNodeStatus()) {
                    case "0":
                        tv_progress_chart_part.setBackgroundResource(R.drawable.gray_corners);
                        break;
                    case "1":
                        tv_progress_chart_part.setBackgroundResource(R.drawable.green_corners);
                        break;
                    case "2":
                        tv_progress_chart_part.setBackgroundResource(R.drawable.yellow_corners);
                        break;
                    case "3":
                        tv_progress_chart_part.setBackgroundResource(R.drawable.red_corners);
                        break;
                }
                tv_progress_chart_part.setText(nodeStatusItem.getNodeName());
            }
        }
    }


}
