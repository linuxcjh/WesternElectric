package com.nuoman.westernele.components;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.nuoman.westernele.common.NuoManConstant;
import com.nuoman.westernelectric.R;

/**
 * AUTHOR: Alex
 * DATE: 26/10/2015 23:04
 */
public class SearchPopupWindow implements View.OnClickListener {


    private EditText searchEt;
    private ImageView clearBt;
    private Button cancelBt;
    private LinearLayout search_layout;
    private View view;
    public PopupWindow mPopupWindow;
    private Context mContext;
    private int mDisplayHeight;
    private ListView listView;
    private Handler handler;

    public String hintText;//提示


    public SearchPopupWindow(Context context, int displayHeight) {
        this(context, displayHeight, null);
    }

    public SearchPopupWindow(Context context, int displayHeight, Handler handler) {
        this.mContext = context;
        this.mDisplayHeight = displayHeight;
        this.handler = handler;
    }


    public PopupWindow getPopupWindow() {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.search_layout, null);
        initView();
        initListener();
        mPopupWindow = new PopupWindow(view, ((Activity) mContext).getWindowManager().getDefaultDisplay().getWidth(), mDisplayHeight);
        mPopupWindow.setContentView(view);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        return mPopupWindow;
    }


    private void initView() {
        searchEt = (EditText) view.findViewById(R.id.search_et);
        clearBt = (ImageView) view.findViewById(R.id.clear_bt);
        cancelBt = (Button) view.findViewById(R.id.cancel_bt);
        listView = (ListView) view.findViewById(R.id.listView);
        search_layout = (LinearLayout) view.findViewById(R.id.search_layout);
        searchEt.requestFocus();
        if (!TextUtils.isEmpty(hintText)) {
            searchEt.setHint(hintText);
        }

        openKeyboard(new Handler(), 200);

    }

    /**
     * 弹出键盘
     *
     * @param mHandler
     * @param s
     */
    private void openKeyboard(Handler mHandler, int s) {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }, s);
    }


    private void initListener() {

        clearBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchEt.setText("");
            }
        });

        cancelBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mPopupWindow.dismiss();
            }
        });

        searchEt.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        searchEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
                if (arg1 == EditorInfo.IME_ACTION_SEARCH) {//搜索调接口
                    String searchStr = searchEt.getText().toString();


                    if (!TextUtils.isEmpty(searchStr)) {

                        handler.sendMessage(handler.obtainMessage(NuoManConstant.SEARCHPOPUPWINDOWRESULT, searchStr));
                        mPopupWindow.dismiss();


                    } else
                        Toast.makeText(mContext.getApplicationContext(), "搜索关键字不能为空！", Toast.LENGTH_SHORT).show();
                }
                return false;
            }

        });

        searchEt.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!TextUtils.isEmpty(s)) {
                    listView.setVisibility(View.VISIBLE);
                    search(s.toString());
                } else {
                    listView.setVisibility(View.GONE);

                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s.toString())) {
                    clearBt.setVisibility(View.VISIBLE);
                } else {
                    clearBt.setVisibility(View.GONE);
                }
            }
        });
    }

    private void search(String keyWord) {
//        if (listData != null && listData.size() > 0) {
//            listSearch = new ArrayList<>();
//            for (SelectModel model : listData) {
//                String name = model.getName();
//                if (name.contains(keyWord)) {
//                    listSearch.add(name);
//                    map.put(name, model);
//                }
//            }
//            adapter = new SearchListAdapter(mContext, R.layout.search_popupwindow_list_itm, listSearch);
//            listView.setAdapter(adapter);
//        }

    }

    @Override
    public void onClick(View v) {

        handler.sendMessage(handler.obtainMessage(NuoManConstant.SEARCHPOPUPWINDOWRESULT, searchEt.getText().toString()));
        mPopupWindow.dismiss();


    }


//    public void setListData(List<SelectModel> listData) {
//        this.listData = listData;
//    }


    /**
     * 设置背景色
     *
     * @param color
     */
    public void setBackGroundColor(int color) {
        search_layout.setBackgroundColor(ContextCompat.getColor(mContext, color));
    }

}


