package com.nuoman.westernele.westNew;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.nuoman.westernele.common.BaseActivity;
import com.nuoman.westernelectric.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WestNewActivity extends BaseActivity {

    @Bind(R.id.title_mid_tv)
    TextView title_mid_tv;

    @Bind(R.id.wv_west_new)
    WebView wv_west_new;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_west_new_layout);
        ButterKnife.bind(this);
        initVariable();
        initView();
    }

    private void initVariable() {

    }

    private void initView() {
        title_mid_tv.setText("西变新闻");
        wv_west_new.setWebViewClient(new WebViewClient());
        WebSettings webSettings = wv_west_new.getSettings();
        webSettings.setDomStorageEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        wv_west_new.loadUrl("http://120.27.137.6:8080/IntelligenceTransformer/Html/PhonePages/News.html");
    }

    @OnClick({R.id.title_left_tv})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.title_left_tv:
                if (wv_west_new.canGoBack())
                    wv_west_new.goBack();
                else
                    finish();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && wv_west_new.canGoBack()) {
            wv_west_new.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
