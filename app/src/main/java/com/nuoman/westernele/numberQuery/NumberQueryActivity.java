package com.nuoman.westernele.numberQuery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nuoman.westernele.common.BaseActivity;
import com.nuoman.westernele.numberDetail.view.NumberDetailActivity;
import com.nuoman.westernele.numberQuery.model.Number;
import com.nuoman.westernelectric.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NumberQueryActivity extends BaseActivity implements Contract.NumberQueryView {

    @Bind(R.id.title_left_tv)
    TextView title_left_tv;

    @Bind(R.id.title_mid_tv)
    TextView title_mid_tv;

    @Bind(R.id.tv_query_reason)
    TextView tv_query_reason;

    @Bind(R.id.et_search_no)
    EditText et_search_no;

    @Bind(R.id.rv_search_reason)
    RecyclerView rv_search_reason;

    private NumberQueryPresenterImp numberQueryPresenterImp;
    private NumberAdapter numberAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_query_layout);
        ButterKnife.bind(this);
        initVariable();
        initLayout();
        bindListener();
        bindAdapter();
    }

    private void initVariable() {
        numberQueryPresenterImp = new NumberQueryPresenterImp(this);
        numberAdapter = new NumberAdapter();
    }

    private void initLayout() {
        title_mid_tv.setText("工号查询");
        rv_search_reason.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    private void bindListener() {
        rv_search_reason.addOnItemTouchListener(new ItemClickListener(this));
    }

    private void bindAdapter() {
        rv_search_reason.setAdapter(numberAdapter);
    }

    @OnClick({R.id.title_left_tv, R.id.btn_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_left_tv:
                finish();
                break;
            case R.id.btn_search:
                numberQueryPresenterImp.queryNumber(et_search_no.getText().toString());
                break;
        }
    }

    @Override
    public void refreshNumber(List<Number> data) {
        if (data.size() > 0) {
            tv_query_reason.setText("查询结果");
            tv_query_reason.setVisibility(View.VISIBLE);
            numberAdapter.refresh(data);
        } else {
            Toast.makeText(this, "未找到", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showNotification(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 列表点击监听
     *
     * @param number 项目id
     */
    public void onItemClick(String number) {
        Intent intent = new Intent(this, NumberDetailActivity.class);
        intent.putExtra("number", number);
        startActivity(intent);
    }


    class NumberAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<Number> mData;

        NumberAdapter() {
            mData = new ArrayList<>();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View inflateView = getLayoutInflater().inflate(R.layout.item_only_text, parent, false);
            return new SearchReasonViewHolder(inflateView);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((SearchReasonViewHolder) holder).initView(mData.get(position));
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        void refresh(List<Number> data) {
            mData = data;
            notifyDataSetChanged();
        }

        Number getItemData(int position) {
            return mData.get(position);
        }

        class SearchReasonViewHolder extends RecyclerView.ViewHolder {

            private TextView textViewItem;

            SearchReasonViewHolder(View itemView) {
                super(itemView);
                textViewItem = (TextView) itemView.findViewById(R.id.textViewItem);
            }

            void initView(Number number) {
                textViewItem.setText(number.getDataName());
            }
        }
    }


    private class ItemClickListener implements RecyclerView.OnItemTouchListener {

        private NumberQueryActivity mNumberQueryActivity;
        private GestureDetector gestureDetector;

        ItemClickListener(NumberQueryActivity numberQueryActivity) {
            mNumberQueryActivity = numberQueryActivity;
            gestureDetector = new GestureDetector(mNumberQueryActivity,
                    new GestureDetector.SimpleOnGestureListener() {
                        @Override
                        public boolean onSingleTapUp(MotionEvent e) {
                            return true;
                        }
                    });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View childView = rv.findChildViewUnder(e.getX(), e.getY());
            if (childView != null && gestureDetector.onTouchEvent(e)) {
                NumberAdapter numberAdapter = (NumberAdapter) rv.getAdapter();
                if (numberAdapter != null) {
                    mNumberQueryActivity.onItemClick(numberAdapter.
                            getItemData(rv.getChildLayoutPosition(childView)).
                            getId());
                } else {
                    throw new RuntimeException("NoAdapter");
                }
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

}
