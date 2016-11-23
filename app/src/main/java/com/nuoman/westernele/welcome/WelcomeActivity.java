package com.nuoman.westernele.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.nuoman.tabletattendance.R;
import com.nuoman.westernele.common.BaseActivity;
import com.nuoman.westernele.common.NuoManConstant;
import com.nuoman.westernele.common.utils.AppConfig;
import com.nuoman.westernele.login.LoginActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WelcomeActivity extends BaseActivity {

    @Bind(R.id.vp_welcome)
    ViewPager vp_welcome;
    @Bind(R.id.btn_welcome_start)
    Button btn_welcome_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_layout);
        ButterKnife.bind(this);
        bindListener();
        bindAdapter();
    }

    private void bindListener() {
        vp_welcome.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 3) {
                    btn_welcome_start.setVisibility(View.VISIBLE);
                } else {
                    btn_welcome_start.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        btn_welcome_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
                AppConfig.setBooleanConfig(NuoManConstant.IS_FIRST, false);
                finish();
            }
        });
    }

    private void bindAdapter() {
        vp_welcome.setAdapter(new WelcomeAdapter(getSupportFragmentManager()));
    }

    class WelcomeAdapter extends FragmentPagerAdapter {

        public WelcomeAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return WelcomeFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return 4;
        }
    }


}
