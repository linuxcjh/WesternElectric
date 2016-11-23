package com.nuoman.westernele.home;

import android.os.Bundle;
import android.os.Process;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nuoman.westernelectric.R;
import com.nuoman.westernele.common.BaseActivity;
import com.nuoman.westernele.common.utils.AppConfig;
import com.nuoman.westernele.common.utils.Utils;
import com.nuoman.westernele.contacts.ContactsFragment;
import com.nuoman.westernele.mine.MineFragment;
import com.nuoman.westernele.set.SetFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * AUTHOR: Alex
 * DATE: 21/10/2015 19:16
 */
public class MainTableActivity extends BaseActivity {


    @Bind(R.id.container_layout)
    FrameLayout containerLayout;
    @Bind(R.id.tab_ws_iv)
    ImageView tabWsIv;
    @Bind(R.id.tab_ws_tv)
    TextView tabWsTv;
    @Bind(R.id.tab_ws_layout)
    LinearLayout tabWsLayout;
    @Bind(R.id.tab_client_iv)
    ImageView tabClientIv;
    @Bind(R.id.tab_client_tv)
    TextView tabClientTv;
    @Bind(R.id.tab_client_layout)
    public LinearLayout tabClientLayout;
    @Bind(R.id.tab_app_iv)
    ImageView tabAppIv;
    @Bind(R.id.tab_app_tv)
    TextView tabAppTv;
    @Bind(R.id.tab_mine_iv)
    ImageView tabMineIv;
    @Bind(R.id.tab_mine_tv)
    TextView tabMineTv;
    @Bind(R.id.tab_mine_layout)
    LinearLayout tabMineLayout;
    @Bind(R.id.bottom_layout)
    LinearLayout bottomLayout;
    @Bind(R.id.top_view)
    ImageView topView;
    @Bind(R.id.root_layout)
    LinearLayout rootLayout;
    @Bind(R.id.tab_app_new_layout)
    RelativeLayout tabAppNewLayout;

    MineFragment mineFirstFragment;
    MainFragment mainFragment;
    ContactsFragment contactsFragment;
    SetFragment setFragment;

    @Bind(R.id.middle_container_layout)
    FrameLayout middleContainerLayout;


    private FragmentManager fragmentManager;
    private List<Fragment> fragments = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab_layout);
        ButterKnife.bind(this);
        init();
        obtainDisplayHeight();
        defaultSelect();
    }


    private void init() {

        fragmentManager = getSupportFragmentManager();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    /**
     * Default selected
     */
    private void defaultSelect() {
        changeStatus(R.id.tab_ws_layout);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        mainFragment = new MainFragment();
        fragments.add(mainFragment);
        transaction.add(R.id.container_layout, mainFragment);
        transaction.commit();

    }

    @OnClick({R.id.tab_ws_layout, R.id.tab_app_new_layout, R.id.tab_client_layout, R.id.tab_mine_layout, R.id.bottom_layout})
    public void onClick(View view) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        switch (view.getId()) {
            case R.id.tab_ws_layout:
                changeStatus(view.getId());
                hideFragments(transaction);
                if (mainFragment == null) {
                    mainFragment = new MainFragment();
                    transaction.add(R.id.container_layout, mainFragment);
                } else {
                    transaction.show(mainFragment);
                }
                transaction.commit();
                break;
            case R.id.tab_client_layout:

                changeStatus(view.getId());
                hideFragments(transaction);
                if (contactsFragment == null) {
                    contactsFragment = new ContactsFragment();
                    fragments.add(contactsFragment);
                    transaction.add(R.id.container_layout, contactsFragment);
                } else {
                    transaction.show(contactsFragment);
                }
                transaction.commit();

                break;


            case R.id.tab_app_new_layout:
                changeStatus(view.getId());
                hideFragments(transaction);

                if (setFragment == null) {
                    setFragment = new SetFragment();
                    fragments.add(setFragment);
                    transaction.add(R.id.container_layout, setFragment);
                } else {
                    transaction.show(setFragment);
                }
                transaction.commit();
                break;
            case R.id.tab_mine_layout:
                changeStatus(view.getId());
                hideFragments(transaction);
                if (mineFirstFragment == null) {
                    mineFirstFragment = new MineFragment();
                    fragments.add(mineFirstFragment);
                    transaction.add(R.id.container_layout, mineFirstFragment);
                } else {
                    transaction.show(mineFirstFragment);
                }
                transaction.commit();
                break;

        }
    }


    /**
     * Change navigation status
     *
     * @param resId
     */
    private void changeStatus(int resId) {

        resetStatus();
        switch (resId) {
            case R.id.tab_ws_layout:
                setStatus(tabWsIv, tabWsTv, R.drawable.tabbar_workbench_select);
                break;
            case R.id.tab_client_layout:
                setStatus(tabClientIv, tabClientTv, R.drawable.tabbar_client_select);
                break;
            case R.id.tab_app_new_layout:
                setStatus(tabAppIv, tabAppTv, R.drawable.tabbar_application_select);
                break;
            case R.id.tab_mine_layout:
                setStatus(tabMineIv, tabMineTv, R.drawable.tabbar_me_select);
                break;
        }
    }


    /**
     * 保存屏幕高度、宽度
     */
    private void obtainDisplayHeight() {

        AppConfig.setIntConfig("HEIGHT", Utils.getDeviceHeightPixels(this));
        AppConfig.setIntConfig("WIDTH", Utils.getDeviceWidthPixels(this));

    }


    /**
     * Set navigation status
     *
     * @param imageView
     * @param textView
     * @param resId
     */
    private void setStatus(ImageView imageView, TextView textView, int resId) {


        imageView.setImageResource(resId);
        textView.setTextColor(getResources().getColor(R.color.colorBlueLight));

    }

    /**
     * Reset navigation status
     */
    private void resetStatus() {
        tabWsIv.setImageResource(R.drawable.tabbar_workbench);
        tabWsTv.setTextColor(ContextCompat.getColor(this, R.color.colorAssist));
        tabClientIv.setImageResource(R.drawable.tabbar_client);
        tabClientTv.setTextColor(ContextCompat.getColor(this, R.color.colorAssist));
        tabAppIv.setImageResource(R.drawable.tabbar_application);
        tabAppTv.setTextColor(ContextCompat.getColor(this, R.color.colorAssist));
        tabMineIv.setImageResource(R.drawable.tabbar_me);
        tabMineTv.setTextColor(ContextCompat.getColor(this, R.color.colorAssist));
    }

    /**12
     * Hide exist fragment
     *
     * @param transaction
     */
    private void hideFragments(FragmentTransaction transaction) {

        for (Fragment fragment : fragments) {
            if (fragment != null) {
                if (fragment.isVisible()) {
                    transaction.hide(fragment);
                }
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    private long mExitTime;

    /**
     * 按返回键程序后台运行
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (middleContainerLayout.getVisibility() == View.VISIBLE) {
                middleContainerLayout.setVisibility(View.GONE);
                return true;
            }

            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(AppConfig.getContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                Process.killProcess(Process.myPid());
                System.exit(0);
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
