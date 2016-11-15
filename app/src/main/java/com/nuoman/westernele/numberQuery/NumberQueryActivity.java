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

import com.nuoman.tabletattendance.R;
import com.nuoman.westernele.common.BaseActivity;
import com.nuoman.westernele.numberDetail.NumberDetailActivity;

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

    @Bind(R.id.et_search_no)
    EditText et_search_no;

    @Bind(R.id.rv_search_reason)
    RecyclerView rv_search_reason;

    private NumberQueryPresenterImp numberQueryPresenterImp;
    private SearchReasonAdapter searchReasonAdapter;

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
        searchReasonAdapter = new SearchReasonAdapter();
    }

    private void initLayout() {
        title_mid_tv.setText("工号查询");
        rv_search_reason.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    private void bindListener() {
        rv_search_reason.addOnItemTouchListener(new ItemClickListener(this));
    }

    private void bindAdapter() {
        rv_search_reason.setAdapter(searchReasonAdapter);
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
    public void updateSearchReason(List<NumberEntity> searchReasons) {
        searchReasonAdapter.update(searchReasons);
    }

    public void onItemClick(String number) {
        Intent intent = new Intent(this, NumberDetailActivity.class);
        intent.putExtra("number", number);
        startActivity(intent);
    }


    class SearchReasonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<NumberEntity> numberEntities;

        SearchReasonAdapter() {
            numberEntities = new ArrayList<>();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View inflateView = getLayoutInflater().inflate(R.layout.item_only_text, parent, false);
            return new SearchReasonViewHolder(inflateView);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((SearchReasonViewHolder) holder).textViewItem.setText(numberEntities.get(position).getNumber());
        }

        @Override
        public int getItemCount() {
            return numberEntities.size();
        }

        void update(List<NumberEntity> number) {
            numberEntities = number;
            notifyDataSetChanged();
        }

        NumberEntity getItemData(int position) {
            return numberEntities.get(position);
        }

        class SearchReasonViewHolder extends RecyclerView.ViewHolder {

            @Bind(R.id.textViewItem)
            TextView textViewItem;

            SearchReasonViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(itemView);
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
                SearchReasonAdapter searchReasonAdapter = (SearchReasonAdapter) rv.getAdapter();
                if (searchReasonAdapter != null) {
                    mNumberQueryActivity.onItemClick(searchReasonAdapter.
                            getItemData(rv.getChildLayoutPosition(childView)).
                            getNumber());
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
